package cn.antraces.dms.service.impl;

import cn.antraces.dms.dao.CookiesDao;
import cn.antraces.dms.entity.BackJson;
import cn.antraces.dms.entity.Cookies;
import cn.antraces.dms.dao.WorkersDao;
import cn.antraces.dms.entity.Workers;
import cn.antraces.dms.service.WorkersService;
import cn.antraces.dms.utils.AESUtil;
import cn.antraces.dms.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static cn.antraces.dms.config.Constant.WORKERS_CODE;

/**
 * (Wokers)表服务实现类
 *
 * @author silver
 * @since 2021-11-16 11:42:10
 */
@Service("wokersService")
public class WorkersServiceImpl implements WorkersService {
    @Resource
    private WorkersDao workersDao;
    @Resource
    private CookiesDao cookiesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Workers queryById(int id) {
        return this.workersDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param workers 实例对象
     * @return 实例对象
     */
    @Override
    public Workers insert(Workers workers) {
        this.workersDao.insert(workers);
        return workers;
    }

    /**
     * 修改数据
     *
     * @param workers 实例对象
     * @return 实例对象
     */
    @Override
    public Workers update(Workers workers) {
        this.workersDao.update(workers);
        return this.queryById(workers.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(int id) {
        return this.workersDao.deleteById(id) > 0;
    }

    @Override
    public BackJson login(HttpServletResponse response, Workers workers) {
        BackJson back = new BackJson();
        Workers worker = workersDao.login(workers);
        if (worker == null) {
            back.setCode(-WORKERS_CODE - 1);
            back.setMsg("密钥信息错误，请联系系统管理员处理!");
            return back;
        }
        if (0 == worker.getQq() || "".equals(worker.getPsw())) {
            back.setCode(-WORKERS_CODE - 2);
            back.setMsg("unbound");
            return back;
        }
        if (worker.getQq() != workers.getQq() || !worker.getPsw().equals(workers.getPsw())) {
            back.setCode(-WORKERS_CODE - 3);
            back.setMsg("账号或密码错误，请检查后重试");
            return back;
        }
        Cookies c = cookiesDao.queryByKey(workers.getToken());

        Cookie cookie;
        back.setCode(1);
        back.setMsg("ok");

        if (null != c && c.getTime() > System.currentTimeMillis()) {
            cookie = new Cookie("request_token", c.getValue());
            cookie.setMaxAge(60 * 60 * 24 * 3);
            cookie.setPath("/");
            response.addCookie(cookie);
        } else {
            Cookies cookies = new Cookies();
            cookies.setKey(workers.getToken());
            long timestamp = System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 3;
            cookies.setTime(timestamp);

            String token = AESUtil.encrypt(worker.getPsw().substring(8, 24) +
                    MD5Util.getMD5Code_16(String.valueOf(timestamp)), "silvermoonant" + worker.getToken());

            token = MD5Util.getMD5Code_16(token.substring(32, 64)) +
                    MD5Util.getMD5Code_16(token.substring(64)) +
                    MD5Util.getMD5Code_16(token.substring(0, 32));

            cookies.setValue(token);

            if (cookiesDao.insert(cookies) > 0) {
                cookie = new Cookie("request_token", token);
                cookie.setMaxAge(60 * 60 * 24 * 3);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }

        return back;
    }

    @Override
    public BackJson isBound(Workers worker) {
        BackJson back = new BackJson();
        Workers workers = workersDao.login(worker);
        if (null == workers) {
            back.setCode(-WORKERS_CODE - 1);
            back.setMsg("密钥信息错误，请联系系统管理员处理!");
            return back;
        }
        if (0 == workers.getQq() && "".equals(workers.getPsw())) {
            back.setCode(1);
            back.setMsg("unbound");
        } else {
            back.setCode(-WORKERS_CODE - 1);
            back.setMsg("bound");
        }
        return back;
    }

    @Override
    public BackJson bound(Workers worker) {
        BackJson back = new BackJson();
        BackJson json = isBound(worker);
        if (json.getCode() != 1) return json;

        worker.setName("");
        worker.setJob("");
        if (workersDao.updateByToken(worker) > 0) {
            back.setCode(1);
            back.setMsg("ok");
        } else {
            back.setCode(-WORKERS_CODE - 1);
            back.setMsg("系统内部错误，请重试");
        }
        return back;
    }

    @Override
    public BackJson getWorkerInfo(Workers workers) {
        BackJson back = new BackJson();
        Workers worker = workersDao.getWorkerInfo(workers);
        if (null == worker) {
            back.setCode(-WORKERS_CODE - 1);
            back.setMsg("密钥信息错误");
        } else {
            back.setCode(1);
            back.setMsg("ok");
            back.setData(worker);
        }
        return back;
    }
}

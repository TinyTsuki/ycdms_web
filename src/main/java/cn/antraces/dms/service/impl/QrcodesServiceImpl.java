package cn.antraces.dms.service.impl;

import cn.antraces.dms.dao.CookiesDao;
import cn.antraces.dms.dao.WorkersDao;
import cn.antraces.dms.entity.*;
import cn.antraces.dms.dao.QrcodesDao;
import cn.antraces.dms.service.QrcodesService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static cn.antraces.dms.config.Constant.QRCODES_CODE;
import static cn.antraces.dms.utils.Functions.getRandomString;

/**
 * (Qrcodes)表服务实现类
 *
 * @author silver
 * @since 2021-11-16 11:42:10
 */
@Service("qrcodesService")
public class QrcodesServiceImpl implements QrcodesService {
    @Resource
    private QrcodesDao qrcodesDao;
    @Resource
    private CookiesDao cookiesDao;
    @Resource
    private WorkersDao workersDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Qrcodes queryById(int id) {
        return this.qrcodesDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param qrcodes 实例对象
     * @return 实例对象
     */
    @Override
    public Qrcodes insert(Qrcodes qrcodes) {
        this.qrcodesDao.insert(qrcodes);
        return qrcodes;
    }

    /**
     * 修改数据
     *
     * @param qrcodes 实例对象
     * @return 实例对象
     */
    @Override
    public Qrcodes update(Qrcodes qrcodes) {
        this.qrcodesDao.update(qrcodes);
        return this.queryById(qrcodes.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(int id) {
        return this.qrcodesDao.deleteById(id) > 0;
    }

    @Override
    public Qrcodes queryByToken(String token) {
        return qrcodesDao.queryByToken(token);
    }

    @Override
    public BackJson getRegToken(HttpServletRequest request) {
        BackJson back = new BackJson();
        Cookies c = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("request_token"))
                c = cookiesDao.queryByValue(cookie.getValue());
        }
        if (null == c) {
            back.setCode(-QRCODES_CODE - 1);
            back.setMsg("系统内部错误，请重试");
            return back;
        }

        Qrcodes qrcodes = new Qrcodes();
        qrcodes.setCreatime(System.currentTimeMillis());
        qrcodes.setWorker(workersDao.getIdByToken(c.getKey()));
        qrcodes.setToken(getRandomString(32));
        if (qrcodesDao.insert(qrcodes) > 0) {
            back.setCode(1);
            back.setMsg("ok");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", qrcodes.getId());
            jsonObject.put("token", qrcodes.getToken());
            back.setData(jsonObject);
        } else {
            back.setCode(-QRCODES_CODE - 2);
            back.setMsg("系统内部错误，请重试");
        }
        return back;
    }

    @Override
    public BackJson getQrInfo(int id) {
        BackJson back = new BackJson();
        QrInfo qrInfo = qrcodesDao.getQrInfo(id);
        if (null != qrInfo) {
            back.setCode(1);
            back.setMsg("ok");
            back.setData(qrInfo);
        } else {
            back.setCode(-QRCODES_CODE - 1);
            back.setMsg("查询失败");
        }
        return back;
    }

    @Override
    public BackJson isUsed(int id) {
        BackJson back = new BackJson();
        Qrcodes qrcode = qrcodesDao.queryById(id);
        if (null != qrcode) {
            if (qrcode.getUsetime() > 0) {
                back.setCode(1);
                back.setMsg("ok");
            } else {
                back.setCode(-QRCODES_CODE - 1);
                back.setMsg("未被使用");
            }
        } else {
            back.setCode(-QRCODES_CODE - 2);
            back.setMsg("查询失败");
        }

        return back;
    }
}

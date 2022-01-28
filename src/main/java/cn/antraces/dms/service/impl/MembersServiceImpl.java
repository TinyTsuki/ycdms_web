package cn.antraces.dms.service.impl;

import cn.antraces.dms.dao.QrcodesDao;
import cn.antraces.dms.entity.BackJson;
import cn.antraces.dms.entity.Members;
import cn.antraces.dms.dao.MembersDao;
import cn.antraces.dms.entity.Qrcodes;
import cn.antraces.dms.service.MembersService;
import cn.antraces.dms.service.QrcodesService;
import cn.antraces.dms.utils.ExcelUtil;
import cn.antraces.dms.utils.MD5Util;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static cn.antraces.dms.config.Constant.MEMBERS_CODE;

/**
 * (Members)表服务实现类
 *
 * @author silver
 * @since 2021-11-16 11:42:10
 */
@Service("membersService")
public class MembersServiceImpl implements MembersService {
    @Resource
    private MembersDao membersDao;
    @Resource
    private QrcodesDao qrcodesDao;

    /**
     * 新增数据
     *
     * @param members 实例对象
     * @return 实例对象
     */
    @Override
    public Members insert(Members members) {
        this.membersDao.insert(members);
        return members;
    }

    /**
     * 修改数据
     *
     * @param members 实例对象
     * @return json
     */
    @Override
    public BackJson update(Members members) {
        BackJson back = new BackJson();
        if (membersDao.update(members) > 0) {
            back.setCode(1);
            back.setMsg("ok");
        } else {
            back.setCode(-MEMBERS_CODE - 1);
            back.setMsg("修改失败，可能是未做任何更改");
        }
        return back;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public BackJson deleteById(int id) {
        BackJson back = new BackJson();
        if (membersDao.deleteById(id) > 0) {
            back.setCode(1);
            back.setMsg("ok");
        } else {
            back.setCode(-MEMBERS_CODE - 1);
            back.setMsg("删除失败");
        }
        return back;
    }

    @Override
    public BackJson regNewUser(Members member, String token) {
        BackJson back = new BackJson();

        if (null == token || "".equals(token)) {
            back.setCode(-MEMBERS_CODE - 1);
            back.setMsg("Token不能为空");
            return back;
        }
        if (member.getQq() >= 0 && member.getQq() <= 10000) {
            back.setCode(-MEMBERS_CODE - 1);
            back.setMsg("该QQ号不合法");
            return back;
        }

        Qrcodes qrcode = qrcodesDao.queryByToken(token);
        if (null == qrcode) {
            back.setCode(-MEMBERS_CODE - 2);
            back.setMsg("注册码不存在，如有问题请与社团管理联系");
            return back;
        }
        if (qrcode.getUsetime() > 0) {
            back.setCode(-MEMBERS_CODE - 3);
            back.setMsg("注册码已被使用，如有疑问请与社团管理联系");
            return back;
        }
        if (0 != membersDao.getIdByQQ(member.getQq())) {
            back.setCode(-MEMBERS_CODE - 4);
            back.setMsg("该QQ号码已经加入社团了，不要重新加入嗷");
            return back;
        }
        if (0 != membersDao.getIdByPhone(member.getPhone())) {
            back.setCode(-MEMBERS_CODE - 4);
            back.setMsg("手机号码已被使用，若非阁下自己使用请更换手机号或者与社团管理联系");
            return back;
        }
        if (member.getSex() != 1 && member.getSex() != 2) {
            back.setCode(-MEMBERS_CODE - 5);
            back.setMsg("性别有点奇怪？");
            return back;
        }
        if (member.getDepartment() < 1 && member.getDepartment() > 5) {
            back.setCode(-MEMBERS_CODE - 6);
            back.setMsg("意向部门似乎不存在？");
            return back;
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        if (member.getGrade() < year - 5 || member.getGrade() > year) {
            back.setCode(-MEMBERS_CODE - 7);
            back.setMsg("暂不支持该年级的注册，请与社团管理联系");
            return back;
        }
        if (member.getPolitics() < 1 || member.getPolitics() > 3) {
            back.setCode(-MEMBERS_CODE - 8);
            back.setMsg("政治面貌不正确");
            return back;
        }

        if (membersDao.insert(member) > 0) {
            qrcodesDao.updateUsetimeByToken(token, System.currentTimeMillis());
            back.setCode(1);
            back.setMsg("ok");
            back.setData(member);
        } else {
            back.setCode(-MEMBERS_CODE - 9);
            back.setMsg("系统内部错误，请重试");
        }
        return back;
    }

    /**
     * 通过QQ获取ID
     *
     * @param qq QQ
     * @return 实例对象
     */
    @Override
    public BackJson getIdByQQ(long qq) {
        int id = membersDao.getIdByQQ(qq);
        BackJson back = new BackJson();
        if (0 != id) {
            back.setCode(1);
            back.setMsg("ok");
        } else {
            back.setCode(-MEMBERS_CODE - 1);
            back.setMsg("未查询到『" + qq + "』的信息");
        }
        back.setData(id);
        return back;
    }

    /**
     * 查询全部
     *
     * @return 实例对象
     */
    @Override
    public BackJson queryAll() {
        BackJson back = new BackJson();
        List<Members> members = membersDao.queryAll();
        if (null != members && members.size() > 0) {
            back.setCode(1);
            back.setMsg("ok");
            back.setData(members);
        } else {
            back.setCode(-MEMBERS_CODE - 1);
            back.setMsg("nothing");
        }
        return back;
    }

    @Override
    public BackJson queryAll(String md5) {
        BackJson back = queryAll();
        if ("d41d8cd98f00b204e9800998ecf8427e".equals(md5)) return back;
        if (back.getCode() == 1) {
            back.setDate(0);
            if (md5.equals(MD5Util.getMD5Code(JSONObject.toJSONString(back)))) {
                back.setDate(System.currentTimeMillis());
                back.setCode(-MEMBERS_CODE - 2);
                back.setMsg("ok");
                back.setData(md5);
            }
        }
        return back;
    }

    /**
     * 通过部门查询单条数据
     *
     * @param department 部门
     * @return 实例对象
     */
    @Override
    public BackJson queryByDepartment(int department) {
        BackJson back = new BackJson();
        if (department <= 0) {
            back.setCode(-MEMBERS_CODE - 1);
            back.setMsg("部门编号有大问题");
            return back;
        }

        List<Members> members = membersDao.queryByDepartment(department);
        if (null != members && members.size() > 0) {
            back.setCode(1);
            back.setMsg("ok");
            back.setData(members);
        } else {
            back.setCode(-MEMBERS_CODE - 2);
            back.setMsg("nothing");
        }
        return back;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param member 主键
     * @return 实例对象
     */
    @Override
    public BackJson query(Members member) {
        BackJson back = new BackJson();
        List<Members> members = membersDao.query(member);
        if (null != members && members.size() > 0) {
            back.setCode(1);
            back.setMsg("ok");
            back.setData(members);
        } else {
            back.setCode(-MEMBERS_CODE - 1);
            back.setMsg("查询失败");
        }
        return back;
    }

    /**
     * 通过ID修改会员是否已交照片
     *
     * @param id    主键
     * @param photo 状态
     * @return 实例对象
     */
    @Override
    public BackJson changePhoto(int id, int photo) {
        BackJson back = new BackJson();
        if (membersDao.changePhoto(id, photo) > 0) {
            back.setCode(1);
            back.setMsg("ok");
        } else {
            back.setCode(-MEMBERS_CODE - 1);
            back.setMsg("修改失败");
        }
        return back;
    }

    @Override
    public BackJson changeQQ(int id, long qq) {
        BackJson back = new BackJson();
        if (membersDao.changeQQ(id, qq) > 0) {
            back.setCode(1);
            back.setMsg("ok");
        } else {
            back.setCode(-MEMBERS_CODE - 1);
            back.setMsg("修改失败");
        }
        return back;
    }

    @Override
    public void exportExcel(HttpServletResponse response) {
        List<List<String>> memberList = formatMembers(membersDao.queryAll());
        List<String> titles = Arrays.asList("编号", "姓名", "圈名", "性别", "年级", "系别", "专业", "班级", "手机号", "QQ号",
                "寝室栋数", "寝室房间号", "民族", "政治面貌", "部门", "是否已交照片", "二维码ID");
        if (null != memberList)
            ExcelUtil.uploadExcelAboutUser(response, "会员信息表.xlsx", titles, memberList);
        else {
            try {
                BackJson back = new BackJson();
                back.setCode(-MEMBERS_CODE - 1);
                back.setMsg("nothing");
                response.getWriter().append(JSONObject.toJSON(back).toString());
            } catch (IOException ignored) {

            }
        }
    }

    private List<List<String>> formatMembers(List<Members> members) {
        if (null != members) {
            List<List<String>> memberList = new ArrayList<>();
            for (Members member : members) {
                List<String> m = new ArrayList<>();
                m.add(String.valueOf(member.getId()));
                m.add(member.getName());
                m.add(member.getCn());
                int sex = member.getSex();
                m.add(sex == 1 ? "男" : sex == 2 ? "女" : "保密");
                m.add(String.valueOf(member.getGrade()));
                m.add(member.getSeries());
                m.add(member.getMajor());
                m.add(String.valueOf(member.getClasse()));
                m.add(String.valueOf(member.getPhone()));
                m.add(String.valueOf(member.getQq()));
                m.add(String.valueOf(member.getTung()));
                m.add(String.valueOf(member.getRoom()));
                m.add(member.getNation());
                int politics = member.getPolitics();
                m.add(politics == 1 ? "群众" : politics == 2 ? "团员" : politics == 3 ? "党员" : "其他");
                int department = member.getDepartment();
                m.add(department == 1 ? "活动" : department == 2 ? "技术" : department == 3 ? "秘书" : department == 4 ? "宣传" : department == 5 ? "财务" : "未知");
                m.add(member.getPhoto() == 1 ? "是" : "否");
                m.add(String.valueOf(member.getQrid()));
                memberList.add(m);
            }
            return memberList;
        }
        return null;
    }
}

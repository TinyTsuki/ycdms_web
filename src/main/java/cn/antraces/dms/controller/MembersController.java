package cn.antraces.dms.controller;

import cn.antraces.dms.entity.BackJson;
import cn.antraces.dms.entity.Members;
import cn.antraces.dms.service.MembersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * (Members)表控制层
 *
 * @author silver
 * @since 2021-11-16 16:24:33
 */
@RestController
@RequestMapping("api/Member")
public class MembersController {
    /**
     * 服务对象
     */
    @Resource
    private MembersService membersService;

    /**
     * 通过QQ获取ID
     *
     * @param qq QQ
     * @return 实例对象
     */
    @PostMapping("getIdByQQ")
    @ResponseBody
    public BackJson getIdByQQ(long qq) {
        return membersService.getIdByQQ(qq);
    }

    @PostMapping("regNewUser")
    @ResponseBody
    public BackJson regNewUser(Members member, String token) {
        return membersService.regNewUser(member, token);
    }

    @PostMapping("changeUserInfo")
    @ResponseBody
    public BackJson changeUserInfo(Members members) {
        return membersService.update(members);
    }

    /**
     * 查询全部
     *
     * @return 实例对象
     */
    @PostMapping("queryAll")
    @ResponseBody
    public BackJson queryByDepartment(String md5) {
        if (!"".equals(md5))
            return membersService.queryAll(md5);
        return membersService.queryAll();
    }

    /**
     * 通过部门查询单条数据
     *
     * @param department 部门
     * @return 实例对象
     */
    @PostMapping("queryByDepartment")
    @ResponseBody
    public BackJson queryByDepartment(int department) {
        return membersService.queryByDepartment(department);
    }

    /**
     * 通过ID/QQ/手机号查询单条数据
     *
     * @param members 主键
     * @return 实例对象
     */
    @PostMapping("query")
    @ResponseBody
    public BackJson query(Members members) {
        return membersService.query(members);
    }

    /**
     * 通过ID删除数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @PostMapping("deleteById")
    @ResponseBody
    public BackJson deleteById(int id) {
        return membersService.deleteById(id);
    }

    /**
     * 通过ID修改会员是否已交照片
     *
     * @param id    主键
     * @param photo 状态
     * @return 实例对象
     */
    @PostMapping("changePhoto")
    @ResponseBody
    public BackJson changePhoto(int id, int photo) {
        return membersService.changePhoto(id, photo);
    }

    /**
     * 通过ID修改会员QQ
     *
     * @param id 主键
     * @param qq QQ
     * @return 实例对象
     */
    @PostMapping("changeQQ")
    @ResponseBody
    public BackJson changeQQ(int id, long qq) {
        return membersService.changeQQ(id, qq);
    }

    /**
     * 获取学生信息表格
     *
     * @param response 响应
     */
    @PostMapping("exportExcel")
    public void exportExcel(HttpServletResponse response) {
        membersService.exportExcel(response);
    }
}

package cn.antraces.dms.service;

import cn.antraces.dms.entity.BackJson;
import cn.antraces.dms.entity.Members;
import cn.antraces.dms.entity.Workers;

import javax.servlet.http.HttpServletResponse;

/**
 * (Members)表服务接口
 *
 * @author silver
 * @since 2021-11-16 11:42:10
 */
public interface MembersService {

    /**
     * 新增数据
     *
     * @param members 实例对象
     * @return 实例对象
     */
    Members insert(Members members);

    /**
     * 修改数据
     *
     * @param members 实例对象
     * @return 实例对象
     */
    BackJson update(Members members);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BackJson deleteById(int id);

    /**
     * 通过QQ获取ID
     *
     * @param qq QQ
     * @return 实例对象
     */
    BackJson getIdByQQ(long qq);

    BackJson regNewUser(Members members, String token);

    /**
     * 查询全部
     *
     * @return 实例对象
     */
    BackJson queryAll();

    /**
     * 查询全部
     *
     * @return 实例对象
     */
    BackJson queryAll(String md5);

    /**
     * 通过部门查询单条数据
     *
     * @param department 部门
     * @return 实例对象
     */
    BackJson queryByDepartment(int department);

    /**
     * 通过ID/QQ/手机号查询单条数据
     *
     * @param members 主键
     * @return 实例对象
     */
    BackJson query(Members members);

    /**
     * 通过ID修改会员是否已交照片
     *
     * @param id    主键
     * @param photo 状态
     * @return 实例对象
     */
    BackJson changePhoto(int id, int photo);

    /**
     * 通过ID修改会员QQ
     *
     * @param id 主键
     * @param qq QQ
     * @return 实例对象
     */
    BackJson changeQQ(int id, long qq);

    void exportExcel(HttpServletResponse response);
}

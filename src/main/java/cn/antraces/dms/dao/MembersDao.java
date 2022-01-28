package cn.antraces.dms.dao;

import cn.antraces.dms.entity.Members;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Members)表数据库访问层
 *
 * @author silver
 * @since 2021-11-16 11:42:09
 */
@Mapper
public interface MembersDao {

    /**
     * 统计总行数
     *
     * @param members 查询条件
     * @return 总行数
     */
    long count(Members members);

    /**
     * 新增数据
     *
     * @param members 实例对象
     * @return 影响行数
     */
    int insert(Members members);

    /**
     * 修改数据
     *
     * @param members 实例对象
     * @return 影响行数
     */
    int update(Members members);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(int id);

    /**
     * 通过QQ查询ID
     *
     * @param qq QQ
     * @return 影响行数
     */
    int getIdByQQ(long qq);

    /**
     * 通过手机查询ID
     *
     * @param phone 手机号
     * @return 影响行数
     */
    int getIdByPhone(long phone);

    /**
     * 查询全部
     *
     * @return 实例对象
     */
    List<Members> queryAll();

    /**
     * 通过部门查询数据
     *
     * @param department 部门
     * @return member列表
     */
    List<Members> queryByDepartment(int department);

    /**
     * 通过ID/QQ/手机号查询单条数据
     *
     * @param members 主键
     * @return 实例对象
     */
    List<Members> query(Members members);

    /**
     * 通过ID修改会员是否已交照片
     *
     * @param id    主键
     * @param photo 状态
     * @return 实例对象
     */
    int changePhoto(int id, int photo);

    /**
     * 通过ID修改会员QQ
     *
     * @param id 主键
     * @param qq QQ
     * @return 实例对象
     */
    int changeQQ(int id, long qq);

}


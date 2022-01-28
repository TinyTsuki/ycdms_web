package cn.antraces.dms.dao;

import cn.antraces.dms.entity.Checks;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Checks)表数据库访问层
 *
 * @author silver
 * @since 2021-11-16 11:42:09
 */
@Mapper
public interface ChecksDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Checks queryById(int id);

    /**
     * 统计总行数
     *
     * @param checks 查询条件
     * @return 总行数
     */
    long count(Checks checks);

    /**
     * 新增数据
     *
     * @param checks 实例对象
     * @return 影响行数
     */
    int insert(Checks checks);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Checks> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Checks> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Checks> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Checks> entities);

    /**
     * 修改数据
     *
     * @param checks 实例对象
     * @return 影响行数
     */
    int update(Checks checks);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(int id);

}


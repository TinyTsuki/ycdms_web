package cn.antraces.dms.dao;

import cn.antraces.dms.entity.BackJson;
import cn.antraces.dms.entity.Workers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Wokers)表数据库访问层
 *
 * @author silver
 * @since 2021-11-16 11:42:10
 */
@Mapper
public interface WorkersDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Workers queryById(int id);

    /**
     * 统计总行数
     *
     * @param workers 查询条件
     * @return 总行数
     */
    long count(Workers workers);

    /**
     * 新增数据
     *
     * @param workers 实例对象
     * @return 影响行数
     */
    int insert(Workers workers);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Wokers> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Workers> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Wokers> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Workers> entities);

    /**
     * 修改数据
     *
     * @param workers 实例对象
     * @return 影响行数
     */
    int update(Workers workers);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(int id);

    /**
     * 通过token查询qq, psw
     *
     * @param workers 实例对象
     * @return 影响行数
     */
    Workers login(Workers workers);

    /**
     * 通过token修改数据
     *
     * @param workers 实例对象
     * @return 影响行数
     */
    int updateByToken(Workers workers);

    int getIdByToken(String token);

    Workers getWorkerInfo(Workers workers);
}

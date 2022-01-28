package cn.antraces.dms.dao;

import cn.antraces.dms.entity.QrInfo;
import cn.antraces.dms.entity.Qrcodes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Qrcodes)表数据库访问层
 *
 * @author silver
 * @since 2021-11-16 11:42:10
 */
@Mapper
public interface QrcodesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Qrcodes queryById(int id);

    /**
     * 统计总行数
     *
     * @param qrcodes 查询条件
     * @return 总行数
     */
    long count(Qrcodes qrcodes);

    /**
     * 新增数据
     *
     * @param qrcodes 实例对象
     * @return 影响行数
     */
    int insert(Qrcodes qrcodes);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Qrcodes> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Qrcodes> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Qrcodes> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Qrcodes> entities);

    /**
     * 修改数据
     *
     * @param qrcodes 实例对象
     * @return 影响行数
     */
    int update(Qrcodes qrcodes);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(int id);

    Qrcodes queryByToken(String token);

    int updateUsetimeByToken(String token, long usetime);

    QrInfo getQrInfo(int id);
}

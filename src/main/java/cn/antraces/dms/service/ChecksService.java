package cn.antraces.dms.service;

import cn.antraces.dms.entity.Checks;

/**
 * (Checks)表服务接口
 *
 * @author silver
 * @since 2021-11-16 11:42:09
 */
public interface ChecksService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Checks queryById(int id);

    /**
     * 新增数据
     *
     * @param checks 实例对象
     * @return 实例对象
     */
    Checks insert(Checks checks);

    /**
     * 修改数据
     *
     * @param checks 实例对象
     * @return 实例对象
     */
    Checks update(Checks checks);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(int id);

}

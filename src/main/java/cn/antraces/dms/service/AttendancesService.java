package cn.antraces.dms.service;

import cn.antraces.dms.entity.Attendances;

/**
 * (Attendances)表服务接口
 *
 * @author silver
 * @since 2021-11-16 11:42:08
 */
public interface AttendancesService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Attendances queryById(int id);

    /**
     * 新增数据
     *
     * @param attendances 实例对象
     * @return 实例对象
     */
    Attendances insert(Attendances attendances);

    /**
     * 修改数据
     *
     * @param attendances 实例对象
     * @return 实例对象
     */
    Attendances update(Attendances attendances);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(int id);

}

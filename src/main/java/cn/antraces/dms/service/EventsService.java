package cn.antraces.dms.service;

import cn.antraces.dms.entity.Events;

/**
 * (Events)表服务接口
 *
 * @author silver
 * @since 2021-11-16 11:42:09
 */
public interface EventsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Events queryById(int id);

    /**
     * 新增数据
     *
     * @param events 实例对象
     * @return 实例对象
     */
    Events insert(Events events);

    /**
     * 修改数据
     *
     * @param events 实例对象
     * @return 实例对象
     */
    Events update(Events events);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(int id);

}

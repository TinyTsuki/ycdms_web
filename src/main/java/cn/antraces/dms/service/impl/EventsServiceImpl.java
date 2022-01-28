package cn.antraces.dms.service.impl;

import cn.antraces.dms.entity.Events;
import cn.antraces.dms.dao.EventsDao;
import cn.antraces.dms.service.EventsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Events)表服务实现类
 *
 * @author silver
 * @since 2021-11-16 11:42:09
 */
@Service("eventsService")
public class EventsServiceImpl implements EventsService {
    @Resource
    private EventsDao eventsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Events queryById(int id) {
        return this.eventsDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param events 实例对象
     * @return 实例对象
     */
    @Override
    public Events insert(Events events) {
        this.eventsDao.insert(events);
        return events;
    }

    /**
     * 修改数据
     *
     * @param events 实例对象
     * @return 实例对象
     */
    @Override
    public Events update(Events events) {
        this.eventsDao.update(events);
        return this.queryById(events.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(int id) {
        return this.eventsDao.deleteById(id) > 0;
    }
}

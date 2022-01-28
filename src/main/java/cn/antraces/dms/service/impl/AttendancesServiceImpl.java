package cn.antraces.dms.service.impl;

import cn.antraces.dms.entity.Attendances;
import cn.antraces.dms.dao.AttendancesDao;
import cn.antraces.dms.service.AttendancesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Attendances)表服务实现类
 *
 * @author silver
 * @since 2021-11-16 11:42:08
 */
@Service("attendancesService")
public class AttendancesServiceImpl implements AttendancesService {
    @Resource
    private AttendancesDao attendancesDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Attendances queryById(int id) {
        return this.attendancesDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param attendances 实例对象
     * @return 实例对象
     */
    @Override
    public Attendances insert(Attendances attendances) {
        this.attendancesDao.insert(attendances);
        return attendances;
    }

    /**
     * 修改数据
     *
     * @param attendances 实例对象
     * @return 实例对象
     */
    @Override
    public Attendances update(Attendances attendances) {
        this.attendancesDao.update(attendances);
        return this.queryById(attendances.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(int id) {
        return this.attendancesDao.deleteById(id) > 0;
    }
}

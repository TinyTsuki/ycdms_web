package cn.antraces.dms.service.impl;

import cn.antraces.dms.entity.Checks;
import cn.antraces.dms.dao.ChecksDao;
import cn.antraces.dms.service.ChecksService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Checks)表服务实现类
 *
 * @author silver
 * @since 2021-11-16 11:42:09
 */
@Service("checksService")
public class ChecksServiceImpl implements ChecksService {
    @Resource
    private ChecksDao checksDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Checks queryById(int id) {
        return this.checksDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param checks 实例对象
     * @return 实例对象
     */
    @Override
    public Checks insert(Checks checks) {
        this.checksDao.insert(checks);
        return checks;
    }

    /**
     * 修改数据
     *
     * @param checks 实例对象
     * @return 实例对象
     */
    @Override
    public Checks update(Checks checks) {
        this.checksDao.update(checks);
        return this.queryById(checks.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(int id) {
        return this.checksDao.deleteById(id) > 0;
    }
}

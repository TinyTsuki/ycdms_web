package cn.antraces.dms.service;

import cn.antraces.dms.entity.BackJson;
import cn.antraces.dms.entity.Workers;

import javax.servlet.http.HttpServletResponse;

/**
 * (Wokers)表服务接口
 *
 * @author silver
 * @since 2021-11-16 11:42:10
 */
public interface WorkersService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Workers queryById(int id);

    /**
     * 新增数据
     *
     * @param workers 实例对象
     * @return 实例对象
     */
    Workers insert(Workers workers);

    /**
     * 修改数据
     *
     * @param workers 实例对象
     * @return 实例对象
     */
    Workers update(Workers workers);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(int id);

    BackJson login(HttpServletResponse response, Workers workers);

    BackJson isBound(Workers workers);

    BackJson bound(Workers workers);

    BackJson getWorkerInfo(Workers workers);
}

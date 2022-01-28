package cn.antraces.dms.service;

import cn.antraces.dms.entity.BackJson;
import cn.antraces.dms.entity.Qrcodes;

import javax.servlet.http.HttpServletRequest;

/**
 * (Qrcodes)表服务接口
 *
 * @author silver
 * @since 2021-11-16 11:42:10
 */
public interface QrcodesService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Qrcodes queryById(int id);

    /**
     * 新增数据
     *
     * @param qrcodes 实例对象
     * @return 实例对象
     */
    Qrcodes insert(Qrcodes qrcodes);

    /**
     * 修改数据
     *
     * @param qrcodes 实例对象
     * @return 实例对象
     */
    Qrcodes update(Qrcodes qrcodes);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(int id);

    Qrcodes queryByToken(String token);

    BackJson getRegToken(HttpServletRequest request);

    BackJson getQrInfo(int id);

    BackJson isUsed(int id);
}

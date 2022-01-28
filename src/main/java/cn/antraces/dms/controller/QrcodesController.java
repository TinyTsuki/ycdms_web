package cn.antraces.dms.controller;

import cn.antraces.dms.entity.BackJson;
import cn.antraces.dms.service.QrcodesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * (Qrcodes)表控制层
 *
 * @author silver
 * @since 2021-11-16 16:24:33
 */
@RestController
@RequestMapping("api/Qrcode")
public class QrcodesController {
    /**
     * 服务对象
     */
    @Resource
    private QrcodesService qrcodesService;

    @PostMapping("getRegToken")
    @ResponseBody
    public BackJson getRegToken(HttpServletRequest request) {
        return qrcodesService.getRegToken(request);
    }

    @PostMapping("getQrInfo")
    @ResponseBody
    public BackJson getQrInfo(int id) {
        return qrcodesService.getQrInfo(id);
    }

    @PostMapping("isUsed")
    @ResponseBody
    public BackJson isUsed(int id) {
        return qrcodesService.isUsed(id);
    }

}

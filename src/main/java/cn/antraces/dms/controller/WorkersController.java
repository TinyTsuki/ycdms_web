package cn.antraces.dms.controller;

import cn.antraces.dms.entity.BackJson;
import cn.antraces.dms.entity.Cookies;
import cn.antraces.dms.entity.Workers;
import cn.antraces.dms.service.CookiesService;
import cn.antraces.dms.service.WorkersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * (Workers)表控制层
 *
 * @author silver
 * @since 2021-11-16 16:24:33
 */
@RestController
@RequestMapping("api/Worker")
public class WorkersController {
    /**
     * 服务对象
     */
    @Resource
    private WorkersService workersService;

    @PostMapping("login")
    @ResponseBody
    public BackJson login(HttpServletResponse response, Workers workers) {
        return workersService.login(response, workers);
    }

    @PostMapping("isBound")
    @ResponseBody
    public BackJson isBound(Workers workers) {
        return workersService.isBound(workers);
    }

    @PostMapping("bound")
    @ResponseBody
    public BackJson bound(Workers workers) {
        return workersService.bound(workers);
    }

    @PostMapping("getWorkerInfo")
    @ResponseBody
    public BackJson getUserInfo(Workers workers) {
        return workersService.getWorkerInfo(workers);
    }

    @PostMapping("checkPermission")
    @ResponseBody
    public BackJson checkPermission() {
        BackJson back = new BackJson();
        back.setCode(1);
        back.setMsg("ok");
        return back;
    }

    @PostMapping("test")
    @ResponseBody
    public BackJson test() {
        System.out.println(System.currentTimeMillis());
        return new BackJson();
    }
}

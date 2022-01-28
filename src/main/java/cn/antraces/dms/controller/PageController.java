package cn.antraces.dms.controller;

import cn.antraces.dms.entity.Qrcodes;
import cn.antraces.dms.service.OptionsService;
import cn.antraces.dms.service.QrcodesService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class PageController {

    @Resource
    private QrcodesService qrcodesService;

    @Resource
    private OptionsService optionsService;

    @GetMapping
    public String index(Model model) {
        List<String> keys = new ArrayList<>();
        keys.add("clubTitle");

        model.addAttribute("clubInfo", JSONObject.toJSON(optionsService.queryByKeys(keys)));
        return "index";
    }

    @GetMapping("reg")
    public String reg(Model model, String token) {
        List<String> keys = new ArrayList<>();
        keys.add("clubTitle");
        keys.add("groupUrl");
        keys.add("groupNo");
        keys.add("adminInfo1");
        keys.add("regTips");
        keys.add("departmentInfo1");
        keys.add("departmentInfo2");
        keys.add("departmentInfo3");
        keys.add("departmentInfo4");
        keys.add("departmentInfo5");
        keys.add("botQQ");

        model.addAttribute("clubInfo", JSONObject.toJSON(optionsService.queryByKeys(keys)));

        if (null == token) return "error";
        if ("".equals(token.trim())) return "error";
        Qrcodes qrcodes = qrcodesService.queryByToken(token);
        if (null == qrcodes) return "error";
        if (qrcodes.getUsetime() == 0) {
            model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
            return "reg";
        }
        return "error";
    }

}

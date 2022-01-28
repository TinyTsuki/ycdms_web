package cn.antraces.dms.config;

import cn.antraces.dms.dao.CookiesDao;
import cn.antraces.dms.entity.BackJson;
import cn.antraces.dms.entity.Cookies;
import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CookieFilter implements HandlerInterceptor {
    @Resource
    private CookiesDao cookiesDao;

    public CookieFilter(CookiesDao cookiesDao) {
        this.cookiesDao = cookiesDao;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Cookie[] cookies = request.getCookies();
        BackJson back = new BackJson();
        back.setCode(-1);
        back.setMsg("Permission denied");
        if (null == cookies) {
            response.getWriter().append(JSONObject.toJSON(back).toString());
            return false;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("request_token")) {
                Cookies c = cookiesDao.queryByValue(cookie.getValue());
                if (null == c) {
                    response.getWriter().append(JSONObject.toJSON(back).toString());
                    return false;
                }
                //if (cookie.getMaxAge() <= 0) return false;
                if (c.getTime() > System.currentTimeMillis()) return true;
            }
        }
        response.getWriter().append(JSONObject.toJSON(back).toString());
        return false;
    }
}

package cn.antraces.dms.config;

import cn.antraces.dms.dao.CookiesDao;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Resource
    private CookiesDao cookiesDao;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CookieFilter(cookiesDao))
                .addPathPatterns("/api/**")
                .excludePathPatterns(
                        "/api/Worker/login"
                        , "/api/Worker/bound"
                        , "/api/Worker/isBound"
                        , "/api/Member/regNewUser"
                        , "/api/Member/getIdByQQ");
    }
}

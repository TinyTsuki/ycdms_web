package cn.antraces.dms.service.impl;

import cn.antraces.dms.dao.CookiesDao;
import cn.antraces.dms.entity.Cookies;
import cn.antraces.dms.service.CookiesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class CookiesServiceImpl implements CookiesService {
    @Resource
    private CookiesDao cookiesDao;

    @Override
    public Cookies queryByValue(String value) {
        System.out.println(value);
        return cookiesDao.queryByValue(value);
    }
}

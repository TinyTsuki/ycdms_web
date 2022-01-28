package cn.antraces.dms.service;

import cn.antraces.dms.entity.Cookies;

public interface CookiesService {
    Cookies queryByValue(String value);
}

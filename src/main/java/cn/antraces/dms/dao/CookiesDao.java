package cn.antraces.dms.dao;

import cn.antraces.dms.entity.Cookies;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CookiesDao {
    int insert(Cookies cookie);

    Cookies queryByValue(String value);

    Cookies queryByKey(String key);
}

package cn.antraces.dms.dao;

import cn.antraces.dms.entity.Options;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OptionsDao {
    List<Options> queryAll();
}

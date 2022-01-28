package cn.antraces.dms.service.impl;

import cn.antraces.dms.dao.OptionsDao;
import cn.antraces.dms.entity.Options;
import cn.antraces.dms.service.OptionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service("optionsService")
public class OptionsServiceImpl implements OptionsService {
    @Resource
    private OptionsDao optionsDao;

    @Override
    public HashMap<String, String> queryByKeys(List<String> keys) {
        List<Options> options = optionsDao.queryAll();
        HashMap<String, String> back = new HashMap<>();
        if (null == options) return back;

        for (Options option : options) {
            for (String key : keys) {
                if (null == option) continue;
                if (option.getKey().equals(key)) {
                    back.put(option.getKey(), option.getValue());
                }
            }
        }
        return back;
    }
}

package cn.antraces.dms.service;

import java.util.HashMap;
import java.util.List;

public interface OptionsService {
    HashMap<String, String> queryByKeys(List<String> keys);
}

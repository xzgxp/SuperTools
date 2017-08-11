package top.danny.tools.collection;

import java.util.Map;

/**
 * @author huyuyang@lxfintech.com
 * @Title: MapUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-08-10 23:12:04
 */
public class MapUtil {
    /**
     * 判断是否为空
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map) {
        if(map == null) {
            return true;
        }
        return map.isEmpty();
    }

    /**
     * 判断是否不为空
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }
}

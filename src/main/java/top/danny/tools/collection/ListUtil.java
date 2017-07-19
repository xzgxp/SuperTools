package top.danny.tools.collection;

import java.util.List;

/**
 * @author huyuyang@lxfintech.com
 * @Title: ListUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-19 11:03:39
 */
public class ListUtil {

    public static boolean isEmpty(List list) {
        return list == null?true:list.isEmpty();
    }

    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }
}

package top.danny.tools.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author huyuyang@lxfintech.com
 * @Title: IteratorUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-19 17:14:04
 */
public class IteratorUtil {

    /**
     * Iterator转List
     * @param iter
     * @param <T>
     * @return
     */
    public static <T> List<T> convertToList(Iterator<T> iter) {
        List<T> copy = new ArrayList<T>();
        while (iter.hasNext())
            copy.add(iter.next());
        return copy;
    }

}

package top.danny.tools.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author huyuyang@lxfintech.com
 * @Title: IterableUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-19 17:12:43
 */
public class IterableUtil {
    /**
     * 获取Iterable的大小
     * @param iterable
     * @return
     */
    public static<T> int getIterableSize(Iterable<T> iterable){
        Iterator<T> iterator=iterable.iterator();
        int count=0;
        while(iterator.hasNext()){
            count++;
            iterator.next();
        }
        return count;
    }

    /**
     * Iterable转化为List
     * @param iterable
     * @return
     */
    public static<T> List<T> convertToList(Iterable<T> iterable){
        List<T> list=new ArrayList<T>();
        Iterator<T> iterator=iterable.iterator();
        while(iterator.hasNext()){
            T t=iterator.next();
            list.add(t);
        }
        return list;
    }


}

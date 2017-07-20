package top.danny.tools.bean;

/**
 * @author huyuyang@lxfintech.com
 * @Title: BeanUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-19 10:08:31
 */
public class BeanUtil {

    public static boolean isJdkInnerObject(Object object) {
        if(object == null) {
            return false;
        } else if(object.getClass().isPrimitive()) {
            return true;
        } else {
            String packageName = object.getClass().getPackage().getName();
            return packageName.indexOf("java.") > -1;
        }
    }

    public static boolean isEmptyObjects(Object... objects) {
        if(objects == null) {
            return true;
        } else {
            Object[] arr$ = objects;
            int len$ = objects.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Object object = arr$[i$];
                if(object == null) {
                    return true;
                }
            }

            return false;
        }
    }

}

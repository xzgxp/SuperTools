package top.danny.tools.string;

import org.slf4j.helpers.MessageFormatter;
import top.danny.tools.collection.ListUtil;
import top.danny.tools.collection.MapUtil;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * @author huyuyang@lxfintech.com
 * @Title: StringUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-19 11:01:57
 */
public class StringUtil {
    public static final String DEFAULT_SEPARATOR = ",";


    /**
     * 获取格式化后的消息,格式如:my name is {}, my age is {}
     * @param message
     * @param objects
     * @return
     */
    public static String getMessageFormat(String message, Object...objects) {
        if(message == null) {
            return message;
        }
        return MessageFormatter.arrayFormat(message, objects).getMessage();
    }

    /**
     * 判断是否是jdk内部对象
     * @param object
     * @return
     */
    public static boolean isJdkInnerObject(Object object) {
        if(object == null) {
            return false;
        }
        if(object.getClass().isPrimitive()) {
            return true;
        }
        String packageName = object.getClass().getPackage().getName();
        if(packageName.indexOf("java.") > -1) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否为空
     * @param objects
     * @return
     */
    public static boolean isEmptyObjects(Object... objects) {
        if(objects == null) {
            return true;
        }
        for(Object object: objects) {
            if(object == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为空或者没有东西
     * @param objects
     * @return
     */
    public static boolean isBlankObjects(Object... objects) {
        if(objects == null) {
            return true;
        }
        for(Object object: objects) {
            if(object == null) {
                return true;
            }
            if(object instanceof String) {
                if(isBlank(object.toString())){
                    return true;
                };
            }
            if(object instanceof List) {
                if(ListUtil.isEmpty((List)object)) {
                    return true;
                }
            }
            if(object instanceof Map) {
                if(MapUtil.isEmpty((Map)object)) {
                    return true;
                }
            }
        }
        return false;
    }



    /**
     * 根据符号拆分字符串返回数组
     * @param value
     * @param separator
     * @return
     */
    public static String[] getSplitArrayBySeparator(String value, String separator) {
        if(value == null) {
            return null;
        }
        return value.split(separator);
    }

    /**
     * 根据符号拆分字符串返回列表
     * @param value
     * @param separator
     * @return
     */
    public static List<String> getSplitListBySeparator(String value, String separator) {
        if(value == null) {
            return null;
        }
        return Arrays.asList(getSplitArrayBySeparator(value,separator));
    }

    /**
     * 根据逗号拆分字符串返回列表
     * @param value
     * @return
     */
    public static List<String> getSplitListByDefaultSeparator(String value) {
        if(value == null) {
            return null;
        }
        return Arrays.asList(getSplitArrayBySeparator(value,DEFAULT_SEPARATOR));
    }

    /**
     * 判断字符串是否含有逗号分隔符号
     * @param value
     * @return
     */
    public static boolean isContainDefaultSeparator(String value) {
        if(value == null) {
            return false;
        }
        if(value.indexOf(DEFAULT_SEPARATOR) > -1) {
            return true;
        }
        return false;
    }

    /**
     * 从公式中获得需要替换的key
     * @param express 公式
     * @param strings 要跳过的key
     * @return
     */
    public static Set<String> getReplaceKeyFromExpress(String express,String... strings) {
        Matcher m= Pattern.compile("\\#.*?\\#").matcher(express);
        Set<String> set = new HashSet<String>();
        while(m.find()) {
            boolean flag = true;
            String replaceKey = m.group().replace("#","").replace("#","");
            // 需要跳过的key
            for(String key : strings) {
                if(key.equals(replaceKey)) {
                    flag = false;
                }
            }
            if (flag) {
                // 获得所有要替换的key
                set.add(m.group().replace("#","").replace("#",""));
            }

        }
        return set;
    }

    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal(-33.1);
        String express = "max(30,#amt# * #rate#)";

        System.out.println(getReplaceKeyFromExpress(express,bigDecimal.toString(),"0.2"));
    }
}

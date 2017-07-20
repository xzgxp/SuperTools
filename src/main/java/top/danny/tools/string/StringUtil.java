package top.danny.tools.string;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huyuyang@lxfintech.com
 * @Title: StringUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-19 11:01:57
 */
public class StringUtil {

    public static String[] getSplitArrayBySeparator(String value, String separator) {
        return value == null?null:value.split(separator);
    }

    public static List<String> getSplitListBySeparator(String value, String separator) {
        return value == null?null: Arrays.asList(getSplitArrayBySeparator(value, separator));
    }

    public static boolean isContainDefaultSeparator(String value) {
        return value == null?false:value.indexOf(",") > -1;
    }

    public static Set<String> getReplaceKeyFromExpress(String express, String... strings) {
        Matcher m = Pattern.compile("\\#.*?\\#").matcher(express);
        HashSet set = new HashSet();

        while(m.find()) {
            boolean flag = true;
            String replaceKey = m.group().replace("#", "").replace("#", "");
            String[] arr$ = strings;
            int len$ = strings.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String key = arr$[i$];
                if(key.equals(replaceKey)) {
                    flag = false;
                }
            }

            if(flag) {
                set.add(m.group().replace("#", "").replace("#", ""));
            }
        }
        return set;
    }

}

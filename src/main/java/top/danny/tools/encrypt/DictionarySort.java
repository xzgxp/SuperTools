package top.danny.tools.encrypt;


import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author huyuyang@lxfintech.com
 * @Title: DictionarySort
 * @Copyright: Copyright (c) 2016
 * @Description: 字典排序
 * @Company: lxjr.com
 * @Created on 2017-05-26 09:30:29
 */
public class DictionarySort {

    public static void main(String[] args) {
        testSort();
        try {
            System.out.println(EncoderByMd5("80f638826343411098978e656351234567890"+"1496372865000"+"80f638826343411098978e6563509ee4"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void testSort() {
        ArrayList<String> arl = new ArrayList<String>();
        arl.add("DD");
        arl.add("aB");
        arl.add("E");
        arl.add("f");
        arl.add("C");
        arl.add("东海湾");
        arl.add("傲来");
        arl.add("东海湾-岩洞");
        arl.add("傲来药店");
        arl.add("北京");
        arl.add("上海");
        arl.add("湖南");
        arl.add("河南");
        arl.add("河北");
        arl.add("80f638826343411098978e6563509ee4");
        arl.add("1496372865000");
        arl.add("1234567890");

        Collections.sort(arl, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                try {
                    // 取得比较对象的汉字编码，并将其转换成字符串
                    String s1 = new String(o1.toString().getBytes("GB2312"), "ISO-8859-1");
                    String s2 = new String(o2.toString().getBytes("GB2312"), "ISO-8859-1");
                    // 运用String类的 compareTo（）方法对两对象进行比较
                    return s1.compareTo(s2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });// 根据元素的自然顺序 对指定列表按升序进行排序。

        for (int i = 0; i < arl.size(); i++) {
            System.out.println(arl.get(i));

        }
    }

    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
}

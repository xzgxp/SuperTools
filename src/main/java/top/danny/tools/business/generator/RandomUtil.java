package top.danny.tools.business.generator;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * @author huyuyang@lxfintech.com
 * @Title: RandomUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-08-11 23:11:46
 */
public class RandomUtil {
    public static double[] getNums() {
        Random random = new Random();
        double[] doubles = new double[20];
        for (double d : doubles) {
            double temp = random.nextDouble() * 10;
            DecimalFormat dformat = new DecimalFormat("0.0");
            d = Double.valueOf(dformat.format(temp));
            System.out.println(d);
        }
        return doubles;
    }

    public static String getRandomString(int length){
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String getRandomNumeric(int length){
        return RandomStringUtils.randomNumeric(length);
    }

    public static String getRandomAlphanumeric(int length){
        return RandomStringUtils.randomAlphanumeric(length);
    }
}

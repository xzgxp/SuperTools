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
    private static final Random RANDOM = new Random();

    public RandomUtil() {
    }

    public static String random(int count) {
        return random(count, false, false);
    }

    public static String randomAscii(int count) {
        return random(count, 32, 127, false, false);
    }

    public static String randomAlphabetic(int count) {
        return random(count, true, false);
    }

    public static String randomAlphanumeric(int count) {
        return random(count, true, true);
    }

    public static String randomNumeric(int count) {
        return random(count, false, true);
    }

    public static String random(int count, boolean letters, boolean numbers) {
        return random(count, 0, 0, letters, numbers);
    }

    public static String random(int count, int start, int end, boolean letters, boolean numbers) {
        return random(count, start, end, letters, numbers, (char[])null, RANDOM);
    }

    public static String random(int count, int start, int end, boolean letters, boolean numbers, char... chars) {
        return random(count, start, end, letters, numbers, chars, RANDOM);
    }

    public static String random(int count, int start, int end, boolean letters, boolean numbers, char[] chars, Random random) {
        if(count == 0) {
            return "";
        } else if(count < 0) {
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
        } else {
            if(start == 0 && end == 0) {
                end = 123;
                start = 32;
                if(!letters && !numbers) {
                    start = 0;
                    end = 2147483647;
                }
            }

            char[] buffer = new char[count];
            int gap = end - start;

            while(true) {
                while(true) {
                    while(count-- != 0) {
                        char ch;
                        if(chars == null) {
                            ch = (char)(random.nextInt(gap) + start);
                        } else {
                            ch = chars[random.nextInt(gap) + start];
                        }

                        if(letters && Character.isLetter(ch) || numbers && Character.isDigit(ch) || !letters && !numbers) {
                            if(ch >= '\udc00' && ch <= '\udfff') {
                                if(count == 0) {
                                    ++count;
                                } else {
                                    buffer[count] = ch;
                                    --count;
                                    buffer[count] = (char)('\ud800' + random.nextInt(128));
                                }
                            } else if(ch >= '\ud800' && ch <= '\udb7f') {
                                if(count == 0) {
                                    ++count;
                                } else {
                                    buffer[count] = (char)('\udc00' + random.nextInt(128));
                                    --count;
                                    buffer[count] = ch;
                                }
                            } else if(ch >= '\udb80' && ch <= '\udbff') {
                                ++count;
                            } else {
                                buffer[count] = ch;
                            }
                        } else {
                            ++count;
                        }
                    }

                    return new String(buffer);
                }
            }
        }
    }

    public static String random(int count, String chars) {
        return chars == null?random(count, 0, 0, false, false, (char[])null, RANDOM):random(count, chars.toCharArray());
    }

    public static String random(int count, char... chars) {
        return chars == null?random(count, 0, 0, false, false, (char[])null, RANDOM):random(count, 0, chars.length, false, false, chars, RANDOM);
    }

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

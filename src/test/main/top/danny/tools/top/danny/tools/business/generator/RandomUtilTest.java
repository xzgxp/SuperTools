package top.danny.tools.top.danny.tools.business.generator;

import org.junit.Test;
import top.danny.tools.business.generator.RandomUtil;

/**
 * @author huyuyang@lxfintech.com
 * @Title: RandomUtilTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-08-12 00:49:19
 */
public class RandomUtilTest {

    /**
     * 随机生成指定长度字符串
     */
    @Test
    public void getRandomTest(){
        System.out.println(RandomUtil.getRandomString(60));
        System.out.println(RandomUtil.getRandomNumeric(60));
        System.out.println(RandomUtil.getRandomAlphanumeric(60));
    }
}

package top.danny.tools.file.similarity.no1;

import org.junit.Test;
import top.danny.tools.file.image.similarity.no1.ImageCompare;

import java.io.IOException;

/**
 * @author huyuyang@lxfintech.com
 * @Title: ImageCompareTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-08-12 00:23:59
 */
public class ImageCompareTest {

    // 项目根目录路径
    public static final String path = System.getProperty("user.dir");

    @Test
    public void compareTest() {
        String filePath1 =path+"/src/test/resources/images/example6.jpg";
        String filePath2 =path+"/src/test/resources/images/example5.jpg";
        System.out.println(filePath1);
        try {
            double similary= ImageCompare.getSimilarity(filePath1,filePath2);
            System.out.println("两张图片相似度为："+similary);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

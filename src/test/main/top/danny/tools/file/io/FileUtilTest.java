package top.danny.tools.file.io;

import org.junit.Test;

import java.util.UUID;

/**
 * @author huyuyang@lxfintech.com
 * @Title: FileUtilTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-08-22 18:43:49
 */
public class FileUtilTest {
    String filePath = "/Users/dannyhoo/file/";
    String fileName = UUID.randomUUID() + "myfile.txt";

    @Test
    public void createFileTest() throws InterruptedException {

        FileUtil.createFile(filePath, fileName);

        Thread.sleep(10000);

        FileUtil.delFile(filePath, fileName);

    }

    @Test
    public void createFileAndWriteContentTest() throws InterruptedException {

        FileUtil.writeContent(filePath, fileName, "我的梦说别停留等待,就让光芒折射泪湿的瞳孔,映出心中最想拥有的彩虹,带我奔向那片有你的天空,因为你是我的梦 我的梦");

        //Thread.sleep(10000);

        //FileUtil.delFile(filePath, fileName);

    }
}

package top.danny.tools.net.httpclient;

import org.junit.Test;

import java.io.IOException;

/**
 * @author huyuyang@lxfintech.com
 * @Title: HttpClientDownLoadUtilTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-20 15:13:23
 */
public class HttpClientDownLoadUtilTest {

    @Test
    public void downloadTest() throws IOException {
        String remoteFileUrl="http://7xteop.com2.z0.glb.clouddn.com/image/coder.png";
        String localFileUrl="/Users/dannyhoo/Desktop/coder.png";
        boolean isDownloadSuccess=HttpClientDownloadUtil.executeDownloadFile(remoteFileUrl,localFileUrl,true);
        System.out.println("下载结果："+(isDownloadSuccess?"成功":"失败"));
    }

}

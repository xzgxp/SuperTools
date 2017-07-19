package top.danny.tools.net.java.net;

import org.junit.Test;

/**
 * @author huyuyang@lxfintech.com
 * @Title: HttpRequestTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-07-18 16:24:58
 */
public class HttpRequestTest {

    /**
     * 发送 GET 请求
     */
    @Test
    public void sendGetTest(){
        String s=HttpRequest.sendGet("http://localhost:9001/Home/RequestString", "key=123&value=456");
        System.out.println(s);
    }

    /**
     * 发送 POST 请求
     */
    @Test
    public void sendPOSTTest(){
        String sr=HttpRequest.sendPost("http://localhost:9001/Home/RequestPostString", "key=123&value=456");
        System.out.println(sr);
    }
}

package top.danny.tools.net.httpclient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @author huyuyang@lxfintech.com
 * @Title: SnippetHtml
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-02-25 22:26:54
 * commons-httpclient.commons-httpclient
 */
public class SnippetHtml {

    public static void main(String[] args) {
        String html = SnippetHtml.parseHtml("http://shenfenzheng.293.net/");
        SnippetHtml.getHtmlEarthBean(html);
    }

    /**
     * 通过url获取网站html
     *
     * @param url 网站url
     */
    public static String parseHtml(String url) {
        // 测试HttpClient用法
        HttpClient client = new HttpClient();
        //设置代理服务器地址和端口
        HttpMethod method = null;
        String html = "";
        try {
            method = new GetMethod(url);
            client.executeMethod(method);
            html = method.getResponseBodyAsString();//获取网页内容
        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //释放连接
            if (method != null) {
                method.releaseConnection();
            }
        }
        return html;
    }

    /**
     * 解析html获取地震bean
     *
     * @param html 解析网页html
     * @return List
     */
    public static void getHtmlEarthBean(String html) {
        if (html != null && !"".equals(html)) {
            Document doc = Jsoup.parse(html);
            Elements linksElements = doc.getElementsByAttributeValue("class", "news-table");//获取class名字为 news-table
            for (Element ele : linksElements) {
                Elements linksElements1 = ele.getElementsByTag("td");//获取网页td的标签元素
                for (Element ele1 : linksElements1) {
                    System.out.println(ele1.text());
                }
            }
        }
    }




}

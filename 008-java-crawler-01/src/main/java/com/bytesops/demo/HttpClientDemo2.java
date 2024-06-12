package com.bytesops.demo;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class HttpClientDemo2 {

    /**
     * 发送不带参数的 GET 请求
     */
    public static void sendGet() throws Exception {
        // 创建 httpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 httpGet 对象，设置访问 URL
        HttpGet httpGet = new HttpGet("https://www.xxxx.com");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 对响应信息进行处理 ...

        // 关闭资源
        response.close();
        httpClient.close();
    }

    /**
     * 发送带参数的 GET 请求
     */
    public static void sendGetHasParam() throws Exception {
        // 创建 httpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 URIBuilder
        URIBuilder uriBuilder = new URIBuilder("https://www.xxxx.com");
        // 设置参数
        uriBuilder
                .setParameter("param1", "value1")
                .setParameter("param2", "value2");
        // 创建 httpGet 对象，设置 URI
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 对响应信息进行处理 ...

        // 关闭资源
        response.close();
        httpClient.close();
    }

    /**
     * 发送不带参数的 POST 请求
     */
    public static void sendPost() throws Exception{
        // 创建 httpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 httpPost 对象，设置访问 URL
        HttpPost httpPost = new HttpPost("https://www.xxxx.com");
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 对响应信息进行处理 ...

        // 关闭资源
        response.close();
        httpClient.close();
    }

    /**
     * 发送带参数的 POST 请求
     */
    public static void sendPostHasParam() throws Exception{
        // 创建 httpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 httpPost 对象，设置访问 URL
        HttpPost httpPost = new HttpPost("https://www.xxxx.com");
        // 封装表单中的参数
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("param1", "value1"));
        params.add(new BasicNameValuePair("param2", "value2"));
        /*
         * 创建表单的 entity 对象
         *      parameters：表单数据
         *      charset：编码
         */
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Consts.UTF_8);
        // 设置表单的 entity 对象到 Post 请求中
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 对响应信息进行处理 ...

        // 关闭资源
        response.close();
        httpClient.close();
    }


}

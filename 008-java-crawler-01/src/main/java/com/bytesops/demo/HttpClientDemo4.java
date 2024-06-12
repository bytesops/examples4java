package com.bytesops.demo;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientDemo4 {

    /**
     * 配置请求信息
     */
    private static void setRequestInfo() throws Exception {
        // 创建 httpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 httpGet 对象，设置访问 URL
        HttpGet httpGet = new HttpGet("https://www.xxxx.com");

        // 配置请求信息
        RequestConfig requestConfig = RequestConfig.custom()
                // 创建连接的最长时间，单位是毫秒
                .setConnectTimeout(1000)
                // 设置获取连接的最长时间，单位是毫秒
                .setConnectionRequestTimeout(500)
                // 设置数据传输的最长时间
                .setSocketTimeout(10 * 1000)
                // 还可以设置其它的设置 ...
                .build();

        // 设置请求信息
        httpGet.setConfig(requestConfig);

        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 对响应信息进行处理 ...

        // 关闭资源
        response.close();
        httpClient.close();
    }

}

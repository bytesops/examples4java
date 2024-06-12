package com.bytesops.demo;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpClientDemo3 {

    /**
     * 连接池
     */
    public static void poolManager() throws Exception {
        // 创建连接池管理器
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        connectionManager.setMaxTotal(100);
        // 设置每个主机的最大连接数：因为在爬取数据的时候可通会访问多个主机，如果不设置可能会导致连接不均衡
        connectionManager.setDefaultMaxPerRoute(10);
        // 使用连接池管理器发起请求
        doGet(connectionManager);
        doGet(connectionManager);
    }

    /**
     * 通过连接池发送 http 请求
     *
     * @param connectionManager 连接池
     */
    private static void doGet(PoolingHttpClientConnectionManager connectionManager) throws Exception {
        // 从连接池中获取 HttpClient 对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();
        // 创建 httpGet 对象，设置访问 URL
        HttpGet httpGet = new HttpGet("https://www.xxxx.com");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 对响应信息进行处理 ...

        // 关闭资源
        response.close();
        // 这里要注意的是 httpClient 不需要再关闭了，因为是连接池管理的
        // httpClient.close();
    }

}

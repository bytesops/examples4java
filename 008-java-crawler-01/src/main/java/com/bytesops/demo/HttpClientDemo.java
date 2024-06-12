package com.bytesops.demo;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Objects;

public class HttpClientDemo {

    public static void main(String[] args) {
        // 创建 httpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建 httpGet 对象，设置访问 URL
        HttpGet httpGet = new HttpGet("https://www.rgbku.com/chaxun.html");
        CloseableHttpResponse response = null;
        try {
            // 发送请求
            response = httpClient.execute(httpGet);
            // 根据状态码判断是否响应成功（一般是 200）
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应
                HttpEntity entity = response.getEntity();
                String html = EntityUtils.toString(entity, Consts.UTF_8);
                // 打印响应内容
                System.out.println(html);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                httpClient.close();
                if (Objects.nonNull(response)) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

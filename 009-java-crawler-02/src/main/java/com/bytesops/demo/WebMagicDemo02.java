package com.bytesops.demo;

import com.google.common.base.Charsets;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WebMagicDemo02 implements PageProcessor {

    @Override
    public void process(Page page) {

        // 获取当前页面的 Html 对象
        Html html = page.getHtml();

        // 获取页面上所有的链接
        List<String> links = html.links().all();
        // 放入待处理 url 中
        page.addTargetRequests(links);


        // 获取所有的 src 属性
        Document document = html.getDocument();
        Elements srcElements = document.select("script,img");
        for (Element element : srcElements) {
            if (StringUtils.isNotBlank(element.baseUri())) {
                String src = element.attr("abs:src");
                // 重新设置属性
                element.attr("src", src);
            }
        }

        // 获取所有的标签属性
        Elements aElements = document.select("a,link");
        for (Element element : aElements) {
            if (StringUtils.isNotBlank(element.baseUri())) {
                String href = element.attr("abs:href");
                // 重新设置属性
                element.attr("href", href);
            }
        }

        // 将 html 内容放置 resultItems 中
        page.putField("html", html.get());
    }

    @Override
    public Site getSite() {
        // 返回自定义 Site
        return Site.me()
                // 设置字符集
                .setCharset(Charsets.UTF_8.name())
                // 设置超时时间：5000(单位毫秒)
                .setTimeOut(5000)
                // 设置重试间隔时间：3000(单位毫秒)
                .setRetrySleepTime(3000)
                // 设置重试次数：5
                .setRetryTimes(5);
    }

    public static void main(String[] args) {

        // 创建 spider
        Spider spider = Spider.create(new WebMagicDemo02());

        // 创建 scheduler
        QueueScheduler scheduler = new QueueScheduler();
        // 设置 scheduler 使用布隆过滤器，预计存放一百万条数据
        scheduler.setDuplicateRemover(new BloomFilterDuplicateRemover(1000000));
        // Spider 设置 scheduler
        spider.setScheduler(scheduler);

        // 创建 filePipeline
        FilePipeline filePipeline = new MyFilePipeline();
        // 设置存放路径
        filePipeline.setPath("E:\\web-magic-02\\download-page");
        // Spider 设置 pipeline
        spider.setPipelines(Collections.singletonList(filePipeline));

        // 设置初始 URL
        spider.addUrl("http://yycx.yybq.net/");

        // 开启 2 个线程
        spider.thread(2);
        // 异步爬取
        spider.runAsync();
    }
}

/**
 * 继承 FilePipeline
 */
class MyFilePipeline extends FilePipeline {

    /**
     * 重写 FilePipeline 中的 process 方法
     * 去除打印多余部分的代码
     */
    @SuppressWarnings("all")
    public void process(ResultItems resultItems, Task task) {
        String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;

        try {
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.getFile(path + DigestUtils.md5Hex(resultItems.getRequest().getUrl()) + ".html")), "UTF-8"));
            Iterator var5 = resultItems.getAll().entrySet().iterator();

            while (true) {
                while (var5.hasNext()) {
                    Map.Entry<String, Object> entry = (Map.Entry) var5.next();
                    if (entry.getValue() instanceof Iterable) {
                        Iterable value = (Iterable) entry.getValue();
                        Iterator var8 = value.iterator();

                        while (var8.hasNext()) {
                            Object o = var8.next();
                            printWriter.println(o);
                        }
                    } else {
                        printWriter.println((String) entry.getValue());
                    }
                }
                printWriter.close();
                break;
            }
        } catch (IOException var10) {
            var10.printStackTrace();
        }
    }
}

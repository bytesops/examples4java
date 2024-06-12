package com.bytesops.demo;

import com.google.common.base.Charsets;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;

import java.util.Collections;
import java.util.List;

public class WebMagicDemo01 implements PageProcessor {

    @Override
    public void process(Page page) {

        // 获取当前页面的 Html 对象
        Html html = page.getHtml();

        // 获取页面上所有的链接
        List<String> links = html.links().all();
        // 放入待处理 url 中
        page.addTargetRequests(links);

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
        Spider spider = Spider.create(new WebMagicDemo01());

        // 创建 scheduler
        QueueScheduler scheduler = new QueueScheduler();
        // 设置 scheduler 使用布隆过滤器，预计存放一百万条数据
        scheduler.setDuplicateRemover(new BloomFilterDuplicateRemover(1000000));
        // Spider 设置 scheduler
        spider.setScheduler(scheduler);

        // 创建 filePipeline
        FilePipeline filePipeline = new FilePipeline();
        // 设置存放路径
        filePipeline.setPath("E:\\web-magic-01\\download-page");
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


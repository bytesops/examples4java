package com.bytesops.demo;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.ArrayList;
import java.util.List;

public class TestProcessorDemo implements PageProcessor {

    @Override
    public void process(Page page) {

        // 通过 page 获取 Html 对象
        Html html = page.getHtml();

        // 通过 Html 可以获取到 Document
        Document document = html.getDocument();
        // 有了 Document 就能够通过 Jsoup 的一些操作进行解析，比如说获取 <a> 标签元素
        Elements aElements = document.select("div.zh > div > span > a");
        // 创建 List 集合用于存放 <a> 标签的超链接信息
        List<String> links = new ArrayList<>();
        for (Element aElement : aElements) {
            // 获取 <a> 标签中的链接
            String href = aElement.attr("href");
            // 添加到集合中
            links.add(href);
        }
        System.err.println("links = " + links);

        /*
         * 不过一般也不会用 Jsoup 的方式来解析 Html
         * WebMagic 有一些解析器可以较为方便的拿到这些元素，比如使用 css 解析器
         */
        List<String> href = html.css("div.zh > div > span > a", "href").all();
        // 在将结果封装到 ResultItems（默认设置下会打印在控制台上）
        page.putField("href", href);

        List<String> href2 = html
                // css 选择类为 zh 的标签
                .css(".zh")
                // 获取其下所有的链接
                .links() // 使用这种方式获取链接，如果链接是相对地址的形式会自动进行拼接
                .all();
        page.putField("href2", href2);
    }

    @Override
    public Site getSite() {
        return PageProcessor.super.getSite();
    }

    public static void main(String[] args) {
        Spider.create(new TestProcessorDemo())
                // 设置起始的 URL
                .addUrl("https://www.rgbku.com/chaxun.html")
                // 在当前线程中执行爬虫
                //.run();
                // 在新线程中执行爬虫
                .start();
    }
}

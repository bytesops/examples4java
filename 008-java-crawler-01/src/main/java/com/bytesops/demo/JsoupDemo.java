package com.bytesops.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.net.URL;

public class JsoupDemo {

    public static void main(String[] args) throws Exception {
        // 解析 URL
        Document document = Jsoup.parse(new URL("https://www.rgbku.com/chaxun.html"), 1000);
        // 比如我想要获取 html 文件中 <title> 部分的内容
        String title = document
                // 获取所有的 title 标签
                .getElementsByTag("title")
                // 拿到第一个
                .first()
                // 获取标签中的文本内容
                .text();
        // 打印
        System.out.println("title = " + title);
    }

    /**
     * 通过 URL 加载文档
     */
    public static void loadingUrl() throws Exception {
        /*
         * 解析 URL：
         *      spec：访问问的 url
         *      timeoutMillis：超时时间
         */
        Document document = Jsoup.parse(new URL("https://www.rgbku.com/chaxun.html"), 1000);
        // 解析 document

    }

    /**
     * 通过字符串加载文档
     */
    public static void loadingString() throws Exception {
        String html = "html-content";
        // 解析字符串
        Document document = Jsoup.parse(html);
        // 解析 document

    }

    /**
     * 通过文件架子啊文档
     */
    public static void loadingFile() throws Exception {
        // html 文件
        File file = new File("D:\\demo.html");
        // 解析字符串
        Document document = Jsoup.parse(file, "utf8");
        // 解析 document

    }

    /**
     * 使用DOM方法获取元素
     */
    public static void getElementByDom() throws Exception {
        // 加载 document
        Document document = Jsoup.parse(new URL("https://www.xxxx.com"), 1000);

        // 根据 id 获取元素
        Element idElement = document.getElementById("id");
        // 根据标签获取元素
        Elements tagElements = document.getElementsByTag("tag_name");
        // 根据 class 获取元素
        Elements classElements = document.getElementsByClass("class_name");
        // 根据属性获取元素
        Elements attributeElements = document.getElementsByAttribute("attribute");
        // 通过属性值获取元素
        Elements attributeValueElements = document.getElementsByAttributeValue("attribute", "value");
    }

    /**
     * 使用选择器获取元素
     */
    public static void getElementBySelector() throws Exception {
        // 加载 document
        Document document = Jsoup.parse(new URL("https://www.xxxx.com"), 1000);

        // 通过 id 查找元素
        Element idElement = document.select("#id").first();
        // 通过标签名称查找元素
        Elements tagElements = document.select("tag_name");
        // 通过 class 名称查找元素
        Elements classElements = document.select(".class_name");
        // 通过属性获取元素
        Elements attributeElements = document.select("[attribute]");
        // 通过属性值获取元素
        Elements attributeValueElements = document.select("[attribute=value]");

        /*
         * 选择器可以任意的组合使用
         */

        // tag#id：标签+ID
        Elements tagIdElements = document.select("tag_name#id");
        // tag.class：标签+class
        Elements tagClassElements = document.select("tag_name.class_name");
        // tag[attribute]：标签+属性名
        Elements tagAttributeElements = document.select("tag_name[attribute]");
        // tag[attribute].class：标签+属性名+class
        Elements tagAttributeClassElements = document.select("tag_name[attribute].class_name");
        // ancestor child：查询某个元素下的子元素
        Elements ancestorChildElements = document.select("ancestor child");
        // parent > child：查询直接子元素
        Elements parentChildElements = document.select("parent > child");
        // parent > *：查找所有子元素
        Elements allChildElements = document.select("parent > *");
    }


}

package com.bytesops.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

public class RgbkuChaxunSpider {

    public static void main(String[] args) throws IOException {
        // 获取 document 文档
        Document document = Jsoup.parse(new URL("https://www.rgbku.com/chaxun.html"), 1000);
        // 通过 id = color 获取 tbody 元素
        Element tbodyElement = document.getElementById("color");
        // 获取 tbody 下所有的 <tr> 标签
        assert tbodyElement != null;

        Elements childrenElements = tbodyElement.children();
        for (Element childrenElement : childrenElements) {
            Elements tdElements = childrenElement.children();
            String engName = tdElements.get(1).text();
            String zhName = tdElements.get(2).text();
            String code = tdElements.get(3).text();
            String value = tdElements.get(4).text();
            System.out.println(engName + "\t" + zhName + "\t" + code + "\t" + value);
        }
    }
}

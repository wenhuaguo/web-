package com.kaishengit.servlet;

import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.io.IOUtils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Acer on 2016/12/14.
 */
public class JsoupServlet {
    public static void main(String[] args) throws IOException{
        for (int j = 1; j <10; j++) {
            Document document = Jsoup.connect("http://www.topit.me/?p" + j )
                    .cookie("is_click", "1")
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36")
                    .get();
            Elements elements = document.select("#content .catalog .e > a");

            for (int i = 0; i < elements.size(); i++) {
                Element element = elements.get(i);
                String href = element.attr("href");
                System.out.println(href);
                Document document1 = Jsoup.connect(href)
                        .cookie("is_click", "1")
                        .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36")
                        .get();
                Element elements1 = document1.select("#content #item-tip img").first();
                String img = elements1.attr("src");
                System.out.println(img);
                //伪装成客户端发出请求
                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpGet get = new HttpGet(img);
                HttpResponse response = httpClient.execute(get);
                if (response.getStatusLine().getStatusCode() == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    String imageName = img.substring(img.lastIndexOf("/"));
                    FileOutputStream fileOutputStream = new FileOutputStream(new File("D:/upfile/image" + imageName));
                    IOUtils.copy(inputStream, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    inputStream.close();

                } else {
                    System.out.println("error:" + response.getStatusLine().getStatusCode());
                }

            }
        }
    }
}

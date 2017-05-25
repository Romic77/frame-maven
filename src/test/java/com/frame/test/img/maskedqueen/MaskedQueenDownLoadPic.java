package com.frame.test.img.maskedqueen;

import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MaskedQueenDownLoadPic {
    private String pathName = "F://imgs//";

    private int page = 424;


    public void getDoc() throws IOException {

        for (String href : getHrefs()) {
            //String href = "https://www.queenshow.org/detailnew/358/57107.html";
            Connection connect = HttpConnection.connect(href);
            connect.timeout(3000);
            connect.header("Accept-Encoding", "gzip,deflate,sdch");
            connect.header("Connection", "close");
            connect.validateTLSCertificates(false);
            System.setProperty("jsse.enableSNIExtension", "false");
            connect.execute();
            Elements pathname = connect.get().select(".detailtitle");
            pathName = pathName + pathname.text();

            File f = new File(pathName);
            if (!f.exists()) {
                f.mkdirs();
            }
            Elements pngs = connect.get().select(".fancybox-thumbs img[src~=(?i)\\.(png|jpe?g)]");
            // 遍历元素
            for (Element e : pngs) {
                String src = "https://www.queenshow.org" + e.attr("src").replace("small", "big");// 获取img中的src路径
                // 获取后缀名
                String imageName = src.substring(src.lastIndexOf("/") + 1, src.length());
                System.out.println(src);
                // 连接url
                URL url = new URL(src);
                URLConnection uri = url.openConnection();

                // 获取数据流
                InputStream is = uri.getInputStream();

                // 写入数据流
                OutputStream os = new FileOutputStream(new File(pathName, imageName));

                byte[] buf = new byte[1024];

                int l = 0;

                while ((l = is.read(buf)) != -1) {
                    os.write(buf, 0, l);
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        new MaskedQueenDownLoadPic().getDoc();
    }

    public List<String> getHrefs() throws IOException {
        List<String> hrefsList = new ArrayList<String>();
        String href = "https://www.queenshow.org/catalog.php?cid=349&page=1";
        Connection connect = HttpConnection.connect(href);
        connect.timeout(3000);
        connect.header("Accept-Encoding", "gzip,deflate,sdch");
        connect.header("Connection", "close");
        connect.validateTLSCertificates(false);
        System.setProperty("jsse.enableSNIExtension", "false");
        connect.execute();
        Elements hrefs = connect.get().select(".c2 a");
        for (Element e : hrefs) {
            hrefsList.add(e.attr("href").replace("./","https://www.queenshow.org/"));

        }
        return hrefsList;
    }

}

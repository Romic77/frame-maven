package com.frame.test.img.ru1mm8;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Ru1mmPicDownload1 implements Runnable{
	private String pathName = "F://imgs//ru1mm";

	private int page = 348;

	public void getDoc(int startPage,int endPage) throws IOException {
		for (int i = startPage; i > endPage; i--) {
			File f = new File(pathName + "/" + i);
			if (!f.exists()) {
				f.mkdirs();
			}
			// 以网易为例子
			Document doc = Jsoup.connect("http://www.ru1mm8.com/thread-" + i + ".htm").get();

			// 获取后缀为png和jpg的图片的元素集合
			Elements pngs = doc.select(".break-all img[src~=(?i)\\.(png|jpe?g)]");

			// 遍历元素
			for (Element e : pngs) {
				String src = e.attr("src");// 获取img中的src路径
				// 获取后缀名
				String imageName = src.substring(src.lastIndexOf("/") + 1, src.length());
				System.out.println(src);
				// 连接url
				URL url = new URL(src);
				URLConnection uri = url.openConnection();

				// 获取数据流
				InputStream is = uri.getInputStream();

				// 写入数据流
				OutputStream os = new FileOutputStream(new File(pathName + "/" + i, imageName));

				byte[] buf = new byte[1024];

				int l = 0;

				while ((l = is.read(buf)) != -1) {
					os.write(buf, 0, l);
				}

			}
		}
	}

	@Override
	public void run() {
		try {
			getDoc(348,100);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
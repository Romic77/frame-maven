package com.frame.test.img.disi;

/**
 * Created by Administrator on 2017/5/2.
 * 多线程下载第四印象
 */
public class PicThread {
    public static void main(String[] args) {
        new Thread(new DisiPicDownload1()).start();
        new Thread(new DisiPicDownload2()).start();
        new Thread(new DisiPicDownload3()).start();
    }
}

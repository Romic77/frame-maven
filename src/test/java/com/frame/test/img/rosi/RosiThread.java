package com.frame.test.img.rosi;

import com.frame.test.img.disi.DisiPicDownload1;
import com.frame.test.img.disi.DisiPicDownload2;
import com.frame.test.img.disi.DisiPicDownload3;

/**
 * Created by Administrator on 2017/5/2.
 */
public class RosiThread {
    public static void main(String[] args) {
        new Thread(new RosiPicDownload1()).start();
        new Thread(new RosiPicDownload2()).start();
        new Thread(new RosiPicDownload3()).start();
        new Thread(new RosiPicDownload4()).start();
    }
}

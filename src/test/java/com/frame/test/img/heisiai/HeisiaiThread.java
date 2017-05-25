package com.frame.test.img.heisiai;

import com.frame.test.img.disi.DisiPicDownload1;
import com.frame.test.img.disi.DisiPicDownload2;
import com.frame.test.img.disi.DisiPicDownload3;

/**
 * Created by Administrator on 2017/5/2.
 */
public class HeisiaiThread {
    public static void main(String[] args) {
        //new Thread(new HeisiaiPicDownload4()).start();
        //new Thread(new HeisiaiPicDownload3()).start();
        //new Thread(new HeisiaiPicDownload2()).start();
       new Thread(new HeisiaiPicDownload1()).start();
    }
}

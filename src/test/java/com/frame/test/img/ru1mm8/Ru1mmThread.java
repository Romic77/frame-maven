package com.frame.test.img.ru1mm8;

import com.frame.test.img.heisiai.HeisiaiPicDownload1;
import com.frame.test.img.heisiai.HeisiaiPicDownload2;
import com.frame.test.img.heisiai.HeisiaiPicDownload3;
import com.frame.test.img.heisiai.HeisiaiPicDownload4;

/**
 * Created by Administrator on 2017/5/2.
 */
public class Ru1mmThread {
    public static void main(String[] args) {
        new Thread(new Ru1mmPicDownload1()).start();
        new Thread(new Ru1mmPicDownload2()).start();
    }
}

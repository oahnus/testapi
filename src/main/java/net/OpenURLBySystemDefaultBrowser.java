package net;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

/**
 * Created by oahnus on 2017/4/17
 * 0:14.
 */
public class OpenURLBySystemDefaultBrowser {
    public static void openUrl(String url) {
        URI uri = URI.create(url);

        //获取当前系统桌面扩展
        Desktop desktop = Desktop.getDesktop();

        //判断系统桌面知否支持要执行的功能
        if(desktop.isSupported(Desktop.Action.BROWSE)){
            try {
                desktop.browse(uri);
            } catch (IOException e) {
System.out.println("not Supported Browser");
                e.printStackTrace();
            }
        }
    }

    public static void main(String... args) {
        openUrl("http://www.baidu.com");
    }
}

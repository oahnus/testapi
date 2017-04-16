package http;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by oahnus on 2017/4/16
 * 21:43.
 */
public class NativeHttpsConnection {

    public static void connectTo(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        connection.setDoInput(true);
        connection.setRequestMethod("GET");
        connection.connect();

        System.out.println("cipherSuite=" + connection.getCipherSuite());
        System.out.println("responseCode=" + connection.getResponseCode());
        System.out.println("responseMessage=" + connection.getResponseMessage());
    }

    public static void main(String... args) throws IOException {
        connectTo("https://www.baidu.com:443");
    }
}

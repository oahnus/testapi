package http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by oahnus on 2017/4/16
 * 21:30.
 */
public class ApacheHttp {

    public static String requestWebPage(String url) throws IOException{
        RequestConfig request = RequestConfig
                .custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .build();

        HttpGet httpGet = new HttpGet(url);
        try(
            CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(request).build();
            CloseableHttpResponse response = httpClient.execute(httpGet)
        ) {
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "utf-8");
            } else {
                return String.valueOf(response.getStatusLine().getStatusCode());
            }
        }
    }

    public static void main(String... args) throws IOException {
        String url = "http://www.baidu.com";
        String htmlString = requestWebPage(url);
        System.out.println(htmlString);
    }
}

package weixin.qrcode;

import com.fasterxml.jackson.databind.ObjectMapper;
import weixin.WxConfig;

import javax.imageio.ImageIO;
import javax.net.ssl.*;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * Created by oahnus on 2017/4/26
 * 15:58.
 */
public class WxQrcodeAPI {
    public static void main(String... args) throws IOException, KeyManagementException, NoSuchAlgorithmException {
        String token = WxConfig.getWxAccessToken(WxConfig.TEST_APP_ID, WxConfig.TEST_SECRET);

        String ticket = getTicket(token);
        System.out.println(ticket);
        saveQrcode(ticket);
    }

    public static String getTicket(String token) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        String urlStr = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + token;
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\"expire_seconds\": 2592000, \"action_name\": \"QR_SCENE\"}";

        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        connection.setRequestProperty("Accept", "application/json");

        try (OutputStream out = connection.getOutputStream()) {
            out.write(json.getBytes());
            out.flush();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        return (String) mapper.readValue(stringBuilder.toString(), Map.class).get("ticket");
    }

    public static void saveQrcode(String ticket) throws IOException {
        String urlStr = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket;

        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "image/jpeg");
        connection.setRequestProperty("Accept", "application/json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        InputStream in = connection.getInputStream();
        File outFile = new File("src/main/resources/weixin/qrcode/qrcode.jpg");
        if (!outFile.exists()) outFile.mkdirs();
        ImageIO.write(ImageIO.read(in), "jpg", outFile);
    }
}

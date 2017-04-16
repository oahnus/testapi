package weixin;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by oahnus on 2017/4/13
 * 10:55.
 */
public class WxTemplateMessageAPI {
    // 微信测试公众号
    public static final String APP_ID = "wx51818aa8a91ca10b";
    public static final String SECRET = "6ecc7b0fbb7656834863eca9175c2b98";

    public static void main(String... args) throws IOException {
        String accessToken = getWxAccessToken(APP_ID, SECRET);
        System.out.println("ACCESS_TOKEN=" + accessToken);
        String templateId = getWxTemplateId(accessToken, "TM00015");
        System.out.println("TEMPLATE_ID=" + templateId);
    }

    public static String getWxTemplateId(String token, String template_id_short) throws IOException {
        String postUrl = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?" +
                "access_token=" + token;
        URL url = new URL(postUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        connection.setRequestProperty("Accept", "application/json");

        OutputStream out = connection.getOutputStream();
        String json = "{\"template_id_short\":\"TM00015\"}";
        out.write(json.getBytes());
        out.flush();
        out.close();


        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(stringBuilder.toString());
        Map<String, String> map = mapper.readValue(stringBuilder.toString(), Map.class);
        return map.get("template_id");
    }

    public static String getWxAccessToken(String appId, String secret) throws IOException {
        String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type=client_credential&" +
                "appid=" + appId + "&" +
                "secret=" + secret;
        URL url = new URL(tokenUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(stringBuilder.toString(), Map.class);
        return map.get("access_token");
    }
}

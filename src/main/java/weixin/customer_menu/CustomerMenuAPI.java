package weixin.customer_menu;

import com.fasterxml.jackson.databind.ObjectMapper;
import weixin.WxConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by oahnus on 2017/4/26
 * 17:14.
 */
public class CustomerMenuAPI {
    public static void main(String... args) throws IOException {
        String token = WxConfig.getWxAccessToken(WxConfig.TEST_APP_ID, WxConfig.TEST_SECRET);
System.out.println(token);
        postMenu(token, templateMenuJson());

        String menuJson = getMenuJson(token);
System.out.println(menuJson);
    }

    public static void postMenu(String token ,String menuJson) throws IOException {
        String urlStr = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + token;
        ObjectMapper mapper = new ObjectMapper();

        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        connection.setRequestProperty("Accept", "application/json");

        try (OutputStream out = connection.getOutputStream()) {
            out.write(menuJson.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
System.out.println(mapper.readValue(stringBuilder.toString(), Map.class));
    }

    public static String getMenuJson(String token) throws IOException {
        String urlStr = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + token;
        ObjectMapper mapper = new ObjectMapper();

        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        connection.setRequestProperty("Accept", "application/json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    public static String templateMenuJson() {
        return "{\n" +
                "    \"button\": [\n" +
                "        {\n" +
                "            \"type\": \"view\", \n" +
                "            \"name\": \"百度\", \n" +
                "            \"url\": \"http://www.baidu.com/\"\n" +
                "        }, \n" +
                "        {\n" +
                "            \"type\": \"view\", \n" +
                "            \"name\": \"谷歌\", \n" +
                "            \"url\": \"http://www.google.com/\"\n" +
                "        }, \n" +
                "        {\n" +
                "            \"name\": \"个人\", \n" +
                "            \"sub_button\": [\n" +
                "                {\n" +
                "                    \"type\": \"view\", \n" +
                "                    \"name\": \"github\", \n" +
                "                    \"url\": \"https://github.com/oahnus\"\n" +
                "                }, \n" +
                "                {\n" +
                "                    \"type\": \"view\", \n" +
                "                    \"name\": \"home\", \n" +
                "                    \"url\": \"http://www.oahnus.top\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }
}

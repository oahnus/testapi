package weixin;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by oahnus on 2017/4/20
 * 17:13.
 */
public class WxUserManagementAPI {

    public static void main(String... args) throws IOException {
        String accessToken = WxConfig.getWxAccessToken(WxConfig.APP_ID, WxConfig.SECRET);
System.out.println(accessToken);
        Map userInfoMap = getUserInfo(accessToken, WxConfig.OPEN_ID);
System.out.println(userInfoMap.get("subscribe"));
    }

    public static Map getUserInfo(String accessToken, String openId) throws IOException {
        String postUrl = "https://api.weixin.qq.com/cgi-bin/user/info?" +
                "access_token=" + accessToken + "&" +
                "openid=" + openId + "&" +
                "lang=zh_CN";
        URL url = new URL(postUrl);
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

System.out.println("UserInfo ==> " + stringBuilder.toString());

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(stringBuilder.toString(), Map.class);
    }
}

package weixin;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by oahnus on 2017/4/13
 * 10:55.
 */
public class WxTemplateMessageAPI {

    public static void main(String... args) throws IOException {
        String accessToken = WxConfig.getWxAccessToken(WxConfig.APP_ID, WxConfig.SECRET);
System.out.println("ACCESS_TOKEN=" + accessToken);

        boolean result = sendTemplateMessage(accessToken, WxConfig.OPEN_ID, WxConfig.getProperty("ORDER_STATE_TEMPLATE_ID"));
        System.out.println(result ? "success" : "error");
    }

    public static boolean sendTemplateMessage(String token, String openId, String templateId) throws IOException {
        String json = WxTemplateMessage.getOrderStateJson(openId, templateId, "创建订单成功！", "S00002203", "官费", "200", "请及时支付");
        String postUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + token;
        URL url = new URL(postUrl);
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

        ObjectMapper mapper = new ObjectMapper();
        Integer errcode = (Integer) mapper.readValue(stringBuilder.toString(), Map.class).get("errcode");
        return errcode == 0;
    }
}

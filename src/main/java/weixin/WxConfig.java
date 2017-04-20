package weixin;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

/**
 * Created by oahnus on 2017/4/20
 * 17:15.
 */
public class WxConfig {

    private static Properties properties = new Properties();

    static {
        try(InputStream in = new FileInputStream(
                Paths.get("src/main/resources/weixin/wx.properties").toFile())){
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 微信测试公众号
     * TEST_APP_ID=wx51818aa8a91ca10b
     * TEST_SECRET=6ecc7b0fbb7656834863eca9175c2b98
     */
    public static final String TEST_APP_ID = properties.getProperty("TEST_APP_ID");
    public static final String TEST_SECRET = properties.getProperty("TEST_SECRET");

    public static final String APP_ID = properties.getProperty("APP_ID");
    public static final String SECRET = properties.getProperty("SECRET");

    public static final String OPEN_ID = properties.getProperty("SUN_HAO_OPEN_ID");

    public static String getProperty(String key){
        return properties.getProperty(key);
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

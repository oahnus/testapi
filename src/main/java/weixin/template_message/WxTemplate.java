package weixin.template_message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oahnus on 2017/4/21
 * 10:06.
 */
public abstract class WxTemplate {
    private String touser = "";
    private String template_id = "";
    private String url = "";
    private String topcolor = "";

    public Map data = new HashMap();

    public boolean send(String token){
        try {
            String postUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + token;
            URL url = new URL(postUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setRequestProperty("Accept", "application/json");

            try (OutputStream out = connection.getOutputStream()) {
                out.write(toJson().getBytes());
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
System.out.println(stringBuilder.toString());
            return errcode == 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkData(){
        return !isEmpty(touser) || !isEmpty(template_id);
    }

    public WxTemplate addData(String name, WxData wxData){
        this.data.put(name, wxData);
        return this;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

    private boolean isEmpty(String s) {
        return s == null || s.equals("");
    }

    public WxTemplate setTouser(String touser) {
        this.touser = touser;
        return this;
    }

    public WxTemplate setTemplate_id(String template_id) {
        this.template_id = template_id;
        return this;
    }

    public WxTemplate setUrl(String url) {
        this.url = url;
        return this;
    }

    public WxTemplate setTopcolor(String topcolor) {
        this.topcolor = topcolor;
        return this;
    }

    public WxTemplate setData(Map data) {
        this.data = data;
        return this;
    }

    public String getTouser() { return touser; }
    public String getTemplate_id() { return template_id; }
    public String getUrl() { return url; }
    public String getTopcolor() { return topcolor; }
    public Map getData() { return data; }
}

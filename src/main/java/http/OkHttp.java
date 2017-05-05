package http;

import okhttp3.*;

import java.io.IOException;

/**
 * Created by oahnus on 2017/5/3
 * 10:06.
 */
public class OkHttp {
    public static void main(String... args) throws IOException {
//        getMethod();
        postMethod();
    }

    private static void postMethod() throws IOException {
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json());
        Request request = new Request.Builder()
                .url("http://139.129.49.14:4567/shixun/api/students")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }

    public static String json() {
        return "{\n" +
                "\"studentNum\": \"1341902103\",\n" +
                "\"name\": \"孙尚香\",\n" +
                "\"sex\": \"女\",\n" +
                "\"profession\": \"通信工程\",\n" +
                "\"depart\": \"计算机科学与工程\",\n" +
                "\"email\": \"443558534sw4@qq.com\"\n" +
                "}";
    }

    private static void getMethod() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://139.129.49.14:4567/shixun/api/students?page=1&limit=5")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}

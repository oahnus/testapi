package http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by oahnus on 2017/5/3
 * 10:38.
 */
public class RetroFitHttp {
    public static void main(String... args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8084/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> callResponse = apiService.fetchAccessTokenFromMessageCenter();
        ResponseBody responseData =  callResponse.execute().body();
        System.out.println(responseData.string());
//        ArrayList list = responseData
    }
}

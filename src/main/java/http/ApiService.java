package http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import java.util.List;

/**
 * Created by oahnus on 2017/5/3
 * 10:32.
 */
public interface ApiService {
    @GET("/notify/wx/token")
    @Headers({"AUTH_KEY: h8aKtn23k09pb0Q", "Content-type: application/json;charset=utf-8", "Accept: application/json"})
    Call<ResponseBody> fetchAccessTokenFromMessageCenter();
}

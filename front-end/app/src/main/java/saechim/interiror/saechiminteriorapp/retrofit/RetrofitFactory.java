package saechim.interiror.saechiminteriorapp.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    public static String ServerUrl;
    public static String jwtToken;

    public static RetrofitService create(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(ServerUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(RetrofitService.class);
    }



}

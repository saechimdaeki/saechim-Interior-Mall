package saechim.interiror.saechiminteriorapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    public static String ServerUrl;
    public static RetrofitService create(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(ServerUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(RetrofitService.class);
    }
}

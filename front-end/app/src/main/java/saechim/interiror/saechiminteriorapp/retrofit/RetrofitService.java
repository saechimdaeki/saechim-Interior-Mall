package saechim.interiror.saechiminteriorapp.retrofit;

import androidx.annotation.Nullable;

import java.security.Principal;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import saechim.interiror.saechiminteriorapp.dto.LoginDto;
import saechim.interiror.saechiminteriorapp.model.User;

public interface RetrofitService {
    @GET("/userservice/users")
    Call<List<User>> getAllUsersInfo();

    @POST("/userservice/login")
    Call<Void> Login(@Body LoginDto loginDto);
}

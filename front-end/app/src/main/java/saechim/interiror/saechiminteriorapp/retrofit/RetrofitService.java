package saechim.interiror.saechiminteriorapp.retrofit;

import androidx.annotation.Nullable;

import java.security.Principal;
import java.util.List;

import lombok.Getter;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import saechim.interiror.saechiminteriorapp.dto.LoginDto;
import saechim.interiror.saechiminteriorapp.model.Coupon;
import saechim.interiror.saechiminteriorapp.model.User;
import saechim.interiror.saechiminteriorapp.model.UserResponseDto;

public interface RetrofitService {

    @GET("/userservice/users")
    Call<List<User>> getAllUsersInfo(@Header("Authorization")String jwt);

    @POST("/userservice/login")
    Call<Void> Login(@Body LoginDto loginDto);

    @GET("/userservice/user/{userId}")
    Call<UserResponseDto> getUserInfoByUserID(@Header("Authorization")String jwt, @Path("userId") String userId);

    @GET("/userservice/{userId}/coupons")
    Call<List<Coupon>> getCouponsByUserId(@Header("Authorization")String jwt, @Path("userId") String userId);
}

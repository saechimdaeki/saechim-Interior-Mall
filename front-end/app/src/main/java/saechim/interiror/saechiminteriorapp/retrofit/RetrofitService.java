package saechim.interiror.saechiminteriorapp.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import saechim.interiror.saechiminteriorapp.model.User;

public interface RetrofitService {
    @GET("/userservice/users")
    Call<List<User>> getAllUsersInfo();
}

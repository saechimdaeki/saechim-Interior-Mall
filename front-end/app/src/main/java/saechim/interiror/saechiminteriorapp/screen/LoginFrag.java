package saechim.interiror.saechiminteriorapp.screen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dd.processbutton.iml.ActionProcessButton;

import java.security.Principal;

import lombok.SneakyThrows;
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saechim.interiror.saechiminteriorapp.R;
import saechim.interiror.saechiminteriorapp.dto.LoginDto;
import saechim.interiror.saechiminteriorapp.retrofit.RetrofitFactory;
import saechim.interiror.saechiminteriorapp.retrofit.RetrofitService;

public class LoginFrag extends Fragment {
    private ActionProcessButton btsLogin;
    private EditText loginText;
    private EditText pwdText;
    private RetrofitService retrofitService;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.loginfragment,container,false);
        btsLogin=view.findViewById(R.id.btnLogIn);
        loginText=view.findViewById(R.id.edit_login);
        pwdText=view.findViewById(R.id.edit_pwd);
        retrofitService= RetrofitFactory.create();
        btsLogin.setOnClickListener(v -> {
            btsLogin.setMode(ActionProcessButton.Mode.PROGRESS);
            btsLogin.setProgress(0);
            login(loginText.getText().toString(), pwdText.getText().toString());
        });

        return view;

    }
    private void login(String id, String pwd){
        Log.e("하이 id", id);
        Log.e("하이 pwd", pwd);
        LoginDto loginDto=new LoginDto(id,pwd);
        retrofitService.Login(loginDto).enqueue(new Callback<Void>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                btsLogin.setMode(ActionProcessButton.Mode.ENDLESS);
                btsLogin.setProgress(50);
                Headers headers = response.headers();
                String jwttoken = headers.get("jwttoken");
                Log.e("jwt",jwttoken);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("오류",call.request().url().toString());

            }
        });
    }
}

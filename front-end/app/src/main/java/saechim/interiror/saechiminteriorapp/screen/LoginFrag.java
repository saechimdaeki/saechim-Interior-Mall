package saechim.interiror.saechiminteriorapp.screen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dd.processbutton.iml.ActionProcessButton;

import java.security.Principal;
import java.util.List;

import lombok.SneakyThrows;
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saechim.interiror.saechiminteriorapp.MainActivity;
import saechim.interiror.saechiminteriorapp.R;
import saechim.interiror.saechiminteriorapp.dto.LoginDto;
import saechim.interiror.saechiminteriorapp.model.User;
import saechim.interiror.saechiminteriorapp.retrofit.RetrofitFactory;
import saechim.interiror.saechiminteriorapp.retrofit.RetrofitService;

public class LoginFrag extends Fragment {
    private ActionProcessButton btsLogin;
    private EditText loginText;
    private EditText pwdText;
    private RetrofitService retrofitService;
    private TextView findIdTextView;
    private TextView resetPwdTextView;
    private TextView createUserTextView;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.loginfragment,container,false);
        pref=getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        editor=pref.edit();

        btsLogin=view.findViewById(R.id.btnLogIn);
        loginText=view.findViewById(R.id.edit_login);
        pwdText=view.findViewById(R.id.edit_pwd);
        findIdTextView=view.findViewById(R.id.findId);
        resetPwdTextView=view.findViewById(R.id.resetpassword);
        createUserTextView=view.findViewById(R.id.createuser);

        retrofitService= RetrofitFactory.create();
        btsLogin.setOnClickListener(v -> {
            btsLogin.setMode(ActionProcessButton.Mode.PROGRESS);
            btsLogin.setProgress(0);
            login(loginText.getText().toString(), pwdText.getText().toString());
        });

        /* retrofit2 테스트. */
        findIdTextView.setOnClickListener(v -> retrofitService.getAllUsersInfo(RetrofitFactory.jwtToken).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful()){
                    List<User> body = response.body();
                    for (User user : body) {
                        Log.e("성공", user.getUserId());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("오류",call.request().url().toString());

            }
        }));

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
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), "후후 로그인 실패입니다만? ", Toast.LENGTH_SHORT).show();
                    btsLogin.setMode(ActionProcessButton.Mode.PROGRESS);
                }else {
                    Headers headers = response.headers();
                    RetrofitFactory.jwtToken = headers.get("jwttoken");
                    editor.putString("id",id);
                    editor.apply();
                    MainActivity.isLogin=true;
                    ((MainActivity)getActivity()).setFrame(2);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("오류",call.request().url().toString());

            }
        });
    }
}

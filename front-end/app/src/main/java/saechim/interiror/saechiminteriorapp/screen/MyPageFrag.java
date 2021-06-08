package saechim.interiror.saechiminteriorapp.screen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saechim.interiror.saechiminteriorapp.R;
import saechim.interiror.saechiminteriorapp.model.Coupon;
import saechim.interiror.saechiminteriorapp.model.UserResponseDto;
import saechim.interiror.saechiminteriorapp.retrofit.RetrofitFactory;
import saechim.interiror.saechiminteriorapp.retrofit.RetrofitService;

public class MyPageFrag extends Fragment {
    private CircleImageView userProfileImage;
    private TextView idTextView;
    private Button editProfileButton;
    private TextView couponCountTextView;
    private RetrofitService retrofitService;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String id;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mypagefragment,container,false);
        userProfileImage=view.findViewById(R.id.userprofileimage);
        idTextView=view.findViewById(R.id.textId);
        editProfileButton=view.findViewById(R.id.profileEditButton);
        couponCountTextView=view.findViewById(R.id.couponCount);
        pref=getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        editor=pref.edit();
        id=pref.getString("id","nono_id");
        retrofitService= RetrofitFactory.create();

        retrofitService.getUserInfoByUserID(RetrofitFactory.jwtToken,id).enqueue(new Callback<UserResponseDto>() {
            @Override
            public void onResponse(Call<UserResponseDto> call, Response<UserResponseDto> response) {
                UserResponseDto body = response.body();
                byte[] imageByte = Base64.decode(body.getUserImage(),Base64.DEFAULT);
                idTextView.setText(body.getUserId());
                Glide.with(getActivity()).load(imageByte).into(userProfileImage);
            }

            @Override
            public void onFailure(Call<UserResponseDto> call, Throwable t) {
                Log.e("오류",call.request().url().toString());

            }
        });


        retrofitService.getCouponsByUserId(RetrofitFactory.jwtToken,id).enqueue(new Callback<List<Coupon>>() {
            @Override
            public void onResponse(Call<List<Coupon>> call, Response<List<Coupon>> response) {
                if(response.isSuccessful()){
                    List<Coupon> body = response.body();
                    couponCountTextView.setText(String.valueOf(body.size()));
                    //couponCountTextView.setText(response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Coupon>> call, Throwable t) {
                Log.e("오류",call.request().url().toString());
                Toast.makeText(getActivity(), "통신 실패", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }
}

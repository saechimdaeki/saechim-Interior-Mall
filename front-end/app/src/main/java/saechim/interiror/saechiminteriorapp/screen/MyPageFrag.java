package saechim.interiror.saechiminteriorapp.screen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saechim.interiror.saechiminteriorapp.R;
import saechim.interiror.saechiminteriorapp.adapter.MyStoryAdapter;
import saechim.interiror.saechiminteriorapp.dto.ResponseMyPostDto;
import saechim.interiror.saechiminteriorapp.model.Coupon;
import saechim.interiror.saechiminteriorapp.model.UserResponseDto;
import saechim.interiror.saechiminteriorapp.retrofit.RetrofitFactory;
import saechim.interiror.saechiminteriorapp.retrofit.RetrofitService;

public class MyPageFrag extends Fragment {
    private CircleImageView userProfileImage;
    private TextView idTextView;
    private Button editProfileButton;
    private RecyclerView myPostRecyclerView;
    private TextView couponCountTextView;
    private RetrofitService retrofitService;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    LinearLayoutManager linearLayoutManager;
    MyStoryAdapter myStoryAdapter=new MyStoryAdapter();
    String id;
    LottieAnimationView lottie;
    @Override
    public void onAttach(@NonNull Context context) {

        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mypagefragment,container,false);
        lottie = view.findViewById(R.id.lottie);
        lottie.playAnimation();
        lottie.loop(true);
        startLoading();

        userProfileImage=view.findViewById(R.id.userprofileimage);
        idTextView=view.findViewById(R.id.textId);
        editProfileButton=view.findViewById(R.id.profileEditButton);
        couponCountTextView=view.findViewById(R.id.couponCount);
        myPostRecyclerView=view.findViewById(R.id.myposts);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        myPostRecyclerView.setHasFixedSize(true);
        myPostRecyclerView.setLayoutManager(linearLayoutManager);
        myPostRecyclerView.setAdapter(myStoryAdapter);

        pref=getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        editor=pref.edit();
        id=pref.getString("id",null);
        retrofitService = RetrofitFactory.create();
        new GetCoupons().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
        new GetUserInfo().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
        new getMyStory().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR,"");
        return view;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private class GetUserInfo extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            retrofitService.getUserInfoByUserID(RetrofitFactory.jwtToken, id).enqueue(new Callback<UserResponseDto>() {
                @Override
                public void onResponse(Call<UserResponseDto> call, Response<UserResponseDto> response) {
                    UserResponseDto body = response.body();

                    byte[] imageByte = Objects.equals(body.getUserImage(), null) ? null :
                            Base64.decode(body.getUserImage(), Base64.DEFAULT);
                    if(body.getUserImage() != null) {
                        editor.putString("userImage", body.getUserImage());
                        editor.apply();
                    }
                    idTextView.setText(body.getUserId());
                    Glide.with(getActivity()).load(imageByte)
                            .error(R.drawable.kinopico).into(userProfileImage);

                }
                @Override
                public void onFailure(Call<UserResponseDto> call, Throwable t) {
                    Log.e("오류", call.request().url().toString());
                }
            });
            return null;
        }
    }

    private class GetCoupons extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... strings) {
            retrofitService.getCouponsByUserId(RetrofitFactory.jwtToken, id).enqueue(new Callback<List<Coupon>>() {
                @Override
                public void onResponse(Call<List<Coupon>> call, Response<List<Coupon>> response) {
                    if (response.isSuccessful()) {
                        List<Coupon> body = response.body();
                        couponCountTextView.setText(String.valueOf(body.size()));
                        editor.putString("coupon",couponCountTextView.getText().toString());
                    }
                }

                @Override
                public void onFailure(Call<List<Coupon>> call, Throwable t) {
                    Log.e("오류", call.request().url().toString());
                    Toast.makeText(getActivity(), "통신 실패", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }
    }

    private class getMyStory extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            retrofitService.getUsersPost(RetrofitFactory.jwtToken,id).enqueue(new Callback<List<ResponseMyPostDto>>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<List<ResponseMyPostDto>> call, Response<List<ResponseMyPostDto>> response) {
                    if (response.isSuccessful()) {
                        List<ResponseMyPostDto> body = response.body();
                        for (ResponseMyPostDto responseMyPostDto : body) {
                            myStoryAdapter.addItem(responseMyPostDto);
                            myStoryAdapter.notifyDataSetChanged();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<ResponseMyPostDto>> call, Throwable t) {
                    Log.e("오류", call.request().url().toString());
                    Toast.makeText(getActivity(), "통신 실패 getMyStory", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(() -> lottie.setVisibility(View.GONE), 600);
    }

}

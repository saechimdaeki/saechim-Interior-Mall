package saechim.interiror.saechiminteriorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saechim.interiror.saechiminteriorapp.model.User;
import saechim.interiror.saechiminteriorapp.retrofit.RetrofitFactory;
import saechim.interiror.saechiminteriorapp.retrofit.RetrofitService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RetrofitFactory.ServerUrl=getString(R.string.Ipaddress);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView=findViewById(R.id.textView);
        ImageView image=findViewById(R.id.imageView);
        RetrofitService retrofitService=RetrofitFactory.create();
        retrofitService.getAllUsersInfo().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> model=response.body();
                User user = model.get(0);
                textView.setText(user.getUserId());
                byte[] imageByte= Base64.decode(model.get(0).getUserPic(),Base64.DEFAULT);
                InputStream is=new ByteArrayInputStream(imageByte);
                Bitmap bitmap= BitmapFactory.decodeStream(is);
                image.setImageBitmap(bitmap);

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("오류",call.request().url().toString());
            }
        });
    }
}
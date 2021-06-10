package saechim.interiror.saechiminteriorapp.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

import saechim.interiror.saechiminteriorapp.R;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        LottieAnimationView lottie = findViewById(R.id.lottie);
        lottie.playAnimation();
        lottie.loop(true);
        startLoading();
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(this::finish, 550);
    }
}
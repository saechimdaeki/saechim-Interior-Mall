package saechim.interiror.saechiminteriorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saechim.interiror.saechiminteriorapp.model.User;
import saechim.interiror.saechiminteriorapp.retrofit.RetrofitFactory;
import saechim.interiror.saechiminteriorapp.retrofit.RetrofitService;
import saechim.interiror.saechiminteriorapp.screen.CategoryFrag;
import saechim.interiror.saechiminteriorapp.screen.EtcFrag;
import saechim.interiror.saechiminteriorapp.screen.HomeFrag;
import saechim.interiror.saechiminteriorapp.screen.LoginFrag;
import saechim.interiror.saechiminteriorapp.screen.MyPageFrag;
import saechim.interiror.saechiminteriorapp.screen.StoryFrag;

public class MainActivity extends AppCompatActivity {
    public static boolean isLogin=false;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private HomeFrag homeFrag;
    private CategoryFrag categoryFrag;
    private MyPageFrag myPageFrag;
    private StoryFrag storyFrag;
    private EtcFrag etcFrag;
    private LoginFrag loginFrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RetrofitFactory.ServerUrl=getString(R.string.Ipaddress);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomNavi);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_home:
                    setFrame(0);
                    break;
                case R.id.action_category:
                    setFrame(1);
                    break;
                case R.id.action_mypage:
                    setFrame(!isLogin ? 5 : 2);
                    break;
                case R.id.action_story:
                    setFrame(3);
                    break;
                case R.id.action_etc:
                    setFrame(4);
                    break;
            }
            return true;
        });
        homeFrag=new HomeFrag();
        categoryFrag=new CategoryFrag();
        myPageFrag=new MyPageFrag();
        storyFrag=new StoryFrag();
        etcFrag=new EtcFrag();
        loginFrag=new LoginFrag();

       setFrame(0);

    }
    public void setFrame(int n){
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();

        switch (n){
            case 0:
                ft.replace(R.id.main_frame,homeFrag);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame,categoryFrag);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame,myPageFrag);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame,storyFrag);
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.main_frame,etcFrag);
                ft.commit();
                break;
            case 5:
                ft.replace(R.id.main_frame, loginFrag);
                ft.commit();
                break;
        }
    }


}
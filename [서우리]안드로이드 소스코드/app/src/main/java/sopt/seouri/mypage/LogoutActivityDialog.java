package sopt.seouri.mypage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.tsengvn.typekit.TypekitContextWrapper;

import sopt.seouri.R;
import sopt.seouri.SharedPrefrernceController;
import sopt.seouri.login.LoginActivity;
import sopt.seouri.network.NetworkService;

import static sopt.seouri.MainActivity.mainActivity;

/**
 * Created by ash on 2017-08-27.
 */

public class LogoutActivityDialog extends AppCompatActivity{

    NetworkService service;
    ImageView imageView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.logout_dialog);
        imageView = (ImageView)findViewById(R.id.logout_btn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                mainActivity.finish();
                SharedPrefrernceController.setUserId(getApplicationContext(),"");
                SharedPrefrernceController.setServertoken(getApplicationContext(),"");
                startActivity(intent);
                finish();

            }
        });
    }
}

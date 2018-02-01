package sopt.seouri.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sopt.seouri.MainActivity;
import sopt.seouri.R;
import sopt.seouri.SharedPrefrernceController;
import sopt.seouri.application.ApplicationController;
import sopt.seouri.network.NetworkService;

public class SplashActivity extends AppCompatActivity {

    NetworkService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        service = ApplicationController.getInstance().getNetworkService();
        AutoLogin();
    }

    public void AutoLogin() {
        //// TODO: 2017-10-18 카카오가 깔려있지 않은 디바이스라면 밑에를 주석처리하고 밑에 주석을 살리면됩니다.
        if (SharedPrefrernceController.getUserId(SplashActivity.this).equals("")) {
            Log.d("ash", "here??");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }, 3000);
        } else {
            Log.d("ash", "here???");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String userId = SharedPrefrernceController.getUserId(getApplicationContext());
                    ApplicationController.memberId = userId;
                    Log.d("ash", "userId " + userId);
                    Call<LoginResult> loginResultCall = service.getLoginResult(new LoginData(userId)); //
                    loginResultCall.enqueue(new Callback<LoginResult>() {
                        @Override
                        public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                            if (response.isSuccessful()) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                ApplicationController.memberImg = response.body().userInfo.profile;
                                ApplicationController.memberName = response.body().userInfo.name;
                                ApplicationController.serverToken = response.body().token;
                                Log.d("ash", "serverToken in login" + response.body().token);
                                finish();
                                startActivity(intent);

                            } else {
                                Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                                toast.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResult> call, Throwable t) {
                            Toast toast = Toast.makeText(getApplicationContext(), "네트워크 상태를 확인하세요", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    });
                }
            }, 3000);

        }
    }
}


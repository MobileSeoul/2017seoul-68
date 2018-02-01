package sopt.seouri.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.kakao.auth.ErrorCode;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.helper.log.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sopt.seouri.MainActivity;
import sopt.seouri.SharedPrefrernceController;
import sopt.seouri.application.ApplicationController;
import sopt.seouri.network.NetworkService;

/**
 * Created by ash on 2017-09-27.
 */

public class KakaoSignupActivity extends Activity {
    NetworkService service;

    /**
     * Main으로 넘길지 가입 페이지를 그릴지 판단하기 위해 me를 호출한다.
     *
     * @param savedInstanceState 기존 session 정보가 저장된 객체
     */

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = ApplicationController.getInstance().getNetworkService();
        requestMe();
    }

    /**
     * 사용자의 상태를 알아 보기 위해 me API 호출을 한다.
     */
    protected void requestMe() { //유저의 정보를 받아오는 함수
        UserManagement.requestMe(new MeResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);

                ErrorCode result = ErrorCode.valueOf(errorResult.getErrorCode());
                if (result == ErrorCode.CLIENT_ERROR_CODE) {
                    finish();
                } else {
                    redirectLoginActivity();
                }
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                redirectLoginActivity();
            }

            @Override
            public void onNotSignedUp() {
            } // 카카오톡 회원이 아닐 시 showSignup(); 호출해야함

            @Override
            public void onSuccess(UserProfile userProfile) {  //성공 시 userProfile 형태로 반환
                Logger.d("UserProfile : " + userProfile);
                final String kakaoID = String.valueOf(userProfile.getId()); // userProfile에서 ID값을 가져옴
                final String kakaoNickname = userProfile.getNickname();     // Nickname 값을 가져옴
                final String kakaoEmail = userProfile.getEmail();
                String kakaoUserProfile = "";
                if (userProfile.getProfileImagePath() != null)
                    kakaoUserProfile = userProfile.getProfileImagePath();
                Log.d("ash",userProfile.toString());
                Log.d("ash", String.valueOf(userProfile.getId()));
                String token = FirebaseInstanceId.getInstance().getToken();
                Log.d("ash", "fire base token " + token);
                Call<SignupResult> signupResultCall = service.getsignupResult(new SignupData(kakaoID, kakaoNickname, kakaoUserProfile,Integer.parseInt(kakaoID),token));
                final String finalKakaoUserProfile = kakaoUserProfile;
                signupResultCall.enqueue(new Callback<SignupResult>() {
                    @Override
                    public void onResponse(Call<SignupResult> call, Response<SignupResult> response) {
                        if (response.isSuccessful()) {
                            if(response.body().message.equals("Succeed in inserting memberInfo.")){
                                ApplicationController.memberId = kakaoID;
                                ApplicationController.memberImg = finalKakaoUserProfile;
                                ApplicationController.memberName = kakaoNickname;
                                SharedPrefrernceController.setUserId(getApplicationContext(),kakaoID);
                                SharedPrefrernceController.setServertoken(getApplicationContext(),response.body().token);
                                ApplicationController.serverToken = response.body().token;
                                Log.d("ash","sign up responce"+response.body().token);
                                redirectMainActivity(); // 로그인 성공시 MainActivity로
                            }else{
                                Log.d("ash",response.body().message);
                            }

                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SignupResult> call, Throwable t) {
                        Toast toast = Toast.makeText(getApplicationContext(), "네트워크 상태를 확인하세요", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });

            }
        });
    }

    private void redirectMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    protected void redirectLoginActivity() {
        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
        finish();
    }

}

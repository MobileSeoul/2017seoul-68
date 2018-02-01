package sopt.seouri.application;

import android.app.Activity;
import android.app.Application;

import com.kakao.auth.KakaoSDK;
import com.tsengvn.typekit.Typekit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sopt.seouri.login.KakaoSDKAdapter;
import sopt.seouri.network.NetworkService;

/**
 * Created by 김지원 on 2017-10-05.
 */

public class ApplicationController extends Application {
    private static ApplicationController instance;
    private static volatile Activity currentActivity = null;
    public static ApplicationController getInstance(){
        return instance;
    }
    private static String baseUrl = "http://52.78.119.54:3000/";
    private NetworkService networkService;
    public NetworkService getNetworkService(){
        return networkService;
    }

    public static String memberId;
    public static String memberName;
    public static String memberImg;
    public static String serverToken;

    @Override
    public void onCreate() {
        super.onCreate();
        // 폰트 바꿀 때 주석 풀고 내용 변경할 것
               Typekit.getInstance()
                       .addNormal(Typekit.createFromAsset(this,"OPENAS_bbTreeGodik_R_TTF.ttf"))
                        .addBold(Typekit.createFromAsset(this,"OPENAS_bbTreeGodik_B_TTF.ttf"))
                       .add("custom1",Typekit.createFromAsset(this,"OPENAS_bbTreeGodik_L_TTF.ttf"));
                       //.addCustom2(Typekit.createFromAsset(this,"NanumMyeongjo.ttc"))
                       //.addCustom3(Typekit.createFromAsset(this,"NanumMyeongjoExtraBold.ttf"))
                       //.addCustom4(Typekit.createFromAsset(this,"NanumSquareR.ttf"));

        ApplicationController.instance = this;
        KakaoSDK.init(new KakaoSDKAdapter());

        buildService();       //통신소스 완료 후 주석풀자.
    }

    public void buildService(){
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();

        networkService = retrofit.create(NetworkService.class);
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        ApplicationController.currentActivity = currentActivity;
    }

    /**
     * singleton 애플리케이션 객체를 얻는다.
     * @return singleton 애플리케이션 객체
     */
    public static ApplicationController getGlobalApplicationContext() {
        if(instance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return instance;
    }

    /**
     * 애플리케이션 종료시 singleton 어플리케이션 객체 초기화한다.
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }

}

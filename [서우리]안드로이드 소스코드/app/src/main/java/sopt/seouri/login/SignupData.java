package sopt.seouri.login;

/**
 * Created by ash on 2017-10-07.
 */

public class SignupData {
    public String userId;
    public String name;
    public String profile;
    public int kakaoToken;
    public String deviceToken;

    public SignupData(String userId, String name, String profile, int kakaoToken, String deviceToken) {
        this.userId = userId;
        this.name = name;
        this.profile = profile;
        this.kakaoToken = kakaoToken;
        this.deviceToken = deviceToken;
    }
}

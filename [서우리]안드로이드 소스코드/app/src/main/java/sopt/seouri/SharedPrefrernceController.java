package sopt.seouri;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ash on 2017-10-07.
 */

public class SharedPrefrernceController {
    public static final String UERID = "userID";
    public static final String SERVERTOKEN = "serverToken";
    private static final String USER = "user";

    public static void setUserId(Context context,String userID){
        SharedPreferences pref = context.getSharedPreferences(USER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(UERID,userID);
        editor.commit();
    }
    public static String getUserId(Context context){
        SharedPreferences pref = context.getSharedPreferences(USER, context.MODE_PRIVATE);
        return pref.getString(UERID,"");
    }
    public static void setServertoken(Context context,String serverToekn){
        SharedPreferences pref = context.getSharedPreferences(USER,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(SERVERTOKEN,serverToekn);
        editor.commit();
    }
    public static String getServertoken(Context context){
        SharedPreferences pref = context.getSharedPreferences(USER, context.MODE_PRIVATE);
        return pref.getString(SERVERTOKEN,"");
    }

}

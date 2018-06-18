package sstorage.mobile.senai.com.sstorage.utils;

import android.content.SharedPreferences;

import sstorage.mobile.senai.com.sstorage.model.ViewModel.Authentication;

public class AppUtils {

    public static final String API_ROOT = "http://10.0.3.2:50939/rest/v1/";

    public static final String SHARED_PREFERENCES = "SStorage";

    public static final String SP_TOKEN = "Token";
    public static final String SP_USERNAME = "UserName";
    public static final String SP_USERLASTNAME = "UserLastName";
    public static final String SP_USERID = "UserId";
    public static final String SP_USERTYPE = "UserType";
    //
    public static final String INT_PATNAME = "PatrimonyName";
    public static final String INT_PATID = "PatrimonyId";
    public static final String INT_PATITEMID = "PatrimonyItemId";
    public static final String INT_ENVID = "EnvironmentId";

    public static void login(SharedPreferences.Editor editor, Authentication auth) {
        editor.putString(AppUtils.SP_TOKEN, "Bearer " + auth.getToken());
        editor.putString(AppUtils.SP_USERNAME, auth.getUserName());
        editor.putString(AppUtils.SP_USERLASTNAME, auth.getUserLastName());
        editor.putLong(AppUtils.SP_USERID, auth.getUserId());
        editor.putInt(AppUtils.SP_USERTYPE, auth.getUserType());
        editor.apply();
        editor.commit();
    }

    public static void logout(SharedPreferences.Editor editor) {
        editor.remove(AppUtils.SP_TOKEN);
        editor.remove(AppUtils.SP_USERNAME);
        editor.remove(AppUtils.SP_USERLASTNAME);
        editor.remove(AppUtils.SP_USERID);
        editor.remove(AppUtils.SP_USERTYPE);
        editor.apply();
        editor.commit();
    }

}

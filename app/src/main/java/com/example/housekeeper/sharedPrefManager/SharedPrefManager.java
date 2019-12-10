package com.example.housekeeper.sharedPrefManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.housekeeper.activity.GetNumberActivity;
import com.example.housekeeper.activity.MainActivity;
import com.example.housekeeper.model.ModelHotels;
import com.example.housekeeper.model.ModelLogin;
import com.example.housekeeper.model.ModelPhoneLanguage;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "housekeepersharedpref";
    private static final String KEY_ACCESSTOKEN = "keyaccesstoken";
    private static final String KEY_VERIFICATIONCODE = "keyverificationcode";
    private static final String KEY_ORGANIZATIONID = "keyorganizationid";
    private static final String KEY_USERID = "keyusernid";
    private static final String KEY_ORGANIZATIONCAPTION = "keyorganizationcaption";
    private static final String KEY_ISERROR = "keyerror";

    private static final String KEY_HOTELID = "keyhotelid";
    private static final String KEY_HOTELADDRESS = "keyhotelid";
    private static final String KEY_HOTELCAPTION = "keyhotelid";

    private static final String KEY_PHONE = "keyphoneno";
    private static final String KEY_LANGUAGE = "keylanguage";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(ModelLogin modelLogin) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_ACCESSTOKEN, modelLogin.getAccessToken());
        editor.putString(KEY_VERIFICATIONCODE, modelLogin.getVerificationCode());
        editor.putInt(KEY_ORGANIZATIONID, modelLogin.getOrganizationId());
        editor.putInt(KEY_USERID, modelLogin.getUserId());
        editor.putString(KEY_ORGANIZATIONCAPTION, modelLogin.getOrganizationCaption());
        editor.putBoolean(KEY_ISERROR, modelLogin.isError());

        editor.apply();
    }

    //this method will give the logged in user
    public ModelLogin getLogin() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new ModelLogin(

                sharedPreferences.getString(KEY_ACCESSTOKEN, null),
                sharedPreferences.getString(KEY_VERIFICATIONCODE, null),
                sharedPreferences.getInt(KEY_ORGANIZATIONID, -1),
                sharedPreferences.getInt(KEY_USERID, -1),
                sharedPreferences.getString(KEY_ORGANIZATIONCAPTION, null),
                sharedPreferences.getBoolean(KEY_ISERROR, true)
        );

    }

    //This method will triggered when hotel selected
    public void hotelDetails(ModelHotels modelHotels){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_HOTELCAPTION, modelHotels.getName());
        editor.putString(KEY_HOTELADDRESS, modelHotels.getAddress());
        editor.putString(KEY_HOTELID, modelHotels.getHotelId());

        editor.apply();

    }

    // This method is for getting hotel information
    public ModelHotels getHotel() {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new ModelHotels(

                sharedPreferences.getString(KEY_HOTELCAPTION, null),
                sharedPreferences.getString(KEY_HOTELADDRESS, null),
                sharedPreferences.getString(KEY_HOTELID, null)

        );
    }

    //This method will store phone number
//    public String getLanguage() {
//                SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return new
//
//                sharedPreferences.getString(KEY_PHONE, null ),
//                sharedPreferences.getString(KEY_LANGUAGE, null)
//
//        );
//    }

    public void setLanguage(String language) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_LANGUAGE, language);
        editor.apply();

    }

//    public void phoneAndLanguage(ModelPhoneLanguage modelPhoneLanguage) {
//
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString(KEY_PHONE, modelPhoneLanguage.getPhone());
//        editor.putString(KEY_LANGUAGE, modelPhoneLanguage.getLanguage());
//
//        editor.apply();
//
//    }

    //This method for getthin phone and languages
//    public ModelPhoneLanguage getPhoneAndLanguage() {
//
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        return new ModelPhoneLanguage(
//
//                sharedPreferences.getString(KEY_PHONE, null ),
//                sharedPreferences.getString(KEY_LANGUAGE, null)
//
//        );
//
//    }


    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(KEY_USERID) ) {
            return true;
        }else {
            return false;
        }

    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, GetNumberActivity.class));
    }

}

package com.example.housekeeper.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.housekeeper.R;
import com.example.housekeeper.api.RetrofitClient;
import com.example.housekeeper.model.ModelHotels;
import com.example.housekeeper.model.ModelLogin;
import com.example.housekeeper.model.ModelPhoneLanguage;
import com.example.housekeeper.sharedPrefManager.SharedPrefManager;
import com.example.housekeeper.utils.Data;
import com.hbb20.CountryCodePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNumberActivity extends AppCompatActivity {

    private CountryCodePicker ccp;
    private TextView mTitle;
    private EditText edPhone;
    private Button mContinueBtn;

    private String fullPhoneNo;
    private String language;

    private SharedPreferences loginPrefs;
    private static final String LOGIN_KEY = "loginKey";
    private AppCompatActivity mContext;
//    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_number);
        uiInitialize();
    }

    private void uiInitialize() {
        ccp = findViewById(R.id.ccp);
        edPhone = findViewById(R.id.ed_phone);
        mContinueBtn = findViewById(R.id.btn_continue);
        mTitle = findViewById(R.id.textTitle);

        mContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                continueSignIn();
                signIn();
            }
        });
    }

    private void signIn() {
        String countryCode = ccp.getSelectedCountryCode().trim();
        String phoneNo = edPhone.getText().toString().trim();
        fullPhoneNo = countryCode + phoneNo;
        language = "en";
        Data.hotelsList = new ArrayList<>();

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .loginWithMobile(fullPhoneNo, language);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = null;

                try {
                    s = response.body().string();
//                    Toast.makeText(GetNumberActivity.this, s, Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (s != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);

                        Boolean isError = jsonObject.getBoolean("isError");

                        if (!isError) {
                            String accessToken = jsonObject.getString("accessToken");
                            String verificationCode = jsonObject.getString("verificationCode");
                            JSONArray hotellistObj = jsonObject.getJSONArray("hotelList");

                            ModelLogin modelLogin = new ModelLogin(
                                    jsonObject.getString("accessToken"),
                                    jsonObject.getString("verificationCode"),
                                    jsonObject.getInt("organizationId"),
                                    jsonObject.getInt("userId"),
                                    jsonObject.getString("organizationCaption"),
                                    fullPhoneNo,
                                    jsonObject.getBoolean("isError")
                            );

                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(modelLogin);

                            for (int i = 0; i < hotellistObj.length(); i++) {
                                JSONObject object = hotellistObj.getJSONObject(i);

                                String hoteName = object.getString("hotelCaption").trim();
                                String hotelAddress = object.getString("address").trim();
                                String hotelId = object.getString("hotelId").trim();

                                Log.d("hotelCaption", hoteName);

                                ModelHotels modelHotels = new ModelHotels(hoteName, hotelAddress, hotelId);
                                SharedPrefManager.getInstance(mContext).hotelDetails(modelHotels);
                                Data.hotelsList.add(modelHotels);
                            }

                            int organizationId = jsonObject.getInt("organizationId");
                            int userId = jsonObject.getInt("userId");
                            String organizationCaption = jsonObject.getString("organizationCaption");
                            // Boolean isError = jsonObject.getBoolean("isError");

                            Intent intent = new Intent(GetNumberActivity.this, MobileCodeActivity.class);
                            intent.putExtra("Verification Code", verificationCode);
                            intent.putExtra("Access Token", accessToken);
                            intent.putExtra("Mobile NO", fullPhoneNo);
                            intent.putExtra("Organization ID", organizationId);
                            intent.putExtra("User ID", userId);
                            intent.putExtra("Organization Caption", organizationCaption);
                            intent.putExtra("Is Error", isError);

                            // Putting phone and language into sharedpreferense
                            ModelPhoneLanguage modelPhoneLanguage = new ModelPhoneLanguage(fullPhoneNo, language);
                            SharedPrefManager.getInstance(mContext).phoneAndLanguage(modelPhoneLanguage);
                            startActivity(intent);
                            finish();
                        } else {
                            String message = jsonObject.getString("message");
                            Toast.makeText(GetNumberActivity.this, message, Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(GetNumberActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

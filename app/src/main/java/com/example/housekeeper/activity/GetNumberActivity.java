package com.example.housekeeper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.housekeeper.R;
import com.example.housekeeper.api.URLs;
import com.example.housekeeper.api.VolleySingleton;
import com.example.housekeeper.custom.MultiLanguage;
import com.example.housekeeper.model.ModelPhoneLanguage;
import com.example.housekeeper.sharedPrefManager.SharedPrefManager;
import com.hbb20.CountryCodePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.SocketHandler;


public class GetNumberActivity extends AppCompatActivity {

    CountryCodePicker ccp;
    private TextView mTitle;
    private EditText edPhone;
    private Button mContinueBtn;

    private String fullPhoneNo;
    private String language;

    private SharedPreferences loginPrefs;
    private static final String LOGIN_KEY = "loginKey";

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_number);

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        edPhone = (EditText) findViewById(R.id.ed_phone);
        mContinueBtn = (Button) findViewById(R.id.btn_continue);
        mTitle = (TextView) findViewById(R.id.textTitle);

//        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
//            finish();
//            startActivity(new Intent(this, DashboardActivity.class));
//            return;
//        }

        mContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Log.d("abc 1", "Akhane asece");
                continueSignIn();

//                mTitle.setText(fullPhoneNo);

            }
        });

    }

    private void continueSignIn() {


        final String getCurrentLocale = Locale.getDefault().getLanguage();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_AUTH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Log.d("abc", response.toString());

                            String accessToken = jsonObject.getString("accessToken");
                            String verificationCode = jsonObject.getString("verificationCode");

                            // TODO: I'll use this method later...
//                            JSONArray hotellistObj=jsonObject.getJSONArray("hotelList");
//                            for (int i = 0; i < hotellistObj.length(); i++) {
//                                JSONObject object = hotellistObj.getJSONObject(i);
//
//                                String hotelId = object.getString("hotelId").trim();
//                                String hotelCaption = object.getString("hotelCaption").trim();
//                                Log.d("hotelCaption", hotelCaption);
//                            }

                            int organizationId = jsonObject.getInt("organizationId");
                            int userId = jsonObject.getInt("userId");
                            String organizationCaption = jsonObject.getString("organizationCaption");
                            Boolean isError = jsonObject.getBoolean("isError");

                            if (isError.equals(false)) {

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
//                                SharedPrefManager.getInstance(getApplicationContext()).phoneAndLanguage(modelPhoneLanguage);


//                                Log.d("For Phone NO CHeck: ", fullPhoneNo);

                                startActivity(intent);
                                finish();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(GetNumberActivity.this, "error number 1" + " " + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(GetNumberActivity.this, "error number 2" + " " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                String countryCode = ccp.getSelectedCountryCode().trim();
                String phoneNo = edPhone.getText().toString().trim();

                fullPhoneNo = countryCode + phoneNo;
                language = "en";


                Map<String, String> params = new HashMap<>();
                params.put("phoneNumber", fullPhoneNo);
                params.put("language", language);
                return params;
            }
        };

        // Singleton class call for queue
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//
//        //noinspection SimplifiableIfStatement
//        if (id == android.R.id.home) {
//            onBackPressed();
//            return true;
//        } else if (id == R.id.action_english) {
//
//            new SharedPrefManager(this).phoneAndLanguage().s;
//            new DataPreference(this).isEnglish(true);
//            MultiLanguage.setApplicationlanguage(this, "en");
//
//            new AppManager(this).SetIntent(LoginActivityNew.class);
//            finish();
//            return true;
//        } else if (id == R.id.action_arabic) {
//            new DataPreference(this).setLanguage("ar");
//            new DataPreference(this).isEnglish(false);
//            MultiLanguage.setApplicationlanguage(this, "ar");
//
//            new AppManager(this).SetIntent(LoginActivityNew.class);
//            finish();
//            return true;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
}

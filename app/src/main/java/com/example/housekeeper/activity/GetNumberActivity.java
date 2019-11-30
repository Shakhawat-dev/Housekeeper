package com.example.housekeeper.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.hbb20.CountryCodePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class GetNumberActivity extends AppCompatActivity {

    CountryCodePicker ccp;
    private TextView mTitle;
    private EditText edPhone;
    private Button mContinueBtn;

    private RequestQueue queue;

    private static final String URL = "http://175.41.47.106/alamaone/fofTaskAssignmentApi/auth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_number);

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        edPhone = (EditText) findViewById(R.id.ed_phone);
        mContinueBtn = (Button) findViewById(R.id.btn_continue);
        mTitle = (TextView) findViewById(R.id.textTitle);

        queue = Volley.newRequestQueue(this);

        mContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String countryCode = ccp.getSelectedCountryCode().trim();
                String phoneNo = edPhone.getText().toString().trim();

                String fullPhoneNo = countryCode + phoneNo;
                Log.d("abc 1", "Akhane asece");
                continueSignIn(URL);
//                mTitle.setText(fullPhoneNo);

            }
        });

    }

    private void continueSignIn(final String url) {


        final String getCurrentLocale = Locale.getDefault().getLanguage();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Log.d("abc", response.toString());

                            String accessToken = jsonObject.getString("accessToken");
                            String verificationCode = jsonObject.getString("verificationCode");

                            //Todo: I'll use this method later...
//                            JSONArray hotellistObj=jsonObject.getJSONArray("hotelList");
//                            for (int i = 0; i < hotellistObj.length(); i++) {
//                                JSONObject object = hotellistObj.getJSONObject(i);
//
//                                String hotelId = object.getString("hotelId").trim();
//                                String hotelCaption = object.getString("hotelCaption").trim();
//                                Log.d("hotelCaption", hotelCaption);
//                            }

                            Boolean isError = jsonObject.getBoolean("isError");

                            if (isError.equals(false)) {

                                Intent intent = new Intent(GetNumberActivity.this, MobileCodeActivity.class);

                                intent.putExtra("Verification Code", verificationCode);
                                intent.putExtra("Access Token", accessToken);

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
                Map<String, String> params = new HashMap<>();
                params.put("phoneNumber", "966920006413");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(GetNumberActivity.this);
        requestQueue.add(stringRequest);


    }
}

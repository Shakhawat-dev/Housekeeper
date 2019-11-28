package com.example.housekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    private Button loginBtn;
    private SharedPreferences loginPrefs;
    private static final String LOGIN_KEY = "loginKey";

    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUsername = (EditText) findViewById(R.id.et_username);
        editTextPassword = (EditText) findViewById(R.id.et_password);
        loginBtn = (Button) findViewById(R.id.btnlogin);

        SharedPreferences prefs;

        prefs = getSharedPreferences(LOGIN_KEY, 0);

        if (prefs.contains("Key")) {

            Toast.makeText(MainActivity.this, "Signed In", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();

        } else {

            Toast.makeText(MainActivity.this, "Not Signed In", Toast.LENGTH_LONG).show();

        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                if (validate(username, password)) {
                    signIn(username, password);
                } else {
                    Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

//    public void clickLogin(View view) {
//        String username = editTextUsername.getText().toString();
//        String password = editTextPassword.getText().toString();
//        if (validate(username, password)) {
//                signIn(username, password);
//        } else {
//            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
//        }
//    }

    private void signIn(final String username, final String password) {
        String URL = "http://175.41.47.106/alamafour/api/login";
        final String getCurrentLocale = Locale.getDefault().getLanguage();

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("username", "admin");
            jsonBody.put("password", "admin");
            final String mRequestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String access_token = jsonObject.getString("access_token");
                        String username = jsonObject.getString("username");
                        JSONArray roles = jsonObject.getJSONArray("roles");
                        String role = roles.getString(0);

                        if (access_token != "") {

                            loginPrefs = getSharedPreferences(LOGIN_KEY, 0);
                            SharedPreferences.Editor editor = loginPrefs.edit();
                            editor.putString("Key", access_token);
                            editor.commit();

                            Intent intent = new Intent(MainActivity.this, HotelListActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "Login fail 2" + " " + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_RESPONSE", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }
            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private boolean validate(String emailStr, String password) {
        //Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        // return password.length() > 0  && emailStr.length();
        return true;
    }
}

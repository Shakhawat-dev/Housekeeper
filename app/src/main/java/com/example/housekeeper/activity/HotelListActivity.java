package com.example.housekeeper.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.housekeeper.R;
import com.example.housekeeper.adapter.AdapterHotels;
import com.example.housekeeper.api.URLs;
import com.example.housekeeper.api.VolleySingleton;
import com.example.housekeeper.model.ModelHotels;
import com.example.housekeeper.model.ModelLogin;
import com.example.housekeeper.sharedPrefManager.SharedPrefManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HotelListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ModelHotels> hotelsList;

    private String mPhoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        recyclerView = (RecyclerView) findViewById(R.id.hotel_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            mPhoneNo = extras.getString("phone no");

        }

        getHotels();

        hotelsList = new ArrayList<>();

//        for (int i=0; i<10; i++) {
//            ModelHotels modelHotels = new ModelHotels(
//                    "Name " + (i+1),
//                    "Image ",
//                    "Address"
//            );
//
//            hotelsList.add(modelHotels);
//        }
//
//        adapter = new AdapterHotels(this, hotelsList);
//        recyclerView.setAdapter(adapter);
    }

    private void getHotels() {

        final String getCurrentLocale = Locale.getDefault().getLanguage();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_AUTH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            // Getting the object values
//                            String accessToken = jsonObject.getString("accessToken");
//                            String verificationCode = jsonObject.getString("verificationCode");
//                            int organizationId = jsonObject.getInt("organizationId");
//                            int userId = jsonObject.getInt("userId");
//                            String organizationCaption = jsonObject.getString("organizationCaption");

                            Boolean isError = jsonObject.getBoolean("isError");

                            if (isError.equals(false)) {

                                ModelLogin modelLogin = new ModelLogin(

                                        jsonObject.getString("accessToken"),
                                        jsonObject.getString("verificationCode"),
                                        jsonObject.getInt("organizationId"),
                                        jsonObject.getInt("userId"),
                                        jsonObject.getString("organizationCaption"),
                                        jsonObject.getBoolean("isError")

                                );

                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(modelLogin);


                            }

                            Log.d("abc", response.toString());

                            //Todo: I'll use this method later...
                            JSONArray hotellistObj = jsonObject.getJSONArray("hotelList");

                            for (int i = 0; i < hotellistObj.length(); i++) {
                                JSONObject object = hotellistObj.getJSONObject(i);

                                String hoteName = object.getString("hotelCaption").trim();
                                String hotelAddress = object.getString("address").trim();
                                String hotelId = object.getString("hotelId").trim();

                                Log.d("hotelCaption", hoteName);

                                ModelHotels modelHotels = new ModelHotels(hoteName, hotelAddress, hotelId);
                                hotelsList.add(modelHotels);
                            }


                            adapter = new AdapterHotels(HotelListActivity.this, hotelsList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();

                            Toast.makeText(HotelListActivity.this, "error number 1" + " " + e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(HotelListActivity.this, "error number 2" + " " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("phoneNumber", mPhoneNo);
                params.put("language", "en");
                return params;
            }
        };

//        RequestQueue requestQueue = Volley.newRequestQueue(HotelListActivity.this);
//        requestQueue.add(stringRequest);
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}

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
import com.example.housekeeper.model.ModelHotels;

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

    private static final String URL = "http://175.41.47.106/alamaone/fofTaskAssignmentApi/auth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        recyclerView = (RecyclerView) findViewById(R.id.hotel_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getHotels(URL);

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

    private void getHotels(String url) {

        final String getCurrentLocale = Locale.getDefault().getLanguage();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Log.d("abc", response.toString());

                            //Todo: I'll use this method later...
                            JSONArray hotellistObj=jsonObject.getJSONArray("hotelList");
                            for (int i = 0; i < hotellistObj.length(); i++) {
                                JSONObject object = hotellistObj.getJSONObject(i);

                                String hotelId = object.getString("hotelId").trim();
                                String hotelCaption = object.getString("hotelCaption").trim();
                                Log.d("hotelCaption", hotelCaption);

                                ModelHotels modelHotels = new ModelHotels(hotelId, " ", hotelCaption);
                                hotelsList.add(modelHotels);
                            }

                            adapter = new AdapterHotels(HotelListActivity.this, hotelsList);
                            recyclerView.setAdapter(adapter);

                            Boolean isError = jsonObject.getBoolean("isError");


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
                params.put("phoneNumber", "966920006413");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(HotelListActivity.this);
        requestQueue.add(stringRequest);

    }
}

package com.example.housekeeper.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housekeeper.R;
import com.example.housekeeper.adapter.AdapterHotels;
import com.example.housekeeper.utils.Data;
import com.example.housekeeper.utils.ExistApplication;

public class HotelListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private AppCompatActivity mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);
        uiInitialize();

        if (Data.hotelsList.size() > 0) {
            setRecycleViwData();
        }


    }

    private void uiInitialize() {
        mContext = this;
        setTitle("Select your Hotel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.hotel_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

//    private void getHotels() {
//
//        final String getCurrentLocale = Locale.getDefault().getLanguage();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_AUTH,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            Log.d(Data.TAG, response.toString());
//                            // Getting the object values
////                            String accessToken = jsonObject.getString("accessToken");
////                            String verificationCode = jsonObject.getString("verificationCode");
////                            int organizationId = jsonObject.getInt("organizationId");
////                            int userId = jsonObject.getInt("userId");
////                            String organizationCaption = jsonObject.getString("organizationCaption");
//
//                            Boolean isError = jsonObject.getBoolean("isError");
//
//                            if (isError.equals(false)) {
//
//                                ModelLogin modelLogin = new ModelLogin(
//
//                                        jsonObject.getString("accessToken"),
//                                        jsonObject.getString("verificationCode"),
//                                        jsonObject.getInt("organizationId"),
//                                        jsonObject.getInt("userId"),
//                                        jsonObject.getString("organizationCaption"),
//                                        "",
//                                        jsonObject.getBoolean("isError")
//
//                                );
//
//                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(modelLogin);
//
//
//                            }
//
//
//                            //Todo: I'll use this method later...
//                            JSONArray hotellistObj = jsonObject.getJSONArray("hotelList");
//
//                            for (int i = 0; i < hotellistObj.length(); i++) {
//                                JSONObject object = hotellistObj.getJSONObject(i);
//
//                                String hoteName = object.getString("hotelCaption").trim();
//                                String hotelAddress = object.getString("address").trim();
//                                String hotelId = object.getString("hotelId").trim();
//
//                                Log.d("hotelCaption", hoteName);
//
//                                ModelHotels modelHotels = new ModelHotels(hoteName, hotelAddress, hotelId);
//                                //hotelsList.add(modelHotels);
//                            }
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//
//                            Toast.makeText(HotelListActivity.this, "error number 1" + " " + e.toString(), Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        Toast.makeText(HotelListActivity.this, "error number 2" + " " + error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("phoneNumber", mPhoneNo);
//                params.put("language", "en");
//                return params;
//            }
//        };
//
////        RequestQueue requestQueue = Volley.newRequestQueue(HotelListActivity.this);
////        requestQueue.add(stringRequest);
//        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            new ExistApplication(mContext);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    void setRecycleViwData() {
        adapter = new AdapterHotels(HotelListActivity.this, Data.hotelsList);
        recyclerView.setAdapter(adapter);
    }
}

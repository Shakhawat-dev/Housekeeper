package com.example.housekeeper.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.housekeeper.R;
import com.example.housekeeper.api.URLs;
import com.example.housekeeper.api.VolleySingleton;
import com.example.housekeeper.fragments.GardenChecklistFragment;
import com.example.housekeeper.fragments.RoomChecklistFragment;
import com.example.housekeeper.fragments.SwimmingPoolChecklistFragment;
import com.example.housekeeper.model.ModelChecklistType;
import com.example.housekeeper.sharedPrefManager.SharedPrefManager;
import com.example.housekeeper.utils.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChecklistActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static FragmentManager fragmentManager;
    //    String[] checkLists = {"Room", "Swimming pool", "Garden"};
    private List<String> checkLists = new ArrayList<>();
    private List<ModelChecklistType> checklistTypes = new ArrayList<>();
    private Spinner spinner;
    private String mAccessToken, mPhoneNo, mLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mLanguage = SharedPrefManager.getInstance(this).getPhoneAndLanguage().getLanguage();
        getCheckLists();

        spinner = findViewById(R.id.checklist_spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, checkLists);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(aa);

        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_container_framelayout) != null) {
            if (savedInstanceState != null) {
                return;
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RoomChecklistFragment roomChecklistFragment = new RoomChecklistFragment();
            fragmentTransaction.add(R.id.fragment_container_framelayout, roomChecklistFragment, null);
            fragmentTransaction.commit();
        }

    }

    public void getCheckLists() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_CHECKLIST_TYPE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(Data.TAG, "Checklist types:" + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray checkListTypeObj = jsonObject.getJSONArray("checkListType");

                            Boolean isError = jsonObject.getBoolean("isError");

                            if (!isError) {

                                for (int i = 0; i < checkListTypeObj.length(); i++) {
                                    JSONObject object = checkListTypeObj.getJSONObject(i);

                                    String optionKey = object.getString("optionKey").trim();
                                    String optionValue = object.getString("optionValue").trim();

                                    checkLists.add(optionValue);
                                    ModelChecklistType checklistType = new ModelChecklistType(optionKey, optionValue);
                                    checklistTypes.add(checklistType);
                                }
                            } else {
                                String message = jsonObject.getString("message");
                                Toast.makeText(ChecklistActivity.this, message, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ChecklistActivity.this, "error number 1" + " " + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ChecklistActivity.this, "error number 2" + " " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("language", mLanguage);
                Log.i(Data.TAG, params.toString());
                return params;
            }

        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Add addToBackStack if you want to get to the default fragment
        if (position == 1) {
            fragmentManager.beginTransaction().replace(R.id.fragment_container_framelayout, new SwimmingPoolChecklistFragment(), null).commit();
        } else if (position == 2) {
            fragmentManager.beginTransaction().replace(R.id.fragment_container_framelayout, new GardenChecklistFragment(), null).commit();
        } else {
            fragmentManager.beginTransaction().replace(R.id.fragment_container_framelayout, new RoomChecklistFragment(), null).commit();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

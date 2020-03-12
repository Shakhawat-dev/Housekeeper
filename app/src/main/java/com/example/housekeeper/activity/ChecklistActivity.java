package com.example.housekeeper.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.housekeeper.R;
import com.example.housekeeper.adapter.AdapterChecklist;
import com.example.housekeeper.api.URLs;
import com.example.housekeeper.api.VolleySingleton;
import com.example.housekeeper.model.ModelChecklist;
import com.example.housekeeper.model.ModelChecklistType;
import com.example.housekeeper.sharedPrefManager.SharedPrefManager;
import com.example.housekeeper.utils.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChecklistActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static FragmentManager fragmentManager;
    private List<ModelChecklistType> checklistTypes = new ArrayList<>();
    private Spinner spinner;
    private ArrayAdapter<ModelChecklistType> spinnerAdapter;
    private String mAccessToken, mPhoneNo, mLanguage, mCurrentDate, mHotelId;

    private RecyclerView roomRecyclerVIew;
    private Button updateBtn;
    private RecyclerView.Adapter recyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<ModelChecklist> checklists = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

        // Fetching checklist types into array
        getCheckListTypes();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAccessToken = SharedPrefManager.getInstance(this).getLogin().getAccessToken();
        mPhoneNo = SharedPrefManager.getInstance(this).getPhoneAndLanguage().getPhone();
        mLanguage = SharedPrefManager.getInstance(this).getPhoneAndLanguage().getLanguage();
        mHotelId = SharedPrefManager.getInstance(this).getHotel().getHotelId();

//        fragmentManager = getSupportFragmentManager();

        spinner = findViewById(R.id.checklist_spinner);
        spinnerAdapter = new ArrayAdapter<ModelChecklistType>(this, android.R.layout.simple_spinner_dropdown_item, checklistTypes);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(this);

//        fragmentManager = getSupportFragmentManager();

//        if (findViewById(R.id.fragment_container_framelayout) != null) {
//            if (savedInstanceState != null) {
//                return;
//            }
//
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            RoomChecklistFragment roomChecklistFragment = new RoomChecklistFragment();
//            fragmentTransaction.add(R.id.fragment_container_framelayout, roomChecklistFragment, null);
//            fragmentTransaction.commit();
//        }

        roomRecyclerVIew = findViewById(R.id.room_recyclerview);
        updateBtn = findViewById(R.id.update_btn);
        roomRecyclerVIew.setHasFixedSize(true);
        mAccessToken = SharedPrefManager.getInstance(this).getLogin().getAccessToken();
        mPhoneNo = SharedPrefManager.getInstance(this).getPhoneAndLanguage().getPhone();
        mLanguage = SharedPrefManager.getInstance(this).getPhoneAndLanguage().getLanguage();
        linearLayoutManager = new LinearLayoutManager(this);

        roomRecyclerVIew.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new AdapterChecklist(this, checklists);
        roomRecyclerVIew.setAdapter(recyclerViewAdapter);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ModelChecklist list : checklists) {

                    Log.i("Member name: ", list.getCaption() + " Status: " + list.getTaskStatus());

                }
//                Log.d(TAG, "onClick: UpdateBtn" + checklists.);
            }
        });

    }

    public void getCheckListTypes() {

//        Call<ChecklistTypeResponse> call = RetrofitClient
//                .getInstance()
//                .getApi()
//                .getCheckListTypes("en");
//
//        call.enqueue(new Callback<ChecklistTypeResponse>() {
//            @Override
//            public void onResponse(Call<ChecklistTypeResponse> call, retrofit2.Response<ChecklistTypeResponse> response) {
//                String s = null;
//                s = response.body().getCheckListType().toString();
//                Toast.makeText(ChecklistActivity.this, s, Toast.LENGTH_LONG).show();
//
//                checklistTypes = response.body().getCheckListType();
//                spinner.setAdapter(spinnerAdapter);
//                Log.d( Data.TAG, "Response: " + checklistTypes.toString());
//            }
//
//            @Override
//            public void onFailure(Call<ChecklistTypeResponse> call, Throwable t) {
//                Toast.makeText(ChecklistActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_CHECKLIST_TYPE + "?lang=" + mLanguage,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(Data.TAG, "Checklist types:" + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray checkListTypeObj = jsonObject.getJSONArray("checkListType");

                            for (int i = 0; i < checkListTypeObj.length(); i++) {
                                JSONObject object = checkListTypeObj.getJSONObject(i);

                                String optionKey = object.getString("optionKey").trim();
                                String optionValue = object.getString("optionValue").trim();

                                ModelChecklistType checklistType = new ModelChecklistType(optionKey, optionValue);
                                checklistTypes.add(checklistType);
                                // Call Spinner Adapter
//                                Log.d( Data.TAG, "Response: " + checklistTypes.toString());
                                spinner.setAdapter(spinnerAdapter);
                            }
//                            } else {
//                                String message = jsonObject.getString("message");
//                                Toast.makeText(ChecklistActivity.this, message, Toast.LENGTH_SHORT).show();
//                            }

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
        });

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void getCheckLists(String checklistType) {

        String url = URLs.URL_CHECKED_LISTS + "?organizationId=" + mHotelId + "&locationType=" + checklistType + "&lang=" + mLanguage;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(Data.TAG, "Checked Lists: " + response);

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray checkedListsObjArray = jsonObject.getJSONArray("checkListList");

                    // Clearing previous data to prevent overlap
                    checklists.clear();
                    for (int i = 0; i < checkedListsObjArray.length(); i++) {
                        JSONObject object = checkedListsObjArray.getJSONObject(i);

                        ModelChecklist checklist = new ModelChecklist(
                                object.getInt("id"),
                                object.getString("caption"),
                                object.getString("checkListType"),
                                object.getBoolean("taskStatus")
                        );

                        checklists.add(checklist);
                    }

                    recyclerViewAdapter.notifyDataSetChanged();

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
        });

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
//        Toast.makeText(ChecklistActivity.this, "Selected: " + item, Toast.LENGTH_SHORT).show();

        getCheckLists(item);


//        RoomChecklistFragment roomChecklistFragment = new RoomChecklistFragment();
//
//        Bundle data = new Bundle();//Use bundle to pass data
//        data.putString("checklist_type", item);//put string, int, etc in bundle with a key value
//        roomChecklistFragment.setArguments(data);//Finally s

//        checklists.clear();
//        for (int i = 0; i < 10; i++) {
//            checklists.add(new ModelChecklist(item + i, false));
//        }

//        recyclerViewAdapter.notifyDataSetChanged();


        // For Multiple Fragments
//        if (findViewById(R.id.fragment_container_framelayout) != null) {
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.add(R.id.fragment_container_framelayout, roomChecklistFragment, null);
//            fragmentTransaction.commit();
//        }


        // Add addToBackStack if you want to get to the default fragment
//        if (position == 1) {
//            fragmentManager.beginTransaction().replace(R.id.fragment_container_framelayout, new SwimmingPoolChecklistFragment(), null).commit();
//        } else if (position == 2) {
//            fragmentManager.beginTransaction().replace(R.id.fragment_container_framelayout, new GardenChecklistFragment(), null).commit();
//        } else {
//            fragmentManager.beginTransaction().replace(R.id.fragment_container_framelayout, new RoomChecklistFragment(), null).commit();
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

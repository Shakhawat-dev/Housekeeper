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

import com.example.housekeeper.R;
import com.example.housekeeper.adapter.AdapterChecklist;
import com.example.housekeeper.api.RetrofitClient;
import com.example.housekeeper.custom.DefectDialog;
import com.example.housekeeper.model.CheckedListResponse;
import com.example.housekeeper.model.ChecklistTypeResponse;
import com.example.housekeeper.model.ModelChecklist;
import com.example.housekeeper.model.ModelChecklistType;
import com.example.housekeeper.model.ModelDefectDialog;
import com.example.housekeeper.sharedPrefManager.SharedPrefManager;
import com.example.housekeeper.utils.Data;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChecklistActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DefectDialog.DefectDialogTapListener {

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

        mAccessToken = SharedPrefManager.getInstance(this).getLogin().getAccessToken();
        mPhoneNo = SharedPrefManager.getInstance(this).getPhoneAndLanguage().getPhone();
        mLanguage = SharedPrefManager.getInstance(this).getPhoneAndLanguage().getLanguage();
        mHotelId = SharedPrefManager.getInstance(this).getHotel().getHotelId();
        spinner = findViewById(R.id.checklist_spinner);
        // Fetching checklist types into array
        getCheckListTypes();

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

        linearLayoutManager = new LinearLayoutManager(this);
        roomRecyclerVIew = findViewById(R.id.room_recyclerview);
        roomRecyclerVIew.setHasFixedSize(true);
        roomRecyclerVIew.setLayoutManager(linearLayoutManager);

        updateBtn = findViewById(R.id.update_btn);

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
        Log.d(Data.TAG, "Language: " + mLanguage);

        Call<ChecklistTypeResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getCheckListTypes(mLanguage);

        call.enqueue(new Callback<ChecklistTypeResponse>() {
            @Override
            public void onResponse(Call<ChecklistTypeResponse> call, Response<ChecklistTypeResponse> response) {
                checklistTypes = response.body().getCheckListType();
//                Log.d(Data.TAG, "checklistTypes: " + checklistTypes);
                spinnerAdapter = new ArrayAdapter<ModelChecklistType>(ChecklistActivity.this, android.R.layout.simple_spinner_dropdown_item, checklistTypes);
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setOnItemSelectedListener(ChecklistActivity.this);
                spinner.setAdapter(spinnerAdapter);
            }

            @Override
            public void onFailure(Call<ChecklistTypeResponse> call, Throwable t) {
                Toast.makeText(ChecklistActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getCheckLists(String checklistType) {

        Call<CheckedListResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getCheckedList(mHotelId, checklistType, mLanguage);

        call.enqueue(new Callback<CheckedListResponse>() {
            @Override
            public void onResponse(Call<CheckedListResponse> call, Response<CheckedListResponse> response) {

                checklists = response.body().getChecklists();
                recyclerViewAdapter = new AdapterChecklist(ChecklistActivity.this, checklists);
                roomRecyclerVIew.setAdapter(recyclerViewAdapter);
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<CheckedListResponse> call, Throwable t) {
                Toast.makeText(ChecklistActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


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

    @Override
    public void onOkBtnTapped(ModelDefectDialog defectDialog) {
        Log.d(Data.TAG, "onOkBtnTapped: " + defectDialog.getCaption());
    }
}


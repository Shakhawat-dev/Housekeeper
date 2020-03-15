package com.example.housekeeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.housekeeper.R;
import com.example.housekeeper.api.RetrofitClient;
import com.example.housekeeper.model.ProgressStatusList;
import com.example.housekeeper.model.StatusResponse;
import com.example.housekeeper.sharedPrefManager.SharedPrefManager;
import com.example.housekeeper.utils.CustomDate;
import com.example.housekeeper.utils.Data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private String[] categories = {"Void", "InProgress", "Completed"};
    private String spinnerSelected;
    private String remarkTxt;
    private String taskId;
    private List<ProgressStatusList> statusLists = new ArrayList<>();

    private Spinner spinner;
    private ArrayAdapter aa;
    private TextView taskTitle, taskStatus, taskRoom, taskDate;
    private EditText remarkEd;
    private Button updateBtn;
    private String mAccessToken, mPhoneNo, mLanguage, mCurrentDate, mHotelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        uiInitialization();

        if (Data.task != null) {
            setData();
        }

    }

    private void uiInitialization() {
        taskTitle = findViewById(R.id.title_tv);
        taskStatus = findViewById(R.id.status_tv);
        taskRoom = findViewById(R.id.room_no_tv);
        taskDate = findViewById(R.id.date_tv);
        spinner = findViewById(R.id.spinner);
        updateBtn = findViewById(R.id.update_btn);
        remarkEd = findViewById(R.id.remark_ed);

//        spinner.setOnItemSelectedListener(this);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTaskStatus();
            }
        });

        taskId = Data.task.getId().toString();
        mAccessToken = SharedPrefManager.getInstance(this).getLogin().getAccessToken();
        mPhoneNo = SharedPrefManager.getInstance(this).getPhoneAndLanguage().getPhone();
        mLanguage = SharedPrefManager.getInstance(this).getPhoneAndLanguage().getLanguage();
        mHotelId = SharedPrefManager.getInstance(this).getHotel().getHotelId();
        mCurrentDate = CustomDate.getCurrentDate();

        setSpinner();
    }

    private void setData() {
        taskTitle.setText(Data.task.getTitle());
        taskStatus.setText(Data.task.getStatus());
        taskRoom.setText(Data.task.getRoom());
        taskDate.setText(Data.task.getDate());
    }

    private void setSpinner() {

        Call<StatusResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getStatusList(mPhoneNo, mLanguage, mAccessToken, mCurrentDate, mHotelId);

        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response != null && response.isSuccessful() && response.body() != null) {
                    // show message
                    String s = response.body().getProgressStatusList().toString();
                    Log.d(Data.TAG, "onResponse: " + s);
                    statusLists = response.body().getProgressStatusList();
                    aa = new ArrayAdapter(TaskDetailsActivity.this, android.R.layout.simple_spinner_dropdown_item, statusLists);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setOnItemSelectedListener(TaskDetailsActivity.this);
                    spinner.setAdapter(aa);

                    if (Data.task.getStatus().equals("Completed")) {
                        spinner.setSelection(2);
                    } else if (Data.task.getStatus().equals("InPrgress")) {
                        spinner.setSelection(1);
                    } else {
                        spinner.setSelection(0);
                    }
                } else {

                }

            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Log.d(Data.TAG, "onResponse: " + t.getMessage());
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerSelected = parent.getItemAtPosition(position).toString();
//        if (spinnerSelected.equals("In Progress")) {
//            spinnerSelected = "InPrgress";
//        }
//        Toast.makeText(getApplicationContext(), "Selected" + spinnerSelected , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void updateTaskStatus() {
        remarkTxt = remarkEd.getText().toString().trim();

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .updateTaskDetails(mPhoneNo, mLanguage, mAccessToken, spinnerSelected, taskId, remarkTxt);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = null;
                try {
                    s = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        Boolean isError = jsonObject.getBoolean("isError");
                        String message = jsonObject.getString("message");

                        if (isError == false) {
                            Toast.makeText(TaskDetailsActivity.this, message, Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(TaskDetailsActivity.this, DashboardActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(TaskDetailsActivity.this, message, Toast.LENGTH_LONG).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(TaskDetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d(Data.TAG, "onFailure: " + t);
            }
        });

    }



}

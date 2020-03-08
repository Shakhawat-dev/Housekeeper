package com.example.housekeeper.ui.tasks;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.housekeeper.R;
import com.example.housekeeper.adapter.AdapterTasks;
import com.example.housekeeper.api.URLs;
import com.example.housekeeper.api.VolleySingleton;
import com.example.housekeeper.model.ModelTasks;
import com.example.housekeeper.sharedPrefManager.SharedPrefManager;
import com.example.housekeeper.utils.CustomDate;
import com.example.housekeeper.utils.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TasksFragment extends Fragment {

    private TasksViewModel tasksViewModel;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView.Adapter adapter;
    private List<ModelTasks> tasksList;
    private String mAccessToken, mPhoneNo, mLanguage, mCurrentDate, mHotelId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tasksViewModel =
                ViewModelProviders.of(this).get(TasksViewModel.class);

        View root = inflater.inflate(R.layout.fragment_tasks, container, false);

        recyclerView = root.findViewById(R.id.task_recyclerview);
        recyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getContext());

        mAccessToken = SharedPrefManager.getInstance(root.getContext()).getLogin().getAccessToken();
        mPhoneNo = SharedPrefManager.getInstance(root.getContext()).getPhoneAndLanguage().getPhone();
        mLanguage = SharedPrefManager.getInstance(root.getContext()).getPhoneAndLanguage().getLanguage();
        mHotelId = SharedPrefManager.getInstance(root.getContext()).getHotel().getHotelId();

        //for test
        //  mCurrentDate = "01-11-2019";

        Log.d("Access token fragment: ", mAccessToken);
//        Log.d("Hotel fragment: ", mLanguage);

        // Getting Task List from Api
        loadTasks();

        tasksList = new ArrayList<>();

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AdapterTasks(getContext(), tasksList);

        recyclerView.setAdapter(adapter);

        return root;
    }

    private void loadTasks() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_TASKS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(Data.TAG, "RESPONSE:" + response);

                try {
                    JSONObject jsonObject = new JSONObject(response);

//                    Boolean isError = jsonObject.getBoolean("isError");
//
//                    if (isError.equals(false)) {
//
////                        ModelTasks modelTasks = new ModelTasks(
////
////                                jsonObject.getString("accessToken"),
////                                jsonObject.getString("verificationCode"),
////                                jsonObject.getInt("organizationId"),
////                                jsonObject.getInt("userId"),
////                                jsonObject.getString("organizationCaption"),
////                                jsonObject.getBoolean("isError")
////
////                        );
////
////                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(modelLogin);
//
//
//                    }


//                    if (isError.equals(false)) {
                    //Todo: I'll use this method later...
                    JSONArray tasklistObj = jsonObject.getJSONArray("taskList");

                    for (int i = 0; i < tasklistObj.length(); i++) {
                        JSONObject object = tasklistObj.getJSONObject(i);

                        String taskCaption = object.getString("taskCaption").trim();
                        String progressStatusKey = object.getString("progressStatusKey").trim();
                        String roomCaption = object.getString("roomCaption").trim();
                        String fromDate = object.getString("fromDate").trim();

                        Log.d("taskCaption", taskCaption);

                        ModelTasks modelTasks = new ModelTasks(taskCaption, progressStatusKey, roomCaption, fromDate);
                        tasksList.add(modelTasks);
                    }


                    adapter = new AdapterTasks(getActivity(), tasksList);
                    recyclerView.setAdapter(adapter);
//                    } else {
//                        Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
//                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(getActivity(), "error number 1" + " " + e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "error number 2" + " " + error.toString(), Toast.LENGTH_SHORT).show();


            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("phoneNumber", mPhoneNo);
                params.put("language", mLanguage);
                params.put("authToken", mAccessToken);
                params.put("currentDate", CustomDate.getCurrentDate());
                params.put("hotelId", mHotelId);
                Log.i(Data.TAG, params.toString());
                return params;
            }

        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);


    }


}
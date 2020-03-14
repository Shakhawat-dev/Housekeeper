package com.example.housekeeper.ui.tasks;

import android.content.Intent;
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

import com.example.housekeeper.R;
import com.example.housekeeper.activity.GetNumberActivity;
import com.example.housekeeper.adapter.AdapterTasks;
import com.example.housekeeper.api.RetrofitClient;
import com.example.housekeeper.model.ModelTasks;
import com.example.housekeeper.model.TaskListResponse;
import com.example.housekeeper.sharedPrefManager.SharedPrefManager;
import com.example.housekeeper.utils.CustomDate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TasksFragment extends Fragment {

    private TasksViewModel tasksViewModel;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView.Adapter adapter;
    private List<ModelTasks> tasksList = new ArrayList<>();
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
        mCurrentDate = CustomDate.getCurrentDate();

        if (mAccessToken == null) {
            Intent intent = new Intent(getContext(), GetNumberActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

        Log.d("Access token fragment: ", mAccessToken);
//        Log.d("Hotel fragment: ", mLanguage);

        // Getting Task List from Api
        loadTasks();
        recyclerView.setLayoutManager(linearLayoutManager);

        return root;
    }

    private void loadTasks() {

        Call<TaskListResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getTaskLists(mPhoneNo, mLanguage, mAccessToken, mCurrentDate, mHotelId);

        call.enqueue(new Callback<TaskListResponse>() {
            @Override
            public void onResponse(Call<TaskListResponse> call, Response<TaskListResponse> response) {

                tasksList = response.body().getTasksList();
//                Log.d(TAG, "Tasks: " + tasksList.toString());
                adapter = new AdapterTasks(getActivity(), tasksList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<TaskListResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

//        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_TASKS, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d(Data.TAG, "Task RESPONSE:" + response);
//
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//
//
////                    if (isError.equals(false)) {
//                    //Todo: I'll use this method later...
//                    JSONArray tasklistObj = jsonObject.getJSONArray("taskList");
//
//                    for (int i = 0; i < tasklistObj.length(); i++) {
//                        JSONObject object = tasklistObj.getJSONObject(i);
//
//                        Integer id = object.getInt("id");
//                        String taskCaption = object.getString("taskCaption").trim();
//                        String progressStatusKey = object.getString("progressStatusKey").trim();
//                        String roomCaption = object.getString("roomCaption").trim();
//                        String fromDate = object.getString("fromDate").trim();
//                        Integer priorityRating = object.getInt("priorityRating");
//
//                        Log.d("taskCaption", taskCaption);
//
//                        ModelTasks modelTasks = new ModelTasks(id, taskCaption, progressStatusKey, roomCaption, fromDate, priorityRating);
//                        tasksList.add(modelTasks);
//                    }
//
//
//                    adapter = new AdapterTasks(getActivity(), tasksList);
//                    recyclerView.setAdapter(adapter);
////                    } else {
////                        Toast.makeText(getActivity(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
////                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//
//                    Toast.makeText(getActivity(), "error number 1" + " " + e.toString(), Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Toast.makeText(getContext(), "error number 2" + " " + error.toString(), Toast.LENGTH_SHORT).show();
//
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Log.d(Data.TAG, "Language: " + mLanguage);
//                Map<String, String> params = new HashMap<>();
//                params.put("phoneNumber", mPhoneNo);
//                params.put("language", mLanguage);
//                params.put("authToken", mAccessToken);
//                params.put("currentDate", CustomDate.getCurrentDate());
//                params.put("hotelId", mHotelId);
//                Log.i(Data.TAG, params.toString());
//                return params;
//            }
//
//        };
//
//        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);


    }


}
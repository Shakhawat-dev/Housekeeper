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

    }

}
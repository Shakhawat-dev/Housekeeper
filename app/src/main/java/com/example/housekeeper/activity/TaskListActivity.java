package com.example.housekeeper.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.housekeeper.R;
import com.example.housekeeper.adapter.AdapterTasks;
import com.example.housekeeper.model.ModelTasks;

import java.util.ArrayList;
import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ModelTasks> tasksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        recyclerView = (RecyclerView) findViewById(R.id.task_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tasksList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            ModelTasks modelTasks = new ModelTasks(
                    "Title" + (i+1),
                    "Status",
                    "Room",
                    "Date"
            );

            tasksList.add(modelTasks);

        }

        adapter = new AdapterTasks(this, tasksList);
        recyclerView.setAdapter(adapter);

    }
}

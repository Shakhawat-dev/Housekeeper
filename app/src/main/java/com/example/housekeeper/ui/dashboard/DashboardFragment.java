package com.example.housekeeper.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.housekeeper.R;
import com.example.housekeeper.activity.ChecklistActivity;
import com.example.housekeeper.activity.DefectActivity;
import com.example.housekeeper.activity.TaskListActivity;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private CardView mRooms, mTasks, mHotels, mChecklists, mDefects;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        mRooms = root.findViewById(R.id.rooms_card);
        mTasks = root.findViewById(R.id.task_card);
        mHotels = root.findViewById(R.id.hotels_card);
        mChecklists = root.findViewById(R.id.checklist_card);
        mDefects = root.findViewById(R.id.defects_card);

//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        mTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TaskListActivity.class);
                startActivity(intent);
            }
        });

        mChecklists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChecklistActivity.class);
                startActivity(intent);
            }
        });

        mDefects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DefectActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }
}
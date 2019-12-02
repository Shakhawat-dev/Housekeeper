package com.example.housekeeper.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.housekeeper.R;
import com.example.housekeeper.activity.HotelListActivity;
import com.example.housekeeper.activity.TaskListActivity;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private CardView mRooms , mTasks, mHotels;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        mRooms = (CardView) root.findViewById(R.id.rooms_card);
        mTasks = (CardView) root.findViewById(R.id.task_card);
        mHotels = (CardView) root.findViewById(R.id.hotels_card);

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

        return root;
    }
}
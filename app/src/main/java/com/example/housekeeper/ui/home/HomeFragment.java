package com.example.housekeeper.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.housekeeper.activity.HotelListActivity;
import com.example.housekeeper.R;
import com.example.housekeeper.activity.TaskListActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private CardView mRooms , mTasks, mHotels;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mRooms = (CardView) root.findViewById(R.id.rooms_card);
        mTasks = (CardView) root.findViewById(R.id.task_card);
        mHotels = (CardView) root.findViewById(R.id.hotels_card);

//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        mHotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), HotelListActivity.class);
                startActivity(intent);

            }
        });

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
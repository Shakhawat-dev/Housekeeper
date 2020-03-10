package com.example.housekeeper.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.housekeeper.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class GardenChecklistFragment extends Fragment {

    public GardenChecklistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_garden_checklist, container, false);
    }
}

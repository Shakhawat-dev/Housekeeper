package com.example.housekeeper.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.housekeeper.GardenChecklistFragment;
import com.example.housekeeper.R;
import com.example.housekeeper.RoomChecklistFragment;
import com.example.housekeeper.SwimmingPoolChecklistFragment;

public class ChecklistActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static FragmentManager fragmentManager;
    String[] checkLists = {"Room", "Swimming pool", "Garden"};
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner = findViewById(R.id.checklist_spinner);

        spinner.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, checkLists);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(aa);

        fragmentManager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_container_framelayout) != null) {
            if (savedInstanceState != null) {
                return;
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            RoomChecklistFragment roomChecklistFragment = new RoomChecklistFragment();
            fragmentTransaction.add(R.id.fragment_container_framelayout, roomChecklistFragment, null);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Add addToBackStack if you want to get to the default fragment
        if (position == 1) {
            fragmentManager.beginTransaction().replace(R.id.fragment_container_framelayout, new SwimmingPoolChecklistFragment(), null).commit();
        } else if (position == 2) {
            fragmentManager.beginTransaction().replace(R.id.fragment_container_framelayout, new GardenChecklistFragment(), null).commit();
        } else {
            fragmentManager.beginTransaction().replace(R.id.fragment_container_framelayout, new RoomChecklistFragment(), null).commit();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

package com.example.housekeeper.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.housekeeper.R;
import com.example.housekeeper.adapter.AdapterHotels;
import com.example.housekeeper.utils.Data;
import com.example.housekeeper.utils.ExistApplication;

public class HotelListActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private AppCompatActivity mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);
        uiInitialize();

        if (Data.hotelsList.size() > 0) {
            setRecycleViwData();
        }
    }

    private void uiInitialize() {
        mContext = this;
        setTitle("Select your Hotel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.hotel_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            new ExistApplication(mContext);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    void setRecycleViwData() {
        adapter = new AdapterHotels(HotelListActivity.this, Data.hotelsList);
        recyclerView.setAdapter(adapter);
    }
}

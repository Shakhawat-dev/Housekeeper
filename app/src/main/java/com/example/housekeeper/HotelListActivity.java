package com.example.housekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.housekeeper.adapter.AdapterHotels;
import com.example.housekeeper.model.ModelHotels;

import java.util.ArrayList;
import java.util.List;

public class HotelListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ModelHotels> hotelsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_list);

        recyclerView = (RecyclerView) findViewById(R.id.hotel_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        hotelsList = new ArrayList<>();

        for (int i=0; i<10; i++) {
            ModelHotels modelHotels = new ModelHotels(
                    "Name " + (i+1),
                    "Image ",
                    "Address"
            );

            hotelsList.add(modelHotels);
        }

        adapter = new AdapterHotels(this, hotelsList);
        recyclerView.setAdapter(adapter);
    }
}

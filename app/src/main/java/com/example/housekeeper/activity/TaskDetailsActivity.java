package com.example.housekeeper.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.housekeeper.R;

public class TaskDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    String[] categories = { "Clean", "Dirty"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Spinner spinner = findViewById(R.id.spinner);
        Button updateBtn = findViewById(R.id.update_btn);

        spinner.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(aa);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();

        Toast.makeText(getApplicationContext(), "Selected" + item , Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}

package com.example.housekeeper.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.housekeeper.R;

public class DefectActivity extends AppCompatActivity {

    private TextView bathroomTV, tvTV, lockTV, heatingTV, lightTV, otherTV;
    private CardView bathroomCard, tvCard, lockCard, heatingCard, lightCard, otherCard;
    private TextView photoTV, customerCareTV;
    private CardView photoCard, customerCareCard;
    private TextView defectTV, communicateTV, reportTV;
    private EditText reportED;
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defect);

        bathroomTV = findViewById(R.id.tv_bathroom);
        tvTV = findViewById(R.id.tv_tv);
        lockTV = findViewById(R.id.tv_lock);
        heatingTV = findViewById(R.id.tv_heating);
        lightTV = findViewById(R.id.tv_lights);
        otherTV = findViewById(R.id.tv_other);

        bathroomCard = findViewById(R.id.card_bathroom);
        tvCard = findViewById(R.id.card_customer_care);
        bathroomCard = findViewById(R.id.card_bathroom);
        bathroomCard = findViewById(R.id.card_bathroom);
        bathroomCard = findViewById(R.id.card_bathroom);
        bathroomCard = findViewById(R.id.card_bathroom);


    }
}

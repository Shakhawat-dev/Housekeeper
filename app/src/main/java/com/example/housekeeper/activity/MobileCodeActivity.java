package com.example.housekeeper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.example.housekeeper.R;

public class MobileCodeActivity extends AppCompatActivity {

    private String mToken, mVerificationCode, mOrganizationId, mUserId, getmOrganizationCaption, mPhoneNo;
    private Boolean mIsError;
    private Button mContinueBtn;
    private PinView pinView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_code);

        pinView = findViewById(R.id.pv_input);
        mContinueBtn = findViewById(R.id.btn_verify);

        pinView.setCursorVisible(true);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            mVerificationCode = extras.getString("Verification Code");
            mToken = extras.getString("Access Token");
            mPhoneNo = extras.getString("Mobile NO");
            pinView.setText(mVerificationCode);

        }

        mContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pinviewText = pinView.getText().toString().trim();

                if (pinviewText.equals(mVerificationCode)) {

                    Intent intent = new Intent(MobileCodeActivity.this, HotelListActivity.class);
                    intent.putExtra("phone no", mPhoneNo);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(MobileCodeActivity.this, "Enter Valid Verification code!", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}

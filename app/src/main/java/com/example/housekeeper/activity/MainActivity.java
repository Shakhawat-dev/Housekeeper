package com.example.housekeeper.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.housekeeper.R;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    private Button mVerifyBtn;
    private SharedPreferences loginPrefs;
    private static final String LOGIN_KEY = "loginKey";

    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUsername = findViewById(R.id.et_username);
        editTextPassword = findViewById(R.id.et_password);
        mVerifyBtn = findViewById(R.id.btnlogin);

        mVerifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if (validate(username, password)) {

//                    signIn(username, password);

                } else {
                    Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private boolean validate(String emailStr, String password) {
        //Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        // return password.length() > 0  && emailStr.length();
        return true;
    }
}

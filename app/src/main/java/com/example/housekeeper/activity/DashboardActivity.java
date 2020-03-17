package com.example.housekeeper.activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.housekeeper.R;
import com.example.housekeeper.utils.ExistApplication;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashboardActivity extends BaseActivity {

    private SharedPreferences prefs;
    private static final String LOGIN_KEY = "loginKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_more)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        prefs = getSharedPreferences(LOGIN_KEY, 0);

        /*if (!prefs.contains("Key") ) {

            Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
            startActivity(intent);

        }*/

    }

    @Override
    public void onBackPressed() {
        new ExistApplication(this);
        int index = getFragmentManager().getBackStackEntryCount() - 1;
//        FragmentManager.BackStackEntry backEntry = (FragmentManager.BackStackEntry) getFragmentManager().getBackStackEntryAt(index);
        //  String tag = backEntry.getName();
        //       Toast.makeText(this, "dca"+index, Toast.LENGTH_SHORT).show();
//        Fragment fragment = getFragmentManager().findFragmentByTag(tag);
//
//        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
//            String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount()).getName();
//            Toast.makeText(this, "dca"+tag, Toast.LENGTH_SHORT).show();
//            return;
//        }else{
//            Toast.makeText(this, "dca", Toast.LENGTH_SHORT).show();
//        }
    }
}

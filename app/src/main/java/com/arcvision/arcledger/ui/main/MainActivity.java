package com.arcvision.arcledger.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.arcvision.arcledger.R;
import com.arcvision.arcledger.ui.customers.CustomersFragment;
import com.arcvision.arcledger.ui.dashboard.DashboardFragment;
import com.arcvision.arcledger.ui.reports.ReportsFragment;
import com.arcvision.arcledger.ui.settings.SettingsFragment;
import com.arcvision.arcledger.ui.transactions.TransactionsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView nav = findViewById(R.id.bottomNav);
        nav.setOnItemSelectedListener(item -> {
            Fragment fragment;
            int id = item.getItemId();
            if (id == R.id.menu_transactions) {
                fragment = new TransactionsFragment();
            } else if (id == R.id.menu_reports) {
                fragment = new ReportsFragment();
            } else if (id == R.id.menu_customers) {
                fragment = new CustomersFragment();
            } else if (id == R.id.menu_settings) {
                fragment = new SettingsFragment();
            } else {
                fragment = new DashboardFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
            return true;
        });

        nav.setSelectedItemId(R.id.menu_dashboard);
    }
}

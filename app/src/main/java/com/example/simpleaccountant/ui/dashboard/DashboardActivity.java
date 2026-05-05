package com.example.simpleaccountant.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simpleaccountant.R;
import com.example.simpleaccountant.data.dao.TransactionDao;
import com.example.simpleaccountant.ui.customers.CustomerListActivity;
import com.example.simpleaccountant.ui.reports.ReportsActivity;
import com.example.simpleaccountant.ui.suppliers.SupplierListActivity;
import com.example.simpleaccountant.ui.transactions.AddTransactionActivity;
import com.example.simpleaccountant.ui.transactions.TransactionListActivity;
import com.example.simpleaccountant.util.Constants;
import com.example.simpleaccountant.util.MoneyUtils;

public class DashboardActivity extends AppCompatActivity {
    private TransactionDao dao;
    private TextView income, expenses, balance, todayIncome, todayExpenses, totalTx;

    @Override protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_dashboard);dao=new TransactionDao(this);
        income=findViewById(R.id.tvTotalIncome);expenses=findViewById(R.id.tvTotalExpenses);balance=findViewById(R.id.tvBalance);todayIncome=findViewById(R.id.tvTodayIncome);todayExpenses=findViewById(R.id.tvTodayExpenses);totalTx=findViewById(R.id.tvTotalTransactions);
        findViewById(R.id.btnAddIncome).setOnClickListener(v->openAdd(Constants.TYPE_INCOME));
        findViewById(R.id.btnAddExpense).setOnClickListener(v->openAdd(Constants.TYPE_EXPENSE));
        findViewById(R.id.btnTransactions).setOnClickListener(v->startActivity(new Intent(this, TransactionListActivity.class)));
        findViewById(R.id.btnCustomers).setOnClickListener(v->startActivity(new Intent(this, CustomerListActivity.class)));
        findViewById(R.id.btnSuppliers).setOnClickListener(v->startActivity(new Intent(this, SupplierListActivity.class)));
        findViewById(R.id.btnReports).setOnClickListener(v->startActivity(new Intent(this, ReportsActivity.class)));
    }
    @Override protected void onResume(){super.onResume();income.setText(MoneyUtils.format(dao.getTotalIncome()));expenses.setText(MoneyUtils.format(dao.getTotalExpenses()));balance.setText(MoneyUtils.format(dao.getBalance()));todayIncome.setText(MoneyUtils.format(dao.getTodayIncome()));todayExpenses.setText(MoneyUtils.format(dao.getTodayExpenses()));totalTx.setText(String.valueOf(dao.getAllTransactions().size()));}
    private void openAdd(String type){Intent i=new Intent(this, AddTransactionActivity.class);i.putExtra("type",type);startActivity(i);} }

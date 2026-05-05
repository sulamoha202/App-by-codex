package com.arcvision.arcledger.ui.reports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.arcvision.arcledger.R;
import com.arcvision.arcledger.data.dao.TransactionDao;
import com.arcvision.arcledger.util.MoneyUtils;

public class ReportsFragment extends Fragment {
    @Nullable @Override public View onCreateView(@NonNull LayoutInflater i,@Nullable ViewGroup c,@Nullable Bundle b){View v=i.inflate(R.layout.fragment_reports,c,false);TransactionDao dao=new TransactionDao(requireContext());((TextView)v.findViewById(R.id.tvMonthlyIncome)).setText("Monthly Income\n"+MoneyUtils.format(dao.getTotalIncome()));((TextView)v.findViewById(R.id.tvMonthlyExpenses)).setText("Monthly Expenses\n"+MoneyUtils.format(dao.getTotalExpenses()));return v;}
}

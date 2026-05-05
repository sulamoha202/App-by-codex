package com.arcvision.arcledger.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.arcvision.arcledger.R;
import com.arcvision.arcledger.data.dao.TransactionDao;
import com.arcvision.arcledger.data.model.Transaction;
import com.arcvision.arcledger.util.MoneyUtils;

import java.util.List;

public class DashboardFragment extends Fragment {
    @Nullable @Override public View onCreateView(@NonNull LayoutInflater i,@Nullable ViewGroup c,@Nullable Bundle b){
        View v=i.inflate(R.layout.fragment_dashboard,c,false);
        TransactionDao dao=new TransactionDao(requireContext());
        ((TextView)v.findViewById(R.id.tvBalance)).setText(MoneyUtils.format(dao.getBalance()));
        LinearLayout container=v.findViewById(R.id.recentContainer);
        List<Transaction> list=dao.getAllTransactions();
        for(int x=0;x<Math.min(3,list.size());x++){TextView tv=new TextView(requireContext());tv.setText(list.get(x).title+" • "+MoneyUtils.format(list.get(x).amount));tv.setPadding(0,8,0,8);container.addView(tv);}return v;
    }
}

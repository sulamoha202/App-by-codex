package com.arcvision.arcledger.ui.transactions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arcvision.arcledger.R;
import com.arcvision.arcledger.data.dao.TransactionDao;
import com.arcvision.arcledger.data.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionsFragment extends Fragment {
    private final List<Transaction> all = new ArrayList<>();
    @Nullable @Override public View onCreateView(@NonNull LayoutInflater i,@Nullable ViewGroup c,@Nullable Bundle b){
        View v=i.inflate(R.layout.fragment_transactions,c,false);
        RecyclerView rv=v.findViewById(R.id.rvTransactions);
        EditText search=v.findViewById(R.id.etSearchTransactions);
        TransactionListAdapter adapter=new TransactionListAdapter();
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv.setAdapter(adapter);
        all.addAll(new TransactionDao(requireContext()).getAllTransactions());
        adapter.submit(all);
        search.addTextChangedListener(new android.text.TextWatcher(){public void beforeTextChanged(CharSequence s,int st,int c1,int c2){} public void onTextChanged(CharSequence s,int st,int b1,int c2){List<Transaction> f=new ArrayList<>();String k=s.toString().toLowerCase();for(Transaction t:all){if(t.title.toLowerCase().contains(k)||t.category.toLowerCase().contains(k))f.add(t);}adapter.submit(f);} public void afterTextChanged(android.text.Editable e){}});
        return v;
    }
}

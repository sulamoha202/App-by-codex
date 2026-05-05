package com.arcvision.arcledger.ui.customers;

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
import com.arcvision.arcledger.data.dao.CustomerDao;
import com.arcvision.arcledger.data.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomersFragment extends Fragment {
    @Nullable @Override public View onCreateView(@NonNull LayoutInflater i,@Nullable ViewGroup c,@Nullable Bundle b){
        View v=i.inflate(R.layout.fragment_customers,c,false);
        List<Customer> all=new ArrayList<>(new CustomerDao(requireContext()).getAllCustomers());
        CustomerListAdapter adapter=new CustomerListAdapter();
        RecyclerView rv=v.findViewById(R.id.rvCustomers); rv.setLayoutManager(new LinearLayoutManager(requireContext())); rv.setAdapter(adapter); adapter.submit(all);
        EditText et=v.findViewById(R.id.etSearchCustomers);
        et.addTextChangedListener(new android.text.TextWatcher(){public void beforeTextChanged(CharSequence s,int a,int b1,int c2){} public void onTextChanged(CharSequence s,int a,int b1,int c2){String k=s.toString().toLowerCase();List<Customer> f=new ArrayList<>();for(Customer cc:all){if(cc.fullName.toLowerCase().contains(k)||(cc.phone!=null&&cc.phone.contains(k)))f.add(cc);}adapter.submit(f);} public void afterTextChanged(android.text.Editable e){}});
        return v;
    }
}

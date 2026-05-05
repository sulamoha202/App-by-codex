package com.arcvision.arcledger.ui.customers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arcvision.arcledger.R;
import com.arcvision.arcledger.data.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {
    private final List<Customer> items = new ArrayList<>();
    public void submit(List<Customer> data){items.clear();items.addAll(data);notifyDataSetChanged();}
    @NonNull @Override public ViewHolder onCreateViewHolder(@NonNull ViewGroup p,int v){return new ViewHolder(LayoutInflater.from(p.getContext()).inflate(R.layout.item_customer,p,false));}
    @Override public void onBindViewHolder(@NonNull ViewHolder h,int p){Customer c=items.get(p);h.name.setText(c.fullName);h.phone.setText(c.phone==null?"-":c.phone);} @Override public int getItemCount(){return items.size();}
    static class ViewHolder extends RecyclerView.ViewHolder{TextView name,phone;ViewHolder(@NonNull View i){super(i);name=i.findViewById(R.id.tvName);phone=i.findViewById(R.id.tvPhone);}}
}

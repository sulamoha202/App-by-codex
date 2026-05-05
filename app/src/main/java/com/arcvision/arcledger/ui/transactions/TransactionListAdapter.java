package com.arcvision.arcledger.ui.transactions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arcvision.arcledger.R;
import com.arcvision.arcledger.data.model.Transaction;
import com.arcvision.arcledger.util.MoneyUtils;

import java.util.ArrayList;
import java.util.List;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.ViewHolder> {
    private final List<Transaction> items = new ArrayList<>();

    public void submit(List<Transaction> data) {
        items.clear();
        items.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = items.get(position);
        holder.title.setText(transaction.title);
        holder.meta.setText(transaction.category + " • " + transaction.transactionDate);
        String signed = ("INCOME".equals(transaction.type) ? "+ " : "- ") + MoneyUtils.format(transaction.amount);
        holder.amount.setText(signed);
        holder.amount.setTextColor(holder.itemView.getResources().getColor(
                "INCOME".equals(transaction.type) ? R.color.income_green : R.color.expense_red));
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, meta, amount;
        ViewHolder(@NonNull View itemView) { super(itemView); title = itemView.findViewById(R.id.tvTitle); meta = itemView.findViewById(R.id.tvMeta); amount = itemView.findViewById(R.id.tvAmount);} }
}

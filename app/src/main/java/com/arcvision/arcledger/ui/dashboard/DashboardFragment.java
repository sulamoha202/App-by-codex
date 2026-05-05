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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        TransactionDao dao = new TransactionDao(requireContext());

        double totalIncome = dao.getTotalIncome();
        double totalExpense = dao.getTotalExpenses();
        double balance = totalIncome - totalExpense;

        ((TextView) root.findViewById(R.id.tvBalance)).setText(MoneyUtils.format(balance));

        bindSummaryCard(root.findViewById(R.id.cardIncome), "Total Income", MoneyUtils.format(totalIncome), R.color.income_green);
        bindSummaryCard(root.findViewById(R.id.cardExpense), "Total Expenses", MoneyUtils.format(totalExpense), R.color.expense_red);
        bindSummaryCard(root.findViewById(R.id.cardNet), "Net Balance", MoneyUtils.format(balance), R.color.arc_blue);

        bindAction(root.findViewById(R.id.actionIncome), "Add Income", android.R.drawable.ic_input_add, R.color.income_green);
        bindAction(root.findViewById(R.id.actionExpense), "Add Expense", android.R.drawable.ic_delete, R.color.expense_red);
        bindAction(root.findViewById(R.id.actionCustomer), "Add Customer", android.R.drawable.ic_menu_myplaces, R.color.arc_blue);
        bindAction(root.findViewById(R.id.actionReport), "View Reports", android.R.drawable.ic_menu_sort_by_size, R.color.midnight_blue);

        LinearLayout recentContainer = root.findViewById(R.id.recentContainer);
        recentContainer.removeAllViews();
        List<Transaction> transactions = dao.getAllTransactions();
        for (int i = 0; i < Math.min(4, transactions.size()); i++) {
            View row = inflater.inflate(R.layout.item_recent_transaction_dashboard, recentContainer, false);
            Transaction t = transactions.get(i);
            ((TextView) row.findViewById(R.id.tvRecentTitle)).setText(t.title);
            ((TextView) row.findViewById(R.id.tvRecentMeta)).setText(t.transactionDate + " • " + t.category);
            TextView amount = row.findViewById(R.id.tvRecentAmount);
            boolean income = "INCOME".equals(t.type);
            amount.setText((income ? "+" : "-") + MoneyUtils.format(t.amount));
            amount.setTextColor(getResources().getColor(income ? R.color.income_green : R.color.expense_red));
            recentContainer.addView(row);
        }

        return root;
    }

    private void bindSummaryCard(View card, String title, String amount, int colorRes) {
        ((TextView) card.findViewById(R.id.tvSummaryTitle)).setText(title);
        TextView amountView = card.findViewById(R.id.tvSummaryAmount);
        amountView.setText(amount);
        amountView.setTextColor(getResources().getColor(colorRes));
    }

    private void bindAction(View card, String label, int iconRes, int colorRes) {
        ((TextView) card.findViewById(R.id.tvActionLabel)).setText(label);
        android.widget.ImageView icon = card.findViewById(R.id.ivActionIcon);
        icon.setImageResource(iconRes);
        icon.setColorFilter(getResources().getColor(colorRes));
    }
}

package com.arcvision.arcledger.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.arcvision.arcledger.data.db.DatabaseHelper;
import com.arcvision.arcledger.data.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
    private final DatabaseHelper helper;

    public TransactionDao(Context context) {
        helper = new DatabaseHelper(context);
    }

    public long insertTransaction(Transaction transaction) {
        ContentValues values = new ContentValues();
        values.put("title", transaction.title);
        values.put("type", transaction.type);
        values.put("amount", transaction.amount);
        values.put("category", transaction.category);
        values.put("payment_method", transaction.paymentMethod);
        values.put("transaction_date", transaction.transactionDate);
        values.put("note", transaction.note);
        values.put("created_at", transaction.createdAt);
        values.put("updated_at", transaction.updatedAt);
        return helper.getWritableDatabase().insert("transactions", null, values);
    }

    public List<Transaction> getAllTransactions() {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "SELECT * FROM transactions ORDER BY id DESC", null);
        List<Transaction> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Transaction transaction = new Transaction();
            transaction.id = cursor.getLong(0);
            transaction.title = cursor.getString(1);
            transaction.type = cursor.getString(2);
            transaction.amount = cursor.getDouble(3);
            transaction.category = cursor.getString(4);
            transaction.paymentMethod = cursor.getString(5);
            transaction.transactionDate = cursor.getString(6);
            list.add(transaction);
        }
        cursor.close();
        return list;
    }

    public double getTotalIncome() {
        return sumByType("INCOME");
    }

    public double getTotalExpenses() {
        return sumByType("EXPENSE");
    }

    public double getBalance() {
        return getTotalIncome() - getTotalExpenses();
    }

    private double sumByType(String type) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "SELECT COALESCE(SUM(amount),0) FROM transactions WHERE type=?",
                new String[]{type});
        cursor.moveToFirst();
        double total = cursor.getDouble(0);
        cursor.close();
        return total;
    }
}

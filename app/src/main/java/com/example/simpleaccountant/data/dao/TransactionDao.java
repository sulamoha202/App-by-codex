package com.example.simpleaccountant.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.simpleaccountant.data.db.DatabaseHelper;
import com.example.simpleaccountant.data.model.Transaction;
import com.example.simpleaccountant.util.Constants;
import com.example.simpleaccountant.util.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionDao {
    private final DatabaseHelper dbHelper;
    public TransactionDao(Context context) { dbHelper = new DatabaseHelper(context); }
    public long insertTransaction(Transaction t){SQLiteDatabase db=dbHelper.getWritableDatabase();String now=DateUtils.now();ContentValues v=toValues(t);v.put("created_at",now);v.put("updated_at",now);return db.insert("transactions",null,v);}    
    public int updateTransaction(Transaction t){SQLiteDatabase db=dbHelper.getWritableDatabase();ContentValues v=toValues(t);v.put("updated_at",DateUtils.now());return db.update("transactions",v,"id=?",new String[]{String.valueOf(t.getId())});}
    public int deleteTransaction(long id){return dbHelper.getWritableDatabase().delete("transactions","id=?",new String[]{String.valueOf(id)});}    
    public Transaction getTransactionById(long id){Cursor c=dbHelper.getReadableDatabase().rawQuery("SELECT * FROM transactions WHERE id=?",new String[]{String.valueOf(id)});try{return c.moveToFirst()?map(c):null;}finally{c.close();}}
    public List<Transaction> getAllTransactions(){return list("SELECT * FROM transactions ORDER BY transaction_date DESC,id DESC",null);}    
    public List<Transaction> getTransactionsByType(String type){return list("SELECT * FROM transactions WHERE type=? ORDER BY transaction_date DESC",new String[]{type});}
    public List<Transaction> getTransactionsByDateRange(String s,String e){return list("SELECT * FROM transactions WHERE transaction_date BETWEEN ? AND ? ORDER BY transaction_date DESC",new String[]{s,e});}
    public double getTotalIncome(){return scalar("SELECT COALESCE(SUM(amount),0) FROM transactions WHERE type='INCOME'");}
    public double getTotalExpenses(){return scalar("SELECT COALESCE(SUM(amount),0) FROM transactions WHERE type='EXPENSE'");}
    public double getBalance(){return getTotalIncome()-getTotalExpenses();}
    public double getTodayIncome(){return scalar("SELECT COALESCE(SUM(amount),0) FROM transactions WHERE type='INCOME' AND transaction_date='"+ DateUtils.today()+"'");}
    public double getTodayExpenses(){return scalar("SELECT COALESCE(SUM(amount),0) FROM transactions WHERE type='EXPENSE' AND transaction_date='"+ DateUtils.today()+"'");}
    public Map<String,Double> getIncomeByCategory(){return groupByCategory(Constants.TYPE_INCOME);}    
    public Map<String,Double> getExpensesByCategory(){return groupByCategory(Constants.TYPE_EXPENSE);}    
    private ContentValues toValues(Transaction t){ContentValues v=new ContentValues();v.put("title",t.getTitle());v.put("type",t.getType());v.put("amount",t.getAmount());v.put("category",t.getCategory());v.put("payment_method",t.getPaymentMethod());v.put("transaction_date",t.getTransactionDate());v.put("note",t.getNote());return v;}
    private List<Transaction> list(String q,String[] a){Cursor c=dbHelper.getReadableDatabase().rawQuery(q,a);List<Transaction> out=new ArrayList<>();try{while(c.moveToNext())out.add(map(c));}finally{c.close();}return out;}
    private double scalar(String q){Cursor c=dbHelper.getReadableDatabase().rawQuery(q,null);try{return c.moveToFirst()?c.getDouble(0):0;}finally{c.close();}}
    private Map<String,Double> groupByCategory(String type){Cursor c=dbHelper.getReadableDatabase().rawQuery("SELECT category,SUM(amount) FROM transactions WHERE type=? GROUP BY category",new String[]{type});Map<String,Double> m=new HashMap<>();try{while(c.moveToNext())m.put(c.getString(0),c.getDouble(1));}finally{c.close();}return m;}
    private Transaction map(Cursor c){Transaction t=new Transaction();t.setId(c.getLong(c.getColumnIndexOrThrow("id")));t.setTitle(c.getString(c.getColumnIndexOrThrow("title")));t.setType(c.getString(c.getColumnIndexOrThrow("type")));t.setAmount(c.getDouble(c.getColumnIndexOrThrow("amount")));t.setCategory(c.getString(c.getColumnIndexOrThrow("category")));t.setPaymentMethod(c.getString(c.getColumnIndexOrThrow("payment_method")));t.setTransactionDate(c.getString(c.getColumnIndexOrThrow("transaction_date")));t.setNote(c.getString(c.getColumnIndexOrThrow("note")));t.setCreatedAt(c.getString(c.getColumnIndexOrThrow("created_at")));t.setUpdatedAt(c.getString(c.getColumnIndexOrThrow("updated_at")));return t;}
}

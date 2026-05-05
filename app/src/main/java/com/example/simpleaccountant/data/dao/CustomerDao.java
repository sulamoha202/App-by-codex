package com.example.simpleaccountant.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.simpleaccountant.data.db.DatabaseHelper;
import com.example.simpleaccountant.data.model.Customer;
import com.example.simpleaccountant.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private final DatabaseHelper dbHelper;
    public CustomerDao(Context context){dbHelper=new DatabaseHelper(context);}    
    public long insertCustomer(Customer c){SQLiteDatabase db=dbHelper.getWritableDatabase();ContentValues v=values(c);String now=DateUtils.now();v.put("created_at",now);v.put("updated_at",now);return db.insert("customers",null,v);}    
    public int updateCustomer(Customer c){ContentValues v=values(c);v.put("updated_at",DateUtils.now());return dbHelper.getWritableDatabase().update("customers",v,"id=?",new String[]{String.valueOf(c.getId())});}
    public int deleteCustomer(long id){return dbHelper.getWritableDatabase().delete("customers","id=?",new String[]{String.valueOf(id)});}    
    public Customer getCustomerById(long id){Cursor cur=dbHelper.getReadableDatabase().rawQuery("SELECT * FROM customers WHERE id=?",new String[]{String.valueOf(id)});try{return cur.moveToFirst()?map(cur):null;}finally{cur.close();}}
    public List<Customer> getAllCustomers(){return list("SELECT * FROM customers ORDER BY full_name",null);}    
    public List<Customer> searchCustomers(String k){String p="%"+k+"%";return list("SELECT * FROM customers WHERE full_name LIKE ? OR phone LIKE ? ORDER BY full_name",new String[]{p,p});}
    private ContentValues values(Customer c){ContentValues v=new ContentValues();v.put("full_name",c.getFullName());v.put("phone",c.getPhone());v.put("email",c.getEmail());v.put("address",c.getAddress());v.put("note",c.getNote());return v;}
    private List<Customer> list(String q,String[] a){Cursor c=dbHelper.getReadableDatabase().rawQuery(q,a);List<Customer> l=new ArrayList<>();try{while(c.moveToNext())l.add(map(c));}finally{c.close();}return l;}
    private Customer map(Cursor c){Customer x=new Customer();x.setId(c.getLong(c.getColumnIndexOrThrow("id")));x.setFullName(c.getString(c.getColumnIndexOrThrow("full_name")));x.setPhone(c.getString(c.getColumnIndexOrThrow("phone")));x.setEmail(c.getString(c.getColumnIndexOrThrow("email")));x.setAddress(c.getString(c.getColumnIndexOrThrow("address")));x.setNote(c.getString(c.getColumnIndexOrThrow("note")));x.setCreatedAt(c.getString(c.getColumnIndexOrThrow("created_at")));x.setUpdatedAt(c.getString(c.getColumnIndexOrThrow("updated_at")));return x;}
}

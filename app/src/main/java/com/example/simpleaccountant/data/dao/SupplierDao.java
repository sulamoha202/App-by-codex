package com.example.simpleaccountant.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.simpleaccountant.data.db.DatabaseHelper;
import com.example.simpleaccountant.data.model.Supplier;
import com.example.simpleaccountant.util.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class SupplierDao {
    private final DatabaseHelper dbHelper;
    public SupplierDao(Context context){dbHelper=new DatabaseHelper(context);}    
    public long insertSupplier(Supplier s){ContentValues v=values(s);String now=DateUtils.now();v.put("created_at",now);v.put("updated_at",now);return dbHelper.getWritableDatabase().insert("suppliers",null,v);}    
    public int updateSupplier(Supplier s){ContentValues v=values(s);v.put("updated_at",DateUtils.now());return dbHelper.getWritableDatabase().update("suppliers",v,"id=?",new String[]{String.valueOf(s.getId())});}
    public int deleteSupplier(long id){return dbHelper.getWritableDatabase().delete("suppliers","id=?",new String[]{String.valueOf(id)});}    
    public Supplier getSupplierById(long id){Cursor c=dbHelper.getReadableDatabase().rawQuery("SELECT * FROM suppliers WHERE id=?",new String[]{String.valueOf(id)});try{return c.moveToFirst()?map(c):null;}finally{c.close();}}
    public List<Supplier> getAllSuppliers(){return list("SELECT * FROM suppliers ORDER BY name",null);}    
    public List<Supplier> searchSuppliers(String k){String p="%"+k+"%";return list("SELECT * FROM suppliers WHERE name LIKE ? OR phone LIKE ? ORDER BY name",new String[]{p,p});}
    private ContentValues values(Supplier s){ContentValues v=new ContentValues();v.put("name",s.getName());v.put("phone",s.getPhone());v.put("email",s.getEmail());v.put("address",s.getAddress());v.put("note",s.getNote());return v;}
    private List<Supplier> list(String q,String[] a){Cursor c=dbHelper.getReadableDatabase().rawQuery(q,a);List<Supplier> l=new ArrayList<>();try{while(c.moveToNext())l.add(map(c));}finally{c.close();}return l;}
    private Supplier map(Cursor c){Supplier s=new Supplier();s.setId(c.getLong(c.getColumnIndexOrThrow("id")));s.setName(c.getString(c.getColumnIndexOrThrow("name")));s.setPhone(c.getString(c.getColumnIndexOrThrow("phone")));s.setEmail(c.getString(c.getColumnIndexOrThrow("email")));s.setAddress(c.getString(c.getColumnIndexOrThrow("address")));s.setNote(c.getString(c.getColumnIndexOrThrow("note")));s.setCreatedAt(c.getString(c.getColumnIndexOrThrow("created_at")));s.setUpdatedAt(c.getString(c.getColumnIndexOrThrow("updated_at")));return s;}
}

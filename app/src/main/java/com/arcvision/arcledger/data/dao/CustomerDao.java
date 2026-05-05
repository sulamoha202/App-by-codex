package com.arcvision.arcledger.data.dao;

import android.content.Context;
import android.database.Cursor;

import com.arcvision.arcledger.data.db.DatabaseHelper;
import com.arcvision.arcledger.data.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private final DatabaseHelper helper;

    public CustomerDao(Context context) {
        helper = new DatabaseHelper(context);
    }

    public List<Customer> getAllCustomers() {
        Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * FROM customers", null);
        List<Customer> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Customer customer = new Customer();
            customer.id = cursor.getLong(0);
            customer.fullName = cursor.getString(1);
            customer.phone = cursor.getString(2);
            list.add(customer);
        }
        cursor.close();
        return list;
    }
}

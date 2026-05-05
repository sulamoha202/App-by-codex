package com.arcvision.arcledger.data.dao;

import android.content.Context;
import android.database.Cursor;

import com.arcvision.arcledger.data.db.DatabaseHelper;
import com.arcvision.arcledger.data.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierDao {
    private final DatabaseHelper helper;

    public SupplierDao(Context context) {
        helper = new DatabaseHelper(context);
    }

    public List<Supplier> getAllSuppliers() {
        Cursor cursor = helper.getReadableDatabase().rawQuery("SELECT * FROM suppliers", null);
        List<Supplier> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Supplier supplier = new Supplier();
            supplier.id = cursor.getLong(0);
            supplier.name = cursor.getString(1);
            supplier.phone = cursor.getString(2);
            list.add(supplier);
        }
        cursor.close();
        return list;
    }
}

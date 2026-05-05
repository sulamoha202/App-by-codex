package com.example.simpleaccountant.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.simpleaccountant.util.Constants;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) { super(context, Constants.DB_NAME, null, Constants.DB_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE transactions (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT NOT NULL,type TEXT NOT NULL,amount REAL NOT NULL,category TEXT NOT NULL,payment_method TEXT NOT NULL,transaction_date TEXT NOT NULL,note TEXT,created_at TEXT NOT NULL,updated_at TEXT NOT NULL)");
        db.execSQL("CREATE TABLE customers (id INTEGER PRIMARY KEY AUTOINCREMENT,full_name TEXT NOT NULL,phone TEXT,email TEXT,address TEXT,note TEXT,created_at TEXT NOT NULL,updated_at TEXT NOT NULL)");
        db.execSQL("CREATE TABLE suppliers (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,phone TEXT,email TEXT,address TEXT,note TEXT,created_at TEXT NOT NULL,updated_at TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS transactions");
        db.execSQL("DROP TABLE IF EXISTS customers");
        db.execSQL("DROP TABLE IF EXISTS suppliers");
        onCreate(db);
    }
}

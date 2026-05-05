package com.arcvision.arcledger.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.arcvision.arcledger.util.Constants;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE transactions (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT NOT NULL,type TEXT NOT NULL,amount REAL NOT NULL,category TEXT NOT NULL,payment_method TEXT NOT NULL,transaction_date TEXT NOT NULL,note TEXT,created_at TEXT NOT NULL,updated_at TEXT NOT NULL)");
        db.execSQL("CREATE TABLE customers (id INTEGER PRIMARY KEY AUTOINCREMENT,full_name TEXT NOT NULL,phone TEXT,email TEXT,address TEXT,note TEXT,created_at TEXT NOT NULL,updated_at TEXT NOT NULL)");
        db.execSQL("CREATE TABLE suppliers (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,phone TEXT,email TEXT,address TEXT,note TEXT,created_at TEXT NOT NULL,updated_at TEXT NOT NULL)");
        db.execSQL("CREATE TABLE app_settings (id INTEGER PRIMARY KEY AUTOINCREMENT,business_name TEXT,owner_name TEXT,currency TEXT DEFAULT 'MAD',created_at TEXT NOT NULL,updated_at TEXT NOT NULL)");
        seed(db);
    }

    private void seed(SQLiteDatabase db) {
        String n = "2026-05-05 10:00:00";
        db.execSQL("INSERT INTO app_settings (business_name,owner_name,currency,created_at,updated_at) VALUES ('Arc Shop','Owner','MAD','" + n + "','" + n + "')");
        db.execSQL("INSERT INTO transactions (title,type,amount,category,payment_method,transaction_date,note,created_at,updated_at) VALUES ('Daily Sales','INCOME',850,'Sales','CASH','2026-05-01','Counter sales','" + n + "','" + n + "'),('Service Fee','INCOME',1200,'Service','BANK_TRANSFER','2026-05-02','Repair service','" + n + "','" + n + "'),('Salary','INCOME',3000,'Salary','BANK_TRANSFER','2026-05-03','','" + n + "','" + n + "'),('Investment Return','INCOME',500,'Investment','CARD','2026-05-04','','" + n + "','" + n + "'),('Gift','INCOME',200,'Gift','OTHER','2026-05-05','','" + n + "','" + n + "'),('Rent','EXPENSE',1000,'Rent','BANK_TRANSFER','2026-05-01','','" + n + "','" + n + "'),('Electricity','EXPENSE',220,'Electricity','CASH','2026-05-02','','" + n + "','" + n + "'),('Internet','EXPENSE',140,'Internet','CARD','2026-05-03','','" + n + "','" + n + "'),('Supplies','EXPENSE',330,'Supplies','CASH','2026-05-04','','" + n + "','" + n + "'),('Food','EXPENSE',90,'Food','MOBILE_PAYMENT','2026-05-05','','" + n + "','" + n + "')");
        db.execSQL("INSERT INTO customers (full_name,phone,email,address,note,created_at,updated_at) VALUES ('Amina Karim','+212610001001','amina@shop.com','Casablanca','','" + n + "','" + n + "'),('Youssef Hana','+212610001002','youssef@shop.com','Rabat','','" + n + "','" + n + "'),('Samir Naji','+212610001003','samir@shop.com','Marrakesh','','" + n + "','" + n + "'),('Nora Idris','+212610001004','nora@shop.com','Tangier','','" + n + "','" + n + "'),('Hakim Ali','+212610001005','hakim@shop.com','Agadir','','" + n + "','" + n + "')");
        db.execSQL("INSERT INTO suppliers (name,phone,email,address,note,created_at,updated_at) VALUES ('Blue Office','+212620001001','blue@sup.com','Casablanca','','" + n + "','" + n + "'),('Power Utility','+212620001002','power@sup.com','Rabat','','" + n + "','" + n + "'),('Fast Internet','+212620001003','net@sup.com','Rabat','','" + n + "','" + n + "'),('Fresh Foods','+212620001004','fresh@sup.com','Marrakesh','','" + n + "','" + n + "'),('City Transport','+212620001005','city@sup.com','Casablanca','','" + n + "','" + n + "')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS transactions");
        db.execSQL("DROP TABLE IF EXISTS customers");
        db.execSQL("DROP TABLE IF EXISTS suppliers");
        db.execSQL("DROP TABLE IF EXISTS app_settings");
        onCreate(db);
    }
}

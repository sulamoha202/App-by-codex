package com.example.simpleaccountant.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.simpleaccountant.util.Constants;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE transactions (id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT NOT NULL,type TEXT NOT NULL,amount REAL NOT NULL,category TEXT NOT NULL,payment_method TEXT NOT NULL,transaction_date TEXT NOT NULL,note TEXT,created_at TEXT NOT NULL,updated_at TEXT NOT NULL)");
        db.execSQL("CREATE TABLE customers (id INTEGER PRIMARY KEY AUTOINCREMENT,full_name TEXT NOT NULL,phone TEXT,email TEXT,address TEXT,note TEXT,created_at TEXT NOT NULL,updated_at TEXT NOT NULL)");
        db.execSQL("CREATE TABLE suppliers (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL,phone TEXT,email TEXT,address TEXT,note TEXT,created_at TEXT NOT NULL,updated_at TEXT NOT NULL)");
        seedData(db);
    }

    private void seedData(SQLiteDatabase db) {
        String now = "2026-05-05 10:00:00";

        db.execSQL("INSERT INTO transactions (title,type,amount,category,payment_method,transaction_date,note,created_at,updated_at) VALUES " +
                "('Product Sale #001','INCOME',1500.00,'Sales','CASH','2026-05-01','Walk-in sale','" + now + "','" + now + "')," +
                "('Website Service','INCOME',2200.00,'Service','BANK_TRANSFER','2026-05-02','Freelance payment','" + now + "','" + now + "')," +
                "('Monthly Salary','INCOME',3500.00,'Salary','BANK_TRANSFER','2026-05-03','Main job salary','" + now + "','" + now + "')," +
                "('Dividend','INCOME',640.00,'Investment','CARD','2026-05-04','Portfolio dividend','" + now + "','" + now + "')," +
                "('Misc Income','INCOME',300.00,'Other','OTHER','2026-05-05','Small refund','" + now + "','" + now + "')");

        db.execSQL("INSERT INTO transactions (title,type,amount,category,payment_method,transaction_date,note,created_at,updated_at) VALUES " +
                "('Office Rent','EXPENSE',1200.00,'Rent','BANK_TRANSFER','2026-05-01','Monthly rent','" + now + "','" + now + "')," +
                "('Electricity Bill','EXPENSE',280.00,'Electricity','CASH','2026-05-02','Utility payment','" + now + "','" + now + "')," +
                "('Internet Bill','EXPENSE',180.00,'Internet','CARD','2026-05-03','Fiber plan','" + now + "','" + now + "')," +
                "('Office Supplies','EXPENSE',450.00,'Supplies','CASH','2026-05-04','Printer and paper','" + now + "','" + now + "')," +
                "('Transport Cost','EXPENSE',130.00,'Transport','OTHER','2026-05-05','Delivery run','" + now + "','" + now + "')");

        db.execSQL("INSERT INTO customers (full_name,phone,email,address,note,created_at,updated_at) VALUES " +
                "('Amine Zahra','+212600100001','amine@example.com','Casablanca','Regular customer','" + now + "','" + now + "')," +
                "('Sara Idrissi','+212600100002','sara@example.com','Rabat','Pays on time','" + now + "','" + now + "')," +
                "('Youssef Alami','+212600100003','youssef@example.com','Marrakech','Wholesale buyer','" + now + "','" + now + "')");

        db.execSQL("INSERT INTO suppliers (name,phone,email,address,note,created_at,updated_at) VALUES " +
                "('Atlas Supplies','+212600200001','atlas@suppliers.com','Casablanca','Stationery supplier','" + now + "','" + now + "')," +
                "('Maghreb Utilities','+212600200002','billing@maghreb.com','Rabat','Internet provider','" + now + "','" + now + "')," +
                "('Casa Maintenance','+212600200003','help@cmaintenance.com','Casablanca','Repairs and maintenance','" + now + "','" + now + "')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS transactions");
        db.execSQL("DROP TABLE IF EXISTS customers");
        db.execSQL("DROP TABLE IF EXISTS suppliers");
        onCreate(db);
    }
}

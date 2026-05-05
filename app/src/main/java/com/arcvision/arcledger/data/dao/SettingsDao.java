package com.arcvision.arcledger.data.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.arcvision.arcledger.data.db.DatabaseHelper;
import com.arcvision.arcledger.data.model.AppSettings;

public class SettingsDao {
    private final DatabaseHelper helper;

    public SettingsDao(Context context) {
        helper = new DatabaseHelper(context);
    }

    public AppSettings getSettings() {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "SELECT * FROM app_settings ORDER BY id LIMIT 1", null);
        AppSettings settings = new AppSettings();
        if (cursor.moveToFirst()) {
            settings.id = cursor.getLong(0);
            settings.businessName = cursor.getString(1);
            settings.ownerName = cursor.getString(2);
            settings.currency = cursor.getString(3);
        }
        cursor.close();
        return settings;
    }

    public int updateSettings(AppSettings settings) {
        ContentValues values = new ContentValues();
        values.put("business_name", settings.businessName);
        values.put("owner_name", settings.ownerName);
        values.put("currency", settings.currency);
        values.put("updated_at", settings.updatedAt);
        return helper.getWritableDatabase().update("app_settings", values, "id=?",
                new String[]{String.valueOf(settings.id)});
    }
}

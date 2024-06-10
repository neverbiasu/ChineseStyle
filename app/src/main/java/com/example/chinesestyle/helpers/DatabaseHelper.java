package com.example.chinesestyle.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.chinesestyle.R;
import com.example.chinesestyle.models.Festival;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "chinesestyle.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_FESTIVALS = "festivals";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_ORIGIN = "origin";
    private static final String COLUMN_VIDEO_RESOURCE = "video_resource";
    private static final String COLUMN_ICON_RESOURCE = "icon_resource";
    private static final String COLUMN_CUSTOM_ICON = "custom_icon";
    private static final String COLUMN_CUSTOM_DESC = "custom_desc";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FESTIVALS_TABLE = "CREATE TABLE " + TABLE_FESTIVALS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_ORIGIN + " TEXT,"
                + COLUMN_VIDEO_RESOURCE + " INTEGER,"
                + COLUMN_ICON_RESOURCE + " INTEGER,"
                + COLUMN_CUSTOM_ICON + " TEXT,"
                + COLUMN_CUSTOM_DESC + " TEXT"
                + ")";
        db.execSQL(CREATE_FESTIVALS_TABLE);

        insertDummyFestivals(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FESTIVALS);
        onCreate(db);
    }

    private void insertDummyFestivals(SQLiteDatabase db) {
        insertFestival(db, "春节", "农历正月初一", "起源于祭祀神灵、祭祖的活动...", R.raw.spring_festival, R.drawable.icon_spring_festival, "icon_spring_festival", "挂灯笼：代表光明，驱散邪气...");
        insertFestival(db, "端午节", "农历五月初五", "纪念爱国诗人屈原...", R.raw.dragon_boat, R.drawable.icon_dragon_boat, "icon_dragon_boat", "吃粽子：代表团圆，缠绕的绳象征家人团聚...");
        // 添加更多节日...
    }

    private void insertFestival(SQLiteDatabase db, String name, String date, String origin, int videoResource, int iconResource, String customIcon, String customDesc) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_ORIGIN, origin);
        values.put(COLUMN_VIDEO_RESOURCE, videoResource);
        values.put(COLUMN_ICON_RESOURCE, iconResource);
        values.put(COLUMN_CUSTOM_ICON, customIcon);
        values.put(COLUMN_CUSTOM_DESC, customDesc);
        db.insert(TABLE_FESTIVALS, null, values);
    }

    public List<Festival> getAllFestivals() {
        List<Festival> festivals = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FESTIVALS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
                int dateIndex = cursor.getColumnIndex(COLUMN_DATE);
                int originIndex = cursor.getColumnIndex(COLUMN_ORIGIN);
                int videoResourceIndex = cursor.getColumnIndex(COLUMN_VIDEO_RESOURCE);
                int iconResourceIndex = cursor.getColumnIndex(COLUMN_ICON_RESOURCE);
                int customIconIndex = cursor.getColumnIndex(COLUMN_CUSTOM_ICON);
                int customDescIndex = cursor.getColumnIndex(COLUMN_CUSTOM_DESC);

                if (idIndex != -1 && nameIndex != -1 && dateIndex != -1 && originIndex != -1 && videoResourceIndex != -1 && iconResourceIndex != -1 && customIconIndex != -1 && customDescIndex != -1) {
                    festivals.add(new Festival(
                            cursor.getInt(idIndex),
                            cursor.getString(nameIndex),
                            cursor.getString(dateIndex),
                            cursor.getString(originIndex),
                            cursor.getInt(videoResourceIndex),
                            cursor.getInt(iconResourceIndex),
                            cursor.getString(customIconIndex),
                            cursor.getString(customDescIndex)
                    ));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return festivals;
    }

    public Festival getFestivalById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FESTIVALS, null, COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int dateIndex = cursor.getColumnIndex(COLUMN_DATE);
            int originIndex = cursor.getColumnIndex(COLUMN_ORIGIN);
            int videoResourceIndex = cursor.getColumnIndex(COLUMN_VIDEO_RESOURCE);
            int iconResourceIndex = cursor.getColumnIndex(COLUMN_ICON_RESOURCE);
            int customIconIndex = cursor.getColumnIndex(COLUMN_CUSTOM_ICON);
            int customDescIndex = cursor.getColumnIndex(COLUMN_CUSTOM_DESC);

            if (idIndex != -1 && nameIndex != -1 && dateIndex != -1 && originIndex != -1 && videoResourceIndex != -1 && iconResourceIndex != -1 && customIconIndex != -1 && customDescIndex != -1) {
                return new Festival(
                        cursor.getInt(idIndex),
                        cursor.getString(nameIndex),
                        cursor.getString(dateIndex),
                        cursor.getString(originIndex),
                        cursor.getInt(videoResourceIndex),
                        cursor.getInt(iconResourceIndex),
                        cursor.getString(customIconIndex),
                        cursor.getString(customDescIndex)
                );
            }
        }
        cursor.close();
        return null;
    }
    // 添加getFestivalById等其他方法...
}

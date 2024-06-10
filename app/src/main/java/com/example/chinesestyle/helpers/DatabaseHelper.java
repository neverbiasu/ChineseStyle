package com.example.chinesestyle.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.chinesestyle.R;
import com.example.chinesestyle.models.Classic;
import com.example.chinesestyle.models.Festival;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "chinesestyle.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_FESTIVALS = "festivals";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_ORIGIN = "origin";
    private static final String COLUMN_VIDEO_RESOURCE = "video_resource";
    private static final String COLUMN_ICON_RESOURCE = "icon_resource";
    private static final String COLUMN_CUSTOM_ICON = "custom_icon";
    private static final String COLUMN_CUSTOM_DESC = "custom_desc";

    private static final String TABLE_CLASSICS = "classics";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_CONTENT = "content";
    private static final String COLUMN_AUDIOURL = "audioUrl";
    private static final String COLUMN_CATEGORY = "category";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CLASSICS_TABLE = "CREATE TABLE " + TABLE_CLASSICS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_AUTHOR + " TEXT,"
                + COLUMN_CONTENT + " TEXT,"
                + COLUMN_AUDIOURL + " TEXT,"
                + COLUMN_CATEGORY + " TEXT"
                + ")";
        db.execSQL(CREATE_CLASSICS_TABLE);
        Log.d("DatabaseHelper", "Classics table has been created.");
        insertDummyClassics(db);

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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASSICS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FESTIVALS);
        onCreate(db);
    }

    private void insertDummyClassics(SQLiteDatabase db) {
        insertClassic(db, "静夜思", "李白", "床前明月光，疑是地上霜。举头望明月，低头思故乡。", "https://www.example.com/audio1.mp3", "诗");
        insertClassic(db, "将进酒", "李白", "君不见黄河之水天上来，奔流到海不复回。君不见高堂明镜悲白发，朝如青丝暮成雪。", "https://www.example.com/audio2.mp3", "诗");
        // 添加更多诗词...
    }

    private void insertDummyFestivals(SQLiteDatabase db) {
        insertFestival(db, "春节", "农历正月初一", "起源于祭祀神灵、祭祖的活动...", R.raw.spring_festival, R.drawable.icon_spring_festival, "icon_spring_festival", "挂灯笼：代表光明，驱散邪气...");
        insertFestival(db, "端午节", "农历五月初五", "纪念爱国诗人屈原...", R.raw.dragon_boat, R.drawable.icon_dragon_boat, "icon_dragon_boat", "吃粽子：代表团圆，缠绕的绳象征家人团聚...");
        // 添加更多节日...
    }

    private void insertClassic(SQLiteDatabase db, String title, String author, String content, String audioUrl, String category) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_AUTHOR, author);
        values.put(COLUMN_CONTENT, content);
        values.put(COLUMN_AUDIOURL, audioUrl);
        values.put(COLUMN_CATEGORY, category);
        db.insert(TABLE_CLASSICS, null, values);
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

    public List<Classic> getAllClassics() {
        List<Classic> classics = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("classics", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex("id");
                int titleIndex = cursor.getColumnIndex("title");
                int authorIndex = cursor.getColumnIndex("author");
                int contentIndex = cursor.getColumnIndex("content");
                int audioUrlIndex = cursor.getColumnIndex("audioUrl");
                int categoryIndex = cursor.getColumnIndex("category");
                if (idIndex != -1) {
                    classics.add(new Classic(
                            cursor.getInt(idIndex),
                            cursor.getString(titleIndex),
                            cursor.getString(authorIndex),
                            cursor.getString(contentIndex),
                            cursor.getString(audioUrlIndex),
                            cursor.getString(categoryIndex)
                    ));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return classics;
    }

    public Classic getClassicById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("classics", null, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        Classic classic = null;
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("id");
            int titleIndex = cursor.getColumnIndex("title");
            int authorIndex = cursor.getColumnIndex("author");
            int contentIndex = cursor.getColumnIndex("content");
            int audioUrlIndex = cursor.getColumnIndex("audioUrl");
            int categoryIndex = cursor.getColumnIndex("category");
            if (idIndex != -1) {
                classic = new Classic(
                        cursor.getInt(idIndex),
                        cursor.getString(titleIndex),
                        cursor.getString(authorIndex),
                        cursor.getString(contentIndex),
                        cursor.getString(audioUrlIndex),
                        cursor.getString(categoryIndex)
                );
            }
        }
        cursor.close();
        return classic;
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

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
    private static final int DATABASE_VERSION = 3;

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
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_NICKNAME = "nickname";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_CONTACT = "contact";
    private static final String COLUMN_PASSWORD = "password";

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

        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NICKNAME + " TEXT UNIQUE,"
                + COLUMN_GENDER + " TEXT,"
                + COLUMN_CONTACT + " TEXT UNIQUE,"
                + COLUMN_PASSWORD + " TEXT"
                + ")";
        db.execSQL(CREATE_USERS_TABLE);
        Log.d("DatabaseHelper", "Users table has been created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASSICS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FESTIVALS);
        onCreate(db);
    }

    // 用户注册
    public long registerUser(String nickname, String gender, String contact, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NICKNAME, nickname);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_CONTACT, contact);
        values.put(COLUMN_PASSWORD, password); // 注意：实际应用中应该哈希处理密码

        long userId = db.insert(TABLE_USERS, null, values);
        db.close();
        return userId;
    }

    // 用户登录
    public boolean loginUser(String loginId, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID};
        String selection = COLUMN_NICKNAME + "=? OR " + COLUMN_CONTACT + "=?";
        String[] selectionArgs = {loginId, loginId};

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if (count > 0) {
            // 用户存在，检查密码
            return checkPassword(loginId, password);
        } else {
            return false;
        }
    }

    // 检查密码
    private boolean checkPassword(String loginId, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_PASSWORD};
        String selection = COLUMN_NICKNAME + "=? OR " + COLUMN_CONTACT + "=?";
        String[] selectionArgs = {loginId, loginId};

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        String storedPassword = "";
        if (cursor.moveToFirst()) {
            storedPassword = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
        }
        cursor.close();
        db.close();

        // 注意：实际应用中应该比较哈希值，而不是明文密码
        return password.equals(storedPassword);
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

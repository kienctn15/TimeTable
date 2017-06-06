package com.example.falcon.timetable.DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.falcon.timetable.Login_Register.User;

/**
 * Created by Admin on 6/6/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="db_timetable_vnpt";
    private static final int DATABASE_VERSION=1;

    // Bảng USER
    public static final String TABLE_NAME_USER="tbl_user";
    public static final String KEY_ID="id";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD="password";

    Context context;
    SQLiteDatabase db;


    // Tạo bảng USER
    private static final String CREATE_TABLE_USER=" CREATE TABLE " + TABLE_NAME_USER + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_USERNAME + " TEXT , "
            + KEY_PASSWORD + " TEXT )";

    private static final String DROP_TABLE_TKB="DROP TABLE IF EXISTS "+ TABLE_NAME_USER;


    public DBHandler(Context context) {
        super(context,DATABASE_NAME , null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_TKB);
        onCreate(db);
    }

    public void open() {
        try {
            db = getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (db != null && db.isOpen()) {
            try {
                db.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insert_table_user(User user){
        open();
        ContentValues values=new ContentValues();
        values.put(KEY_USERNAME,user.getUsername());
        values.put(KEY_PASSWORD,user.getPassword());


        if(db.insert(TABLE_NAME_USER,null,values)!=-1){
              Toast.makeText(context,"Thêm Thành Công!" ,Toast.LENGTH_SHORT).show();

        }else{
              Toast.makeText(context,"Thêm Thất Bại!",Toast.LENGTH_SHORT).show();
        }

        close();
    }

    public boolean check_login(String username, String password){
        open();
        String selection = KEY_USERNAME + " = ?" + " AND " + KEY_PASSWORD + " = ?";
        Cursor cursor = db.query(TABLE_NAME_USER,null,selection,new String[]{username,password},null,null,null);
        if(cursor.getCount()>0)
            return true;
        return false;

    }

    public boolean check_register(String username){
        open();
        Cursor cursor = db.query(TABLE_NAME_USER,null,KEY_USERNAME + " =? ",new String[]{username},null,null,null);
        if(cursor.getCount()>0)
            return false;
        return true;
    }

    public void delete_table_user(){
        open();
        if(db.delete(TABLE_NAME_USER,null,null)!=-1){
             Toast.makeText(context,"Xóa Thành Công " + TABLE_NAME_USER,Toast.LENGTH_SHORT).show();
        }else{
              Toast.makeText(context,"Xóa Thất Bại " + TABLE_NAME_USER,Toast.LENGTH_SHORT).show();
        }
        close();
    }

}

package com.example.falcon.timetable.DBHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.falcon.timetable.DanhSachCongViec.CongViec;
import com.example.falcon.timetable.Login_Register.User;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 6/6/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="db_timetable_vnpt_2";
    private static final int DATABASE_VERSION=1;

    public static final String KEY_ID="id";

    // Bảng USER
    public static final String TABLE_NAME_USER="tbl_user";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD="password";


    // Bảng Công việc
    public static final String TABLE_NAME_CONGVIEC="tbl_congviec";
    public static final String KEY_TITLE = "title";
    public static final String KEY_ADDRESS="address";
    public static final String KEY_DATE="date";
    public static final String KEY_TIME_START="timestart";
    public static final String KEY_TIME_END="timeend";
    public static final String KEY_NOTE="ghichu";

    Context context;
    SQLiteDatabase db;


    // Tạo bảng USER
    private static final String CREATE_TABLE_USER=" CREATE TABLE " + TABLE_NAME_USER + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_USERNAME + " TEXT , "
            + KEY_PASSWORD + " TEXT )";

    private static final String DROP_TABLE_USER="DROP TABLE IF EXISTS "+ TABLE_NAME_USER;

    // Tạo bảng Công Việc
    private static final String CREATE_TABLE_CONGVIEC=" CREATE TABLE " + TABLE_NAME_CONGVIEC+ " ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_TITLE + " TEXT , "
            + KEY_ADDRESS + " TEXT , "
            + KEY_DATE + " TEXT , "
            + KEY_TIME_START + " TEXT , "
            + KEY_TIME_END + " TEXT , "
            + KEY_NOTE + " TEXT )";
    private static final String DROP_TABLE_CONGVIEC="DROP TABLE IF EXISTS "+ TABLE_NAME_CONGVIEC;

    public DBHandler(Context context) {
        super(context,DATABASE_NAME , null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_CONGVIEC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_USER);
        db.execSQL(DROP_TABLE_CONGVIEC);
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


    // TABLE USER
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

    public void delete_user(String username){
        open();
        if(db.delete(TABLE_NAME_USER,KEY_USERNAME+ " =? ",new String[]{username})!=-1){
            Toast.makeText(context,"Xóa Thành Công " + TABLE_NAME_USER,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Xóa Thất Bại " + TABLE_NAME_USER,Toast.LENGTH_SHORT).show();
        }
        close();
    }

    // xoa ca bang user
    public void delete_table_user(){
        open();
        if(db.delete(TABLE_NAME_USER,null,null)!=-1){
             Toast.makeText(context,"Xóa Thành Công " + TABLE_NAME_USER,Toast.LENGTH_SHORT).show();
        }else{
              Toast.makeText(context,"Xóa Thất Bại " + TABLE_NAME_USER,Toast.LENGTH_SHORT).show();
        }
        close();
    }


    // TABLE CONG VIEC
    public void insert_table_congviec(CongViec congViec){
        open();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        ContentValues values=new ContentValues();
        values.put(KEY_TITLE,congViec.getTitle());
        values.put(KEY_ADDRESS,congViec.getAddress());
        values.put(KEY_DATE,dateFormat.format(congViec.getDate()));
        values.put(KEY_TIME_START,timeFormat.format(congViec.getTime_start()));
        values.put(KEY_TIME_END,timeFormat.format(congViec.getTime_end()));
        values.put(KEY_NOTE,congViec.getNote());

        if(db.insert(TABLE_NAME_CONGVIEC,null,values)!=-1){
            Toast.makeText(context,"Thêm Thành Công!" ,Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(context,"Thêm Thất Bại!",Toast.LENGTH_SHORT).show();
        }

        close();
    }

    public List<CongViec> get_all_congviec() throws ParseException {
        open();
        List<CongViec> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME_CONGVIEC,null,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                CongViec congViec = new CongViec();
                congViec.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
                congViec.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
                congViec.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                congViec.setTime_start(cursor.getString(cursor.getColumnIndex(KEY_TIME_START)));
                congViec.setTime_end(cursor.getString(cursor.getColumnIndex(KEY_TIME_END)));
                congViec.setNote(cursor.getString(cursor.getColumnIndex(KEY_NOTE)));

                list.add(congViec);
            }
        }
        close();

        return list;

    }

}

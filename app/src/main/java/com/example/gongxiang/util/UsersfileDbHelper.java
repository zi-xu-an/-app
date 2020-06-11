package com.example.gongxiang.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gongxiang.bean.Userfile;

import java.util.LinkedList;

/**
 * 学生数据库连接类
 * @author : autumn_leaf
 */
public class UsersfileDbHelper extends SQLiteOpenHelper {

    //定义用户信息表
    public static final String DB_NAME = "tb_usefile";
    /**创建用户信息表*/
    private static final String CREATE_STUDENT_DB = "create table tb_usefile(" +
            "id integer primary key autoincrement," +
            "stuNumber text," +
            "sex text,"+
            "name text,"+
            "stuName text)";

    public UsersfileDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 保存用户信息
     * @param userfile 用户信息对象
     */
    public void saveUserfile(Userfile userfile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("stuNumber", userfile.getStuNumber());
        values.put("stuName", userfile.getStuName());
        values.put("sex", userfile.getSex());
        values.put("name", userfile.getName());
//        values.put("picture",userfile.getPicture());
        db.insert(DB_NAME,null,values);
        values.clear();
    }

    /**
     * 通过用户名读取用户相关信息
     * @param stuNumber 用户名
     * @return 查询到的用户对象列表
     */
    public LinkedList<Userfile> readUserfile(String stuNumber) {
        LinkedList<Userfile> userfiles = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_usefile where stuNumber=?",new String[]{stuNumber});
        if(cursor.moveToFirst()) {
            do {
                //String stuNum = cursor.getString(cursor.getColumnIndex("stuNumber"));
                String stuName = cursor.getString(cursor.getColumnIndex("stuName"));
                String sex = cursor.getString(cursor.getColumnIndex("sex"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
//                byte[] picture = cursor.getBlob(cursor.getColumnIndex("picture"));
                Userfile userfile = new Userfile();
                //userfile.setStuNumber(stuNum);
                userfile.setStuName(stuName);
                userfile.setSex(sex);
                userfile.setName(name);
//                userfile.setPicture(picture);
                userfiles.add(userfile);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return userfiles;
    }


}

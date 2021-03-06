package com.dell.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" create table " + TABLE_NAME +"(ID INTEGER PRIMARY KEY ,NAME TEXT,SURNAME TEXT,MARKS INTEGER )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);

    }


    //Method for insert DAta in database.....

    public boolean insertData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);

        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;


    }

    // Method For Show database detail on Mobile....
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME, null);
        return res;

    }

    //Methods For Updatedata from Database....

    public boolean updateData(String id,String name,String surname,String marks){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);

        db.update(TABLE_NAME, contentValues, "ID = ?",new String[]{ id });
        return true;

    }

    //Methods For Delete From DataBase....

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{id});


    }
}

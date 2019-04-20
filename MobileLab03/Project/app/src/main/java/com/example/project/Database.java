package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "taskinator.db";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + User.TABLE_NAME +
                "("+User.COL1+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                User.COL2+" TEXT," +
                User.COL3+" TEXT)");

        db.execSQL("create table " + ProjectEntity.TABLE_NAME +
                "("+ProjectEntity.COL1+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                ProjectEntity.COL2+" TEXT," +
                ProjectEntity.COL3+" TEXT)");

        db.execSQL("create table " + ClientEntity.TABLE_NAME +
                "("+ClientEntity.COL1+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                ClientEntity.COL2+" TEXT,"+
                ClientEntity.COL3+" TEXT)");

        db.execSQL("create table " + TaskEntity.TABLE_NAME +
                "("+TaskEntity.COL1+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                TaskEntity.COL2+" TEXT," +
                TaskEntity.COL3+" DATE," +
                TaskEntity.COL4+" DATE," +
                TaskEntity.COL5+" DATE," +
                TaskEntity.COL6+" INTEGER," +
                TaskEntity.COL7+" INTEGER," +
                TaskEntity.COL8+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ProjectEntity.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ClientEntity.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TaskEntity.TABLE_NAME);
        onCreate(db);
    }

    public class User{
        public static final String TABLE_NAME = "users";
        public static final String COL1 = "ID";
        public static final String COL2 = "LOGIN";
        public static final String COL3 = "PASSWORD";
    }

    public class ProjectEntity {
        public static final String TABLE_NAME = "projects";
        public static final String COL1 = "ID";
        public static final String COL2 = "NAME";
        public static final String COL3 = "DESCRIPTION";
    }

    public class ClientEntity{
        public static final String TABLE_NAME = "clients";
        public static final String COL1 = "ID";
        public static final String COL2 = "NAME";
        public static final String COL3 = "DESCRIPTION";
    }

    public class TaskEntity{
        public static final String TABLE_NAME = "tasks";
        public static final String COL1 = "ID";
        public static final String COL2 = "NAME";
        public static final String COL3 = "START_DATE";
        public static final String COL4 = "END_DATE";
        public static final String COL5 = "FINISH_DATE";
        public static final String COL6 = "CLIENT_ID";
        public static final String COL7 = "TASK_ID";
        public static final String COL8 = "DESCRIPTION";
    }

    public boolean insertData_Project(String name, String description){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ProjectEntity.COL2, name);
        contentValues.put(ProjectEntity.COL3, description);

        Long result = db.insert(ProjectEntity.TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllData(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + tableName, null);
        return result;
    }


}

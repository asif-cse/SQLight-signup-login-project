package com.example.sqlightlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHalper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userdetails.bd";
    private static final String TABLE_NAME = "user_Details";
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final String EMAIL = "Email";
    private static final String USER_NAME = "Username";
    private static final String PASSWORD = "Password";
    private static final int VERSION = 1;
    private Context context;

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) NOT NULL, "+EMAIL+" TEXT NOT NULL, "+USER_NAME+" TEXT(255) NOT NULL, "+PASSWORD+" TEXT NOT NULL )";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    public DatabaseHalper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context, "Table create", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
            Toast.makeText(context, "Table Update", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Exception: "+e, Toast.LENGTH_SHORT).show();
        }
    }

    public Long insertData(UserDetails userDetails){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, userDetails.getId());
        contentValues.put(NAME, userDetails.getName());
        contentValues.put(USER_NAME, userDetails.getUsername());
        contentValues.put(EMAIL, userDetails.getEmail());
        contentValues.put(PASSWORD, userDetails.getPassword());

        Long rowId = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        return rowId;

    }
    public Boolean findPassword(String username, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        Boolean result = false;

        if (cursor.getCount()==0){

        }else {
            while (cursor.moveToNext()){
                String uname = cursor.getString(3);
                String upassword = cursor.getString(4);

                if (uname.equals(username) && upassword.equals(password)){
                    result =  true;
                    break;
                }
            }
        }
        return result;
    }
}

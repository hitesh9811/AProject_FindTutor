package com.dotvoid.drona;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by anuragjoshi on 19/09/17.
 */

public class DBHelp extends SQLiteOpenHelper
{
    public static final String USER_NAME="user_name";
    public static final String USER_PASS="pass";
    public static final String USER_MOBILE="mobile";
    public static final String USER_EMAIL="email";
    public static final String USER_FNAME="fname";
    public static final String USER_LNAME="lname";
    public static final String USER_CITY="city";
    public static final String USER_GENDER="gender";
    public static final String USER_AGE="age";

    public static final String TABLE_NAME="user_info";
    public static final String DATABASE_NAME="find.db";
    public static final int DATABASE_VERSION=1;
    public static final String CREATE_QUERY="CREATE TABLE " +TABLE_NAME+"("+USER_NAME+" TEXT,"+USER_PASS+" TEXT,"+USER_MOBILE+" TEXT,"+USER_EMAIL+" TEXT,"+USER_FNAME+" TEXT,"+USER_LNAME+" TEXT,"+USER_CITY+" TEXT,"+USER_GENDER+" TEXT,"+USER_AGE+" TEXT);";

    public DBHelp(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        //SQLiteDatabase.openOrCreateDatabase("/storage/CE5F-39C9/dataapp/"+DATABASE_NAME,null);
        Log.e("DataBaseOPeration","Database Created/Opened");
    }



    @Override
    public void onCreate(SQLiteDatabase dbase)
    {
        dbase.execSQL(CREATE_QUERY);
        Log.e("DataBaseOPeration","Table Created");
    }

    public void addInfo(String u, String p,String pn, String m,String f,String l,String c,String g,String a, SQLiteDatabase sqLiteDatabase)
    {
        ContentValues cntval=new ContentValues();
        cntval.put(USER_NAME,u);
        cntval.put(USER_PASS,p);
        cntval.put(USER_MOBILE,pn);
        cntval.put(USER_EMAIL,m);
        cntval.put(USER_FNAME,f);
        cntval.put(USER_LNAME,l);
        cntval.put(USER_CITY,c);
        cntval.put(USER_GENDER,g);
        cntval.put(USER_AGE,a);
        sqLiteDatabase.insert(TABLE_NAME,null,cntval);
        Log.e("DatabaseOPeration","One row inserted");
    }

    public Cursor getContact(String username,String password, SQLiteDatabase db)
    {
        String[] projection={USER_FNAME,USER_LNAME};
        String selection=USER_NAME+" =? AND "+USER_PASS+ "=?";
        String[] selection_arg={username,password};
        Cursor cursor=db.query(TABLE_NAME,projection,selection,selection_arg,null,null,null);
        return (cursor);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

}

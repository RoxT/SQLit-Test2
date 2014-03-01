package com.example.sqlitetest2;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLitehelper extends SQLiteOpenHelper {

	private static final String TAG = "com.example.sqlitetest2";
	
	// Table 
	public static final String TABLE_NAME = "Employees";
	public static final String COLUMN_ID = "_id";
	
	// Columns
	public static final String COLUMN_FIRSTNAME = "First";
	public static final String COLUMN_LASTNAME = "Last";
	public static final String COLUMN_AGE = "Age";
	
	// Database
	public static final String DATABASE_NAME = "employee.db";
	public static final int DATABASE_VERSION = 1;
	
	// onCreate SQL commands
	  private static final String DATABASE_CREATE = "create table "
		      + TABLE_NAME + " ( " + COLUMN_ID
		      + " integer primary key autoincrement, " 
		      + COLUMN_FIRSTNAME + " text not null, "
		      + COLUMN_LASTNAME + " text not null, "
		      + COLUMN_AGE + " integer not null);";
	
	public MySQLitehelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create the database
		db.execSQL(DATABASE_CREATE);
		
		// Add some sample data
		this.addEmployee("Jessica", "Walters", 32);
		this.addEmployee("Roxanne", "Taylor", 24);

        Log.i(TAG, "Table created");
	}
	
	public void addEmployee(String first, String last, int age) {
		ContentValues values = new ContentValues();
		
		values.put("first", first);
		values.put("last", last);
		values.put("Age", age);
		
		SQLiteDatabase db = this.getWritableDatabase();
		db.insert(TABLE_NAME, null, values);
		db.close();
		Log.i(TAG, "Employee added");
	}
	
	public ArrayList<String> getEntry(String sqlArg) {
		// Create the string to be returned
		ArrayList<String> nameList = new ArrayList<String>();
		// Make an SQL statement to be used
		String selectQuery = "SELECT  " + sqlArg + " FROM " + TABLE_NAME;
		
		
		// Create a database to use
		SQLiteDatabase database = this.getReadableDatabase();
		// Create a cursor (in the database)
		// rawQuery is used to give SQL and returns a cursor (position with data)
		Cursor cursor = database.rawQuery(selectQuery, null);
		// Loop to get each item. Goes to next after every pass
		if (cursor.moveToFirst()) {
			do {
				nameList.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		database.close();
		return nameList;
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query;
        query = "DROP TABLE IF EXISTS Students";
        db.execSQL(query);
        onCreate(db);
	}

}

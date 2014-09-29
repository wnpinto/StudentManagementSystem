package com.example.studentmoneymanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/*
 * The purpose of this class is to provide a systematic layout of the schema 
 * that hold the database information of user's recent purchaces. This class will provide a
 * framework for classes that invoke this class duch as the helper class and datasource class. This makes it easier to address
 * any changes needed.
 */
public class RecentPurchacesSQLContract extends SQLiteOpenHelper implements BaseColumns{

	public static final String TABLE_NAME = "purchaces";
	public static final String COLUMN_NAME_STORE = "store";
	public static final String COLUMN_NAME_ITEM = "item";
	public static final String COLUMN_NAME_PRICE = "price";
	public static final String COLUMN_NAME_CATEGORY = "category";
	
	
	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_PATH =  "/data/data/com.example.studentmoneymanagement/" + WalletSQLContract.DATABASE_NAME;
	
	public RecentPurchacesSQLContract(Context context){	
		super(context, DATABASE_PATH, null, DATABASE_VERSION);
	}
	
	public static final String DATABASE_CREATE = "create table " + TABLE_NAME + " (" + COLUMN_NAME_STORE + 
			" text not null, " + COLUMN_NAME_ITEM + " text not null, " + 
			  COLUMN_NAME_PRICE + " text not null, " + COLUMN_NAME_CATEGORY + " text not null);";

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(newVersion > oldVersion)
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
}

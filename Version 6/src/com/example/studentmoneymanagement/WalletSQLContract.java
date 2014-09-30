package com.example.studentmoneymanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/*
 * The purpose of this class is to provide a systematic layout of the schema 
 * that hold the database information of user's wallet. This class will provide a
 * framework for classes that invoke this class such as the helper class and datasource class. This makes it easier to address
 * any changes needed.
 */
public class WalletSQLContract extends SQLiteOpenHelper implements BaseColumns{


	/*
	 * Wallet Table Definitions 
	 */
	public static final String TABLE_NAME = "wallet";
	public static final String COLUMN_NAME_CURRENTBALANCE = "current_balance";
	public static final String COLUMN_NAME_BUDGETPLAN = "budgetplan";
	public static final String COLUMN_NAME_BUDGET = "budget";
	
	public WalletSQLContract(Context context) {
		super(context, DATABASE_PATH, null, DATABASE_VERSION);
	}
	
	
	static final String DATABASE_NAME = "wallet.db";
	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_PATH = "/data/data/com.example.studentmoneymanagement/" + DATABASE_NAME;
	
	//create the wallet database
	public static final String DATABASE_CREATE = "create table " + TABLE_NAME + " (" + COLUMN_NAME_BUDGETPLAN + " text primary key," + COLUMN_NAME_CURRENTBALANCE + " text, " + 
			  COLUMN_NAME_BUDGET + " text);";
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
		db.execSQL(RecentPurchacesSQLContract.DATABASE_CREATE);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
		onCreate(db);
	}
}

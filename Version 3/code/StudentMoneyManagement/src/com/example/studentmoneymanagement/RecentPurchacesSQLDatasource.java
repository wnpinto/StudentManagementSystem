package com.example.studentmoneymanagement;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/*
 * 
 * Maintains the conenction for purchaces database.
 * Database operations are done in this class.
 * 
 */
public class RecentPurchacesSQLDatasource {
	private SQLiteDatabase database;
	private RecentPurchacesSQLContract recentPurchacesHelper;
	
	private String[] columns = {RecentPurchacesSQLContract.COLUMN_NAME_STORE, RecentPurchacesSQLContract.COLUMN_NAME_ITEM, 
			RecentPurchacesSQLContract.COLUMN_NAME_PRICE};
	
	public RecentPurchacesSQLDatasource(Context context){
		recentPurchacesHelper = new RecentPurchacesSQLContract(context);
	}
	
	public void open() throws SQLException{
		database = recentPurchacesHelper.getWritableDatabase();
		Log.d("DATABASE OPENED", database.getPath());
	}
	
	public void close(){
		recentPurchacesHelper.close();
	}
	
	/*Purchaces Database operations*/
	public void createNewPurchace(String store, String item, String price){
		ContentValues values = new ContentValues();
		
		values.put(RecentPurchacesSQLContract.COLUMN_NAME_STORE, store);
		values.put(RecentPurchacesSQLContract.COLUMN_NAME_ITEM, item);
		values.put(RecentPurchacesSQLContract.COLUMN_NAME_PRICE, price);
		
		long newRowId;
		newRowId=database.insert(RecentPurchacesSQLContract.TABLE_NAME, null, values);
	}
	
	/*
	 ***************************************** readData() **********************************
	 * get the user's last purchased item. 
	 */
	public String[] readData(){
		//ArrayList<Wallet> purchaseList = new ArrayList<Wallet>();
		//return all columns in the database with no WHERE clause /group order
		Cursor cursor = database.query(RecentPurchacesSQLContract.TABLE_NAME, columns, null, null, null, null, null);
		if(cursor.getCount() <= 0)
			createNewPurchace("Default Store", "default Product", "0");
		
		cursor.moveToFirst();
		String[] c = {cursor.getString(0), cursor.getString(1), cursor.getString(2)};
		cursor.close();
		return c;
	}
	
	public ArrayList<String[]> readDataAll() {
		// TODO Auto-generated method stub
		//ArrayList<Wallet> walletList = new ArrayList<Wallet>();
		//return all columns in the database with no WHERE clause /group order
		ArrayList<String[]> purchases = new ArrayList<String[]>();
		Cursor cursor = database.query(RecentPurchacesSQLContract.TABLE_NAME, columns, null, null, null, null, null);
		while(cursor.moveToNext() && cursor.getCount() > 0){
			String[] c = {cursor.getString(0), cursor.getString(1), cursor.getString(2)};
			purchases.add(c);
		}
		cursor.close();
		return purchases;
	}
}

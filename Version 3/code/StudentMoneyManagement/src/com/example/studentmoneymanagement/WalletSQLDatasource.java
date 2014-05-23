package com.example.studentmoneymanagement;



import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

/*
 * Class that maintains connection with the wallet database.
 * Supports updating the database.
 */
public class WalletSQLDatasource {

	private SQLiteDatabase database;
	private WalletSQLContract walletdbHelper;
	private Wallet myWallet;
	
	private String [] columns = {WalletSQLContract.COLUMN_NAME_BUDGETPLAN,
			WalletSQLContract.COLUMN_NAME_CURRENTBALANCE,
			WalletSQLContract.COLUMN_NAME_BUDGET};
	
	public WalletSQLDatasource(Context context){
		walletdbHelper = new WalletSQLContract(context);
	}
	
	public void open() throws SQLException{
			database = walletdbHelper.getWritableDatabase();
	}
	
	public void close(){
		walletdbHelper.close();
	}
	
	/*Database Operations*/
	/*
	 ***************************************** createNewWallet(String budgetPlan, String currentBalance, String budget) **********************************
	 * Create a new wallet entry in the wallet database. 
	 */
	public Wallet createNewWallet(String budgetPlan, String currentBalance, String budget){
	
		ContentValues values = new ContentValues();
		values.put(WalletSQLContract.COLUMN_NAME_BUDGETPLAN, budgetPlan);
		values.put(WalletSQLContract.COLUMN_NAME_CURRENTBALANCE, currentBalance);
		values.put(WalletSQLContract.COLUMN_NAME_BUDGET, budget);

		//insert the new row into database.
		long newRowId;
		newRowId=database.insert(WalletSQLContract.TABLE_NAME, null, values);
		
		myWallet = new Wallet(Integer.parseInt(currentBalance), Integer.parseInt(budget), budgetPlan);
		
		return myWallet;
	}

	/*
	 ***************************************** readData() **********************************
	 * get the users current budget plan. 
	 */
	public String[] readData(){
		ArrayList<Wallet> walletList = new ArrayList<Wallet>();
		//return all columns in the database with no WHERE clause /group order
		Cursor cursor = database.query(WalletSQLContract.TABLE_NAME, columns, null, null, null, null, null);
		if(cursor.getCount() <= 0)
			createNewWallet("Default Plan", "100", "100");
		
			cursor.moveToFirst();
			String[] c = {cursor.getString(0), cursor.getString(1), cursor.getString(2)};
			cursor.close();
			return c;

	}

	public ArrayList<String[]> readDataAll() {
		// TODO Auto-generated method stub
		//ArrayList<Wallet> walletList = new ArrayList<Wallet>();
		//return all columns in the database with no WHERE clause /group order
		ArrayList<String[]> plans = new ArrayList<String[]>();
		Cursor cursor = database.query(WalletSQLContract.TABLE_NAME, columns, null, null, null, null, null);
		while(cursor.moveToNext()){
			String[] c = {cursor.getString(0), cursor.getString(1), cursor.getString(2)};
			plans.add(c);
		}
		cursor.close();
		return plans;
	}

	
	/*
	 ***************************************** updateWallet(String, String, String) **********************************
	 * update the user's current wallet setting. 
	 */
	public int updateWallet(String parseDouble, String parseDouble2,
			String budgetPlan) {
		database = walletdbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(WalletSQLContract.COLUMN_NAME_BUDGETPLAN, budgetPlan);
		values.put(WalletSQLContract.COLUMN_NAME_CURRENTBALANCE, parseDouble);
		values.put(WalletSQLContract.COLUMN_NAME_BUDGET, parseDouble2);
	
		return database.update(WalletSQLContract.TABLE_NAME, values, WalletSQLContract.COLUMN_NAME_BUDGETPLAN + " = ?",
				 new String[] { budgetPlan});
		
	}
}

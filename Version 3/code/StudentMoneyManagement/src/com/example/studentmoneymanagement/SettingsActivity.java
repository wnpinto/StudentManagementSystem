package com.example.studentmoneymanagement;

import com.example.studentmoneymanagement.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SettingsActivity extends MainActivity {


	private Button backButton;
	private Button OKButton;
	private Button addNewPlanButton;
	private TextView initialWalletInput;
	private EditText initialBudgetInput;
	private EditText addNewPlanInput;
	private WalletSQLDatasource walletDatasource;

	String TAG = "TAG_MyActivity"; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_activity_main);

		walletDatasource = new WalletSQLDatasource(this);
		walletDatasource.open();
		final String[] data = walletDatasource.readData();
		walletDatasource.close();
		
		myWallet = new Wallet(Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[0]);
		
		initialWalletInput = (EditText) findViewById(R.id.setWalletInitialEditText1);
		initialBudgetInput = (EditText) findViewById(R.id.setBudgetInitialEditText1);
		addNewPlanInput = (EditText) findViewById(R.id.addnewplaneditText1);

		backButton = (Button) findViewById(R.id.settingsbackbutton1);
		OKButton = (Button) findViewById(R.id.settingsOKbutton1);
		addNewPlanButton = (Button) findViewById(R.id.addnewplanbutton1);
		
		backButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				/*
				Intent i = getIntent();
				// Sending param key as 'website' and value as 'androidhive.info'
				String msg = i.getStringExtra("initialWalletMoney");
				final String amount = initialWalletInput.getText().toString();
				final String budgetAmount = initialBudgetInput.getText().toString();
				
				Log.i(TAG, "amount" + amount);
				Log.i(TAG, "budgetAmount" + budgetAmount);
				
				if(msg.contentEquals("0.00")){
				//	if(!(amount.equals("")))
						i.putExtra("initialWalletMoney", amount);
				//	if(!(budgetAmount.equals("")))
						i.putExtra("initialBudgetMoney", budgetAmount);
				}

				setResult(100,i);
				*/
				finish();
			}
		});
		
		OKButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				walletDatasource.open();
				final String[] data = walletDatasource.readData();	
	
				walletDatasource.updateWallet(initialWalletInput.getText().toString(), initialBudgetInput.getText().toString(), data[0]);
				walletDatasource.close();
				/*walletDatasource.open(); 
				//myWallet = (Wallet) walletDatasource.createNewWallet(addNewPlanInput.getText().toString(), initialWalletInput.getText().toString(), initialBudgetInput.getText().toString());
				//walletDatasource.close();
				*/
			}
		});
		
		
		addNewPlanButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				walletDatasource.open();
				myWallet = (Wallet) walletDatasource.createNewWallet(addNewPlanInput.getText().toString(), initialWalletInput.getText().toString(), initialBudgetInput.getText().toString());
				walletDatasource.close();
			}
		});
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

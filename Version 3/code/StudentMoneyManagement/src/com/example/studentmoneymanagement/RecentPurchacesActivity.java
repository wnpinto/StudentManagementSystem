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


public class RecentPurchacesActivity extends MainActivity {

	RecentPurchases rp = new RecentPurchases();
	private Button addPurchaseButton;
	private Button backButton;
	private Button recentPurchaseButton;
	private Button analysePurchaseButton;
	private EditText productInput;
	private EditText priceInput;
	private EditText storeInput;
	//Wallet wallet = super.getWallet();
	private RecentPurchacesSQLDatasource purchasesDatasource;
	private WalletSQLDatasource walletDatasource;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.purchaces_activity_main);
		purchasesDatasource = new RecentPurchacesSQLDatasource(this);
		walletDatasource = new WalletSQLDatasource(this);
		
		backButton = (Button) findViewById(R.id.backbutton2);
		backButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
		
				finish();
			}
		});
		
		
		addPurchaseButton = (Button) findViewById(R.id.addapurchasebutton1);
		addPurchaseButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//retrieve recent purchase information and store in database.
				storeInput = (EditText)findViewById(R.id.storeEditText);
				productInput = (EditText)findViewById(R.id.productEditText);
				priceInput = (EditText)findViewById(R.id.priceEditText);
				
				if(storeInput == null || storeInput.equals(""))
					storeInput.setText("unknown store");
				if(productInput == null || productInput.equals(""))
					productInput.setText("unknown product");
				if(priceInput == null || priceInput.equals(""))
					priceInput.setText("unknown price");
				

				purchasesDatasource.open();
				purchasesDatasource.createNewPurchace(storeInput.getText().toString(), productInput.getText().toString(), priceInput.getText().toString());
				purchasesDatasource.close();
				
				//update wallet.
				walletDatasource.open();
				String data[] = walletDatasource.readData();
				double newWalletMoney = Double.parseDouble(data[1]) - Double.parseDouble(priceInput.getText().toString());
				walletDatasource.updateWallet(String.valueOf(newWalletMoney), data[2], data[0]);
				walletDatasource.close();
			}
		});
		
		recentPurchaseButton = (Button) findViewById(R.id.recentpurchasebutton1);
		recentPurchaseButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), CheckPurchasesActivity.class);
				startActivity(i);
			}
		});
		
		analysePurchaseButton = (Button) findViewById(R.id.analysepurchasebutton1);
		analysePurchaseButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), AnalysePurchasesActivity.class);
				startActivity(i);
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

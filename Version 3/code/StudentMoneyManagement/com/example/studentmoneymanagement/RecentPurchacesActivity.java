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
	Wallet wallet = super.getWallet();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.purchaces_activity_main);

		backButton = (Button) findViewById(R.id.backbutton2);
		backButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
		
				finish();
			}
		});
		
		
		addPurchaseButton = (Button) findViewById(R.id.addapurchasebutton1);
		addPurchaseButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), AddPurchaseActivity.class);
				startActivity(i);
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

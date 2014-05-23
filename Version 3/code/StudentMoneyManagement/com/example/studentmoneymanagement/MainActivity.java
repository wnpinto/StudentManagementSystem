package com.example.studentmoneymanagement;

import com.example.studentmoneymanagement.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {


	double parentWalletMoney = 0;
	double parentBudgetMoney;
	double INITIAL_WALLET_MONEY = 4000.00;
	double INITIAL_BUDGET_MONEY = 5.00;
	boolean withinBudget = true;

	Button settingsButton;
	Button backButton;
	Button recentPurchasesButton;
	
	TextView parentWalletField;
	TextView messageField;
	TextView parentBudgetField;

	private Wallet userParentWallet;
	private RecentPurchases rp = new RecentPurchases();
	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		userParentWallet = new Wallet(INITIAL_WALLET_MONEY, INITIAL_BUDGET_MONEY);
		
		parentWalletField = (TextView) findViewById(R.id.WalletView1);
		parentBudgetField = (TextView) findViewById(R.id.BudgetView1);
		messageField = (TextView) findViewById(R.id.messagetextView1);
		settingsButton = (Button) findViewById(R.id.settingsbutton1);
		recentPurchasesButton = (Button) findViewById(R.id.recentpurchacesbutton1);
		mHandler = new Handler();
		mHandler.post(mUpdate);
		
		 
		
		
		settingsButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
				startActivityForResult(i, 100); // 100 is some code to identify the returning result
			}
		});
		

		
		recentPurchasesButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(), RecentPurchacesActivity.class);
				startActivity(i); // 100 is some code to identify the returning result
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	protected void onActivityResult(int requestCode,
			int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == 100){

			// Storing result in a variable called myvar
			// get("website") 'website' is the key value result data
			if(!(data.getStringExtra("initialWalletMoney").isEmpty())){
				parentWalletField.setText(data.getStringExtra("initialWalletMoney"));
				userParentWallet.setParentWalletMoney(Double.parseDouble(data.getStringExtra("initialWalletMoney")));
			}
			if(!(data.getStringExtra("initialBudgetMoney").isEmpty())){
				parentBudgetField.setText(data.getStringExtra("initialBudgetMoney"));
				userParentWallet.setParentBudget(Double.parseDouble(data.getStringExtra("initialBudgetMoney")));
			}
			
		}

	}


	private Runnable mUpdate = new Runnable() {
		public void run() {

			checkBudget();
			updateWallet();
			
			
			setNewMessage();
			Log.v("THREAD LOOP:", "PASSSS");
			mHandler.postDelayed(this, 4000);
		}

		public void updateWallet() {
			parentWalletMoney = userParentWallet.getParentWalletMoney();
			parentBudgetMoney = userParentWallet.getParentBudget();
			parentWalletField.setText(String.valueOf(parentWalletMoney));
			parentBudgetField.setText(String.valueOf(parentBudgetMoney));		
		}

	

	};

	private void setNewMessage() {
		if(withinBudget)
			messageField.setText("You are within your budget!");
		else if(!withinBudget)
			messageField.setText("You are below your budget! Change your purchases");
		
	}

	private void checkBudget() {
		withinBudget = userParentWallet.isWithinBudget();

		if(withinBudget)
			parentWalletField.setTextColor(Color.GREEN);
		else{
			parentWalletField.setTextColor(Color.RED);	
		}
	}
	
	public void setWallet(Wallet wallet){
		userParentWallet = wallet;
	}
	
	public Wallet getWallet(){
		return userParentWallet;
	}
	
	public void setRecentPurchases(RecentPurchases r){
		rp = r;
		userParentWallet.setParentWalletMoney(userParentWallet.getParentWalletMoney() - rp.getTotalCosts());
		Log.v("-----", ""+userParentWallet.getParentWalletMoney());
	}
	
	public RecentPurchases getRecentPurchaces(){
		return rp;
	}
}

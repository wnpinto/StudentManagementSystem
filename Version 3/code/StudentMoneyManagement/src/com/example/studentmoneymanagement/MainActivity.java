package com.example.studentmoneymanagement;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private Button recentPurchacesButton;
	private Button settingButton;
	private Button allPlansButton;
	private WalletSQLDatasource walletDatasource;
	Wallet myWallet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recentPurchacesButton = (Button) findViewById(R.id.recentpurchacesbutton1);
		settingButton = (Button) findViewById(R.id.settingsbutton1);
		allPlansButton = (Button) findViewById(R.id.viewAllPlansButton);
		
		walletDatasource = new WalletSQLDatasource(this);
		walletDatasource.open();
		final String[] data = walletDatasource.readData();
		walletDatasource.close();

		myWallet = new Wallet(Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[0]);
		
		final TextView tv = (TextView)findViewById(R.id.WalletView1);
		final TextView tv2 = (TextView)findViewById(R.id.BudgetView1);
		tv.setText(data[1]);
		tv2.setText(data[2]);
				
				/*View All Plans Button Handler*/
		allPlansButton.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			//switch to Settings View.
				Log.d("PLAN Name:", data[0]);
				Log.d("PLAN Wallet:", data[1]);
				Log.d("PLAN Budjet:", data[2]);				
				Intent plansIntent = new Intent(v.getContext(), PlansActivity.class);
				startActivity(plansIntent);
			}
		});
				
				
		/*Recent Purchases Button Handler*/
		recentPurchacesButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//switch to Recent Purchases View.
				
				Intent recentPurchacesIntent = new Intent(v.getContext(), RecentPurchacesActivity.class);
				startActivityForResult(recentPurchacesIntent, 0);
			}
		});
		
		
		/*Settings Button Handler*/
		settingButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//switch to Settings View.
				Intent settingsIntent = new Intent(v.getContext(), SettingsActivity.class);
				startActivity(settingsIntent);
			
				walletDatasource.open();
				final String[] data = walletDatasource.readData();
				walletDatasource.close();

				tv.setText(data[1]);
				tv2.setText(data[2]);
				
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
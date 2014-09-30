package com.example.studentmoneymanagement;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewAnimator;

public class MainActivity extends Activity {

	private Button recentPurchacesButton;
	private Button settingButton;
	private Button allPlansButton;
	private WalletSQLDatasource walletDatasource;
	protected TextView tv;
	protected TextView tv2;
	private ViewAnimator viewAnimator;
	private Animation slide_in_left, slide_out_right;
	
	//Wallet myWallet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recentPurchacesButton = (Button) findViewById(R.id.recentpurchacesbutton1);
		settingButton = (Button) findViewById(R.id.settingsbutton1);
		allPlansButton = (Button) findViewById(R.id.viewAllPlansButton);
		viewAnimator = (ViewAnimator)findViewById(R.id.viewAnimator1);
		
		slide_in_left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
		slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
		viewAnimator.setInAnimation(slide_in_left);
		viewAnimator.setOutAnimation(slide_out_right);
		  
		walletDatasource = new WalletSQLDatasource(this);
		//walletDatasource.open();
		final String[] data = walletDatasource.readData();
		//walletDatasource.close();

		//myWallet = new Wallet(Double.parseDouble(data[1]), Double.parseDouble(data[2]), data[0]);
		
		tv = (TextView)findViewById(R.id.WalletView1);
		tv2 = (TextView)findViewById(R.id.BudgetView1);
		tv.setText(data[1]);
		tv2.setText(data[2]);
		
		
		//Perform and Display Purchase Analysis
		
		
		
		//wallet status
		
		//String quote = new SpendingHabits().getQuote();
	//	walletStatusTV = (TextView)findViewById(R.id.walletStatusTextView);
	//	walletStatusTV.setText(quote);
		
		
		
				
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
			
				//walletDatasource.open();
				final String[] data = walletDatasource.readData();
				//walletDatasource.close();

				tv.setText(data[1]);
				tv2.setText(data[2]);
				
			}
		});
		
		viewAnimator.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				viewAnimator.showNext();
				return true;
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
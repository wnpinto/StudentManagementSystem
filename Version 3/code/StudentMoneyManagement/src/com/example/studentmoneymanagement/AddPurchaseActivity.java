package com.example.studentmoneymanagement;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class AddPurchaseActivity extends MainActivity {

	ArrayList<String> listItems=new ArrayList<String>();
	RecentPurchases rp = new RecentPurchases();
    //DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
    ArrayAdapter<String> adapter;

	private EditText dateInput;
	private EditText costInput;
	private EditText itemInput;
	private Button backButton;
	private Button addButton;
	private RecentPurchacesSQLDatasource recentPurchacesDatasource;
	
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.add_purchases_main);
     
    	
        recentPurchacesDatasource = new RecentPurchacesSQLDatasource(this);
        
    	dateInput = (EditText) findViewById(R.id.dateeditText1); /*testing for store name instead, NOT DATE*/
    	itemInput = (EditText) findViewById(R.id.itemeditText2);
    	costInput = (EditText) findViewById(R.id.costeditText3);
    	
    	backButton = (Button) findViewById(R.id.backbutton3);
		backButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		addButton = (Button) findViewById(R.id.addnewpurchasebutton);
		addButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				RecentPurchases rp = new RecentPurchases();
				
				final String date = dateInput.getText().toString();
		    	final String item = itemInput.getText().toString();
		    	final String cost = costInput.getText().toString();
		    	//rp.addNewPurchase(date, item, cost);
		    	
		    	recentPurchacesDatasource.open();
		    	recentPurchacesDatasource.createNewPurchace(date, item, cost);
		    	recentPurchacesDatasource.close();
		       // setRecentPurchases(rp);
			}
		});
    }
}
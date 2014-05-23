package com.example.studentmoneymanagement;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.example.studentmoneymanagement.R;

import android.os.Bundle;
import android.os.Environment;
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


public class CheckPurchasesActivity extends ListActivity {

	ArrayList<String> listItems=new ArrayList<String>();
	RecentPurchases rp = new RecentPurchases();
	//DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
	ArrayAdapter<String> adapter;

	String strLine = null;
	private Button backButton;


	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.check_purchases_main);
		adapter=new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				rp.getRecentPurchase());
		setListAdapter(adapter);


	//	getRecentPurchase();
		//adapter.notifyDataSetChanged();
		
		//listItems.add(rp.getPurchases);
		adapter.notifyDataSetChanged();
		backButton = (Button) findViewById(R.id.backbutton2);
		backButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	
}
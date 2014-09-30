package com.example.studentmoneymanagement;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AnalysePurchasesActivity2 extends MainActivity{

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.analyse_purchases_main2);

		AnalysePurchasesCustomView lineChart = (AnalysePurchasesCustomView) findViewById(R.id.analysePurchasesCustomView1);
		AnalysePurchasesCustomViewPie pieChart = (AnalysePurchasesCustomViewPie) findViewById(R.id.analysePurchasesCustomViewPie1);
		//lineChart.setChartData();
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}	
}

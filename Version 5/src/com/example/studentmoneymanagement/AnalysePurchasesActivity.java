package com.example.studentmoneymanagement;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AnalysePurchasesActivity extends MainActivity{

	private ArrayList<String[]> purchaseList = new ArrayList<String[]>();
	private TextView walletMoney;
	private TextView budgetMoney;
	/*private GraphicalView chart;
	private XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
	private XYSeries mCurrentSeries;
	private XYSeriesRenderer mCurrentRenderer;
	
	 private void initChart() {
	        mCurrentSeries = new XYSeries("Sample Data");
	        dataset.addSeries(mCurrentSeries);
	        mCurrentRenderer = new XYSeriesRenderer();
	        mRenderer.addSeriesRenderer(mCurrentRenderer);
	    }

	 private void addSampleData() {
	        mCurrentSeries.add(1, 2);
	        mCurrentSeries.add(2, 3);
	        mCurrentSeries.add(3, 2);
	        mCurrentSeries.add(4, 5);
	        mCurrentSeries.add(5, 4);
	    }
	  */  
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.analyse_purchases_main);

		RecentPurchacesSQLDatasource rpDatasource = new RecentPurchacesSQLDatasource(this);
		// rpDatasource.open();
		 purchaseList = rpDatasource.readDataAll();
		 walletMoney = super.tv;
		 budgetMoney = super.tv2;
		// rpDatasource.close();
		 
		 
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}	
	
	protected void onResume() {
        super.onResume();
        
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.chart);
        PurchasesAnalysis pa = new PurchasesAnalysis();
        GraphicalView chart = pa.getChart();
		 walletMoney = super.tv;
		 budgetMoney = super.tv2;
		 
        if (chart == null) {
            pa.getAllPurchases(purchaseList);
            chart = ChartFactory.getLineChartView(this, pa.getDataset(), pa.getMRenderer());
            layout.addView(chart);
        } else {
            chart.repaint();
        }
    }
}

package com.example.studentmoneymanagement;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;

public class AnalysePurchasesActivity extends MainActivity{

	ArrayList<String[]> purchaseList = new ArrayList<String[]>();

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
		 rpDatasource.open();
		 purchaseList = rpDatasource.readDataAll();
		 rpDatasource.close();
		 
		 
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}	
	
	protected void onResume() {
        super.onResume();
        LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
        PurchasesAnalysis pa = new PurchasesAnalysis();
        GraphicalView chart = pa.getChart();
        
        if (chart == null) {
            pa.getAllPurchases(purchaseList);
            chart = ChartFactory.getLineChartView(this, pa.getDataset(), pa.getMRenderer());
            layout.addView(chart);
        } else {
            chart.repaint();
        }
    }
}

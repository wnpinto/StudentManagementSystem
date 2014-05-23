package com.example.studentmoneymanagement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.graphics.Color;


/*
 * 
 * This class will collect all the recent purchases 
 * data and perform an analysis.
 * 
 */
public class PurchasesAnalysis extends MainActivity{

	private ArrayList<String[]> purchaseList = new ArrayList<String[]>();
	private RecentPurchacesSQLDatasource rpDatasource = new RecentPurchacesSQLDatasource(this);
	private WalletSQLDatasource walletDatasource = new WalletSQLDatasource(this);
	private GraphicalView chart;
	private XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
	private XYSeries mCurrentSeries;
	private XYSeriesRenderer mCurrentRenderer;
	private HashMap<String, XYSeries> chartData = new HashMap<String, XYSeries>();
	
	public PurchasesAnalysis(){
		initChart();
		
	}
	
	
	 public void initChart() {
		 	chartData.put("Recent Purchases", new XYSeries("Recent Purchases"));      
		 	
		 	for(Entry<String, XYSeries> entry: chartData.entrySet()){
		 		dataset.addSeries(entry.getValue());
		 	}
	      
	        mCurrentRenderer = new XYSeriesRenderer();
	        mRenderer.addSeriesRenderer(mCurrentRenderer);
	        
	        mRenderer.setChartTitle("Analysis Of Your Purchases");
	        mRenderer.addXTextLabel(1, "Item Number");
	        mRenderer.setGridColor(Color.WHITE);

	    }

	 public void addData(int x, int y, String key){
		 chartData.get(key).add(x, y);
	 }
	 
	 public void getAllPurchases(ArrayList<String[]> pL){

		 for(int index = 0; index < pL.size(); index++){
				int itemPrice = (int)(Double.parseDouble(pL.get(index)[2]));
				 addData(index, itemPrice, "Recent Purchases");
			}
	 }
	 
	/*
	 * Retrieve all the total amount of money spent from purchases table
	 * [DEV]: POSSIBLY CHANGE TO SQL QUERY INSTEAD OF ITERATIVE CODE
	 */
	public String getTotalMoneySpent(){
		rpDatasource.open();
		ArrayList<String[]> pl = new ArrayList<String[]>();
		pl = rpDatasource.readDataAll();
		rpDatasource.close();
		double totalSpent = 0.0;
		
		for(int index = 0; index < purchaseList.size(); index++){
			totalSpent+=(Double.parseDouble(purchaseList.get(index)[2]));
		}
		
		return String.valueOf(totalSpent);
	}
	
	/*
	 * Retrieve the count of each store for each visit.
	 * [DEV]: POSSIBLY CHANGE TO SQL QUERY INSTEAD OF ITERATIVE CODE
	 */
	public HashMap<String, Integer> getAllStores(){
		rpDatasource.open();
		purchaseList = rpDatasource.readDataAll();
		rpDatasource.close();

		HashMap<String, Integer> stores = new HashMap<String, Integer>();
		
		for(int index = 0; index < purchaseList.size(); index++){
			if(stores.containsKey(purchaseList.get(index)[0]))
				stores.put(purchaseList.get(index)[0], new Integer(stores.get(purchaseList.get(index)[0])) + 1);
			else
				stores.put(purchaseList.get(index)[0], new Integer(1));
		}
		
		return stores;
	}
	
	public String[] walletStatus(){
		walletDatasource.open();
		String[] wallet = rpDatasource.readData();
		rpDatasource.close();

		String[] walletStatusUpdate = new String[2];
		
		//check if over/under the user-set budget:
		//over: wallet money is less than budget money
		//under = wallet money is more than budget money
		if(Double.parseDouble(wallet[1]) < Double.parseDouble(wallet[2]))
			walletStatusUpdate[0] = "over";
		else
			walletStatusUpdate[0] = "under";
		
		//set a colour for the budget status:
		//0 = bad(red)
		//3 = good(green)
		if(Double.parseDouble(wallet[1]) > Double.parseDouble(wallet[2])*0.20 + Double.parseDouble(wallet[2]))
			walletStatusUpdate[1] = "3";
		else if(Double.parseDouble(wallet[1]) < Double.parseDouble(wallet[2])*0.20 + Double.parseDouble(wallet[2])
				&& Double.parseDouble(wallet[1]) > Double.parseDouble(wallet[2])*0.10 + Double.parseDouble(wallet[2]))
			walletStatusUpdate[1] =  "2";
		else if(Double.parseDouble(wallet[1]) < Double.parseDouble(wallet[2])*0.10 + Double.parseDouble(wallet[2])
				&& Double.parseDouble(wallet[1]) > Double.parseDouble(wallet[2])*0.05 + Double.parseDouble(wallet[2]))
			walletStatusUpdate[1] = "1";
		else
			walletStatusUpdate[1] = "0";
		
		return walletStatusUpdate;
	}


	public GraphicalView getChart() {
		// TODO Auto-generated method stub
		return chart;
	}


	public XYMultipleSeriesDataset getDataset() {
		// TODO Auto-generated method stub
		return dataset;
	}


	public XYMultipleSeriesRenderer getMRenderer() {
		// TODO Auto-generated method stub
		return mRenderer;
	}
}

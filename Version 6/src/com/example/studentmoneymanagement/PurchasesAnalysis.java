package com.example.studentmoneymanagement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/*
 * 
 * This class will collect all the recent purchases 
 * data and perform an analysis.
 * 
 */
public class PurchasesAnalysis extends MainActivity{

	private ArrayList<String[]> purchaseList = new ArrayList<String[]>();
	private RecentPurchacesSQLDatasource rpDatasource = new RecentPurchacesSQLDatasource(this);
	private GraphicalView chart;
	private XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
	private XYSeriesRenderer mCurrentRenderer;
	private HashMap<String, XYSeries> chartLineData = new HashMap<String, XYSeries>();
	private HashMap<String, CategorySeries> chartPieData = new HashMap<String, CategorySeries>();
	
	public PurchasesAnalysis(){
		initChart();
	}
	
	
	/*
	 * =================================== initChart() ===================================
	 * initializes the chart data.
	 * 
	 */
	 public void initChart() {
		 	chartLineData.put("Recent Purchases", new XYSeries("Recent Purchases"));      
		 	chartPieData.put("Store Count", new CategorySeries("pie"));
		 	
		 	for(Entry<String, XYSeries> entry: chartLineData.entrySet()){
		 		dataset.addSeries(entry.getValue());
		 	}
	      
	        mCurrentRenderer = new XYSeriesRenderer();
	        mRenderer.addSeriesRenderer(mCurrentRenderer);
	        
	       // mRenderer.setChartTitle("Analysis Of Your Purchases");
	        mRenderer.addXTextLabel(1, "Item Number");
	       // mRenderer.setGridColor(Color.WHITE);
	 }

	 public void addLineData(int x, int y, String key){
		 chartLineData.get(key).add(x, y);
	 }
	 
	 public void addPieData(String slice, int size, String key){
		 chartPieData.get(key).add(slice, size);
	 }
	 
	 public void getAllPurchases(ArrayList<String[]> pL){

		 for(int index = 0; index < pL.size(); index++){
				int itemPrice = (int)(Double.parseDouble(pL.get(index)[2]));
				 addLineData(index, itemPrice, "Recent Purchases");
			}
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
		
		/*
		 * Retrieve all the total amount of money spent from purchases table
		 * [DEV]: POSSIBLY CHANGE TO SQL QUERY INSTEAD OF ITERATIVE CODE
		 */
		public String getTotalMoneySpent(){
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
			//rpDatasource.open();
			purchaseList = rpDatasource.readDataAll();
			//rpDatasource.close();

			HashMap<String, Integer> stores = new HashMap<String, Integer>();
			
			for(int index = 0; index < purchaseList.size(); index++){
				if(stores.containsKey(purchaseList.get(index)[0]))
					//stores.put(purchaseList.get(index)[0], new Integer(stores.get(purchaseList.get(index)[0])) + 1);
					addPieData(purchaseList.get(index)[0], Integer.valueOf(stores.get(purchaseList.get(index)[0])), "Store Count");
				else
					addPieData(purchaseList.get(index)[0], 1, "Store Count");
				
			}
			
			return stores;
		}
		
		public String[] walletStatus(){
			//walletDatasource.open();
			String[] wallet = rpDatasource.readData();
			//rpDatasource.close();

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


		
		
		//A method that writes a report based on an analysis of the spending habits.
		public String getQuote(){
			String quote = "";
			
			String[] walletStatus = walletStatus();
			
			if (walletStatus[0].equals("over") && walletStatus[1].equals("3"))
				quote+=("Great Job! Your wallet seems to be 20% over your budget expenses!");
			else if (walletStatus[0].equals("over") && walletStatus[1].equals("2"))
				quote+=("Nice! Your wallet seems to be atleat 10% over your budget expenses!");
			else if (walletStatus[0].equals("over") && walletStatus[1].equals("1"))
				quote+=("Watch out! You're spending habits seem to be affecting your budget. Condider revising your budget plan...");
			else
				quote+=("WARNING! You are spending way too much! Consider changing....");
			
			return quote;
			
		}

	
}

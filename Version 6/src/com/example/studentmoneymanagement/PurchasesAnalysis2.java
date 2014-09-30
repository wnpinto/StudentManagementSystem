package com.example.studentmoneymanagement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map.Entry;

import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;

/*
 * 
 * This class will collect all the recent purchases 
 * data and perform an analysis.
 * 
 */
public class PurchasesAnalysis2{

	private RecentPurchacesSQLDatasource rpDatasource; 
	private ArrayList<String[]> purchaseList = new ArrayList<String[]>();
	
	
	public PurchasesAnalysis2(Context context){
		//init();
		rpDatasource = new RecentPurchacesSQLDatasource(context);
		purchaseList = rpDatasource.readDataAll();
	}

	
	public float[] getPurchasePricesData(){
		float[] purchasesPriceList = new float[purchaseList.size()];
		
		for(int index = 0; index < purchasesPriceList.length; index++){
			purchasesPriceList[index] = Float.parseFloat(purchaseList.get(index)[2]);
		}
		return purchasesPriceList;
	}
	
	public HashMap<String, Integer> getDistinctCategoryData(){
		HashMap<String, Integer> temp = new HashMap<String, Integer>();
		
		temp.put("bills", Integer.valueOf(0));
		temp.put("entertainment", Integer.valueOf(0));
		temp.put("groceries", Integer.valueOf(0));
		
		int billsCount = 0, entertainmentCount = 0, groceriesCount = 0;
		
		for(int index = 0; index < purchaseList.size(); index++){
			if(purchaseList.get(index)[3].equals("bills"))
				billsCount++;
			else if(purchaseList.get(index)[3].equals("entertainment"))
				entertainmentCount++;
			else
				groceriesCount++;
		}
		
		temp.put("bills", Integer.valueOf(billsCount));
		temp.put("entertainment", Integer.valueOf(entertainmentCount));
		temp.put("groceries",Integer.valueOf(groceriesCount));
		
		return temp;
	}
}

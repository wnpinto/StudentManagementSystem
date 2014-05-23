package com.example.studentmoneymanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class RecentPurchases extends Activity{

	ArrayList<String> purchaseItem = new ArrayList<String>();
	ArrayList<String> purchaseDate = new ArrayList<String>();
	ArrayList<String> purchaseCost = new ArrayList<String>();
	ArrayList<String> jointList = new ArrayList<String>();

	public RecentPurchases() {

	}

	public void addNewPurchase(String date, String item, String cost){
		purchaseDate.add(date);
		purchaseItem.add(item);
		purchaseCost.add(cost);

		try{
			jointList.add(date + "\t\t" + item+ "\t$" + cost);
			
			File rpFile = new File("mnt/ext_sdcard/Android/data/com.example.studentmoneymanagement/");
			
			if (!rpFile.exists()) {
		        try {
		        	rpFile.mkdirs();
		        	rpFile.createNewFile();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		}
			 // InputStreamReader isr = new InputStreamReader(System.in);
			//  BufferedReader br = new BufferedReader(isr);
			  
			  FileReader fr = new FileReader(rpFile.getPath() + "/recentpurchases.txt");
			 BufferedReader brr = new BufferedReader(fr);
		
			  
			  String line = brr.readLine();
			   while (line != null) {
			    Log.v("line", line);
			    line = brr.readLine();
			   }
			   
		
			   
			   File sampleFile = new File(rpFile.getPath() + "/recentpurchases.txt");
			   FileWriter fw = new FileWriter(sampleFile, true); 
			   BufferedWriter output = new BufferedWriter(fw);
			   output.newLine();
			   output.write(jointList.get(jointList.size() - 1));
			  output.close();
			   // close BW
			  /*
			FileOutputStream fos = new FileOutputStream(rpFile.getPath() + "/recentpurchases.txt");
			fos.write(jointList.get(jointList.size() - 1).getBytes());
			fos.write("\n".getBytes());
			//fos.write("\n".getBytes());
			fos.close();*/
		}
		catch (Exception e) {

			e.printStackTrace();

		}

		//setParentWalletMoney(getParentWalletMoney() - Double.parseDouble(cost));
	//	Log.v("Super: ", ""+getParentWalletMoney());
	}

	public void joinList(){
		for(int count = 0; count < purchaseItem.size(); count++)
			jointList.add(purchaseDate.get(count) + "\t\t" + purchaseItem.get(count)+ "\t$" + purchaseCost.get(count));

	}

	
	//*************** getRecentPurchase() **************//
	/*
	 *  Method that 
	 */
	public ArrayList<String> getRecentPurchase(){
		
		ArrayList<String> tempList = new ArrayList<String>();
		//tempList.add(purchaseDate.get(purchaseDate.size() - 1) + "\t\t" + purchaseItem.get(purchaseItem.size() - 1)+ "\t$" + purchaseCost.get(purchaseCost.size() - 1));

			FileInputStream fis;
			final StringBuffer storedString = new StringBuffer(); 	


			//mnt/sdcard/Android/data/com.example.studentmoneymanagement/


			Log.v("wjjii", "fkman");

			try {
				fis = new FileInputStream (Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.example.studentmoneymanagement/recentpurchases.txt");

				InputStreamReader inputreader = new InputStreamReader(fis);//(Environment.getExternalStorageDirectory().getPath() + "/Android/data/com.example.studentmoneymanagement/recentpurchases.txt")); 
				BufferedReader buffreader = new BufferedReader(inputreader);

				StringBuffer stringBuffer = new StringBuffer();                

				String strLine;
				while ((strLine = buffreader.readLine()) != null) {
					
					Log.v("wddddde", strLine);
					tempList.add(strLine);
				}
				Log.v("zzzzzzzzzz", "g");

			}
			catch  (Exception e) {  
				e.printStackTrace();
			}

		return tempList;
	}

	public ArrayList<String> getPurchases(){
		return jointList;
	}

	public double getTotalCosts(){ 

		double temp = 0;

		for(int count = 0; count < purchaseCost.size(); count++)
			temp+=Double.parseDouble(purchaseCost.get(count));

		return temp;

	}
}

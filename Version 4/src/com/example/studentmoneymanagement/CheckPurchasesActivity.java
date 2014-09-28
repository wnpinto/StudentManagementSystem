package com.example.studentmoneymanagement;

import java.util.ArrayList;
import com.example.studentmoneymanagement.R;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class CheckPurchasesActivity extends MainActivity {

	
	//DEFINING STRING ADAPTER WHICH WILL HANDLE DATA OF LISTVIEW
	ListView purchasesList;
	String strLine = null;


	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.check_purchases_main);
		RecentPurchacesSQLDatasource purchasesDatasource = new RecentPurchacesSQLDatasource(this);
		
		//retrieve all data from purchases database.
		purchasesList = (ListView) findViewById(R.id.purchaseList);
	//	purchasesDatasource.open();
		ArrayList<String[]> data = purchasesDatasource.readDataAll();
	//	purchasesDatasource.close();
		
		String[] purchases = new String[data.size()];
		
		for(int index = 0; index < purchases.length; index++){
			purchases[index] = data.get(index)[0] + "\t" + data.get(index)[1] + "\t" +  data.get(index)[2];
			
		}
		
		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1, purchases);
		purchasesList.setAdapter(adapter);


	//	getRecentPurchase();
		//adapter.notifyDataSetChanged();
		
		//listItems.add(rp.getPurchases);
		adapter.notifyDataSetChanged();
		//backButton = (Button) findViewById(R.id.backbutton2);
	//	backButton.setOnClickListener(new View.OnClickListener() {
		//	public void onClick(View v) {
	//			finish();
	//		}
	//	});
	}
	
	
}
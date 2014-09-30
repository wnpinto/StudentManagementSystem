package com.example.studentmoneymanagement;

import java.util.ArrayList;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PlansActivity extends MainActivity {
	private ListView plansList;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.plans_activity_layout);
		
		 plansList = (ListView) findViewById(R.id.plansList);
		WalletSQLDatasource walletDatasource = new WalletSQLDatasource(this);
		walletDatasource.open();
		ArrayList<String[]> data = walletDatasource.readDataAll();
		walletDatasource.close();
		
		String[] plans = new String[data.size()];
		
		for(int index = 0; index < data.size(); index++){
			plans[index] = data.get(index)[0] + "\t" + data.get(index)[1] + "\t" + data.get(index)[2];
		}
		
		  ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	              android.R.layout.simple_list_item_1, android.R.id.text1, plans);
		  
		  plansList.setAdapter(adapter); 
		  
		  /*
		   * // ListView Item Click Listener
            listView.setOnItemClickListener(new OnItemClickListener() {
 
                  @Override
                  public void onItemClick(AdapterView<?> parent, View view,
                     int position, long id) {
                    
                   // ListView Clicked item index
                   int itemPosition     = position;
                   
                   // ListView Clicked item value
                   String  itemValue    = (String) listView.getItemAtPosition(position);
                      
                    // Show Alert 
                    Toast.makeText(getApplicationContext(),
                      "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                      .show();
                 
                  }
    
             }); 
        }
		   *  
		   */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

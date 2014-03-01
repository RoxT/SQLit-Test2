package com.example.sqlitetest2;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {
	
	ArrayList<String> employeeList;
	MySQLitehelper database = new MySQLitehelper(this);

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		employeeList = database.getEntry("first");
		if (employeeList.size() != 0) {
			// make the Array Adapter <String> adapter
			ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,
					android.R.layout.simple_list_item_1, employeeList);
			
			// set our adapter
			setListAdapter(adapter);
		}

		
	}
	
	public void showAddForm(View view) {
		Intent intent = new Intent(this, AddNew.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

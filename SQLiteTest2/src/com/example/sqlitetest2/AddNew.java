package com.example.sqlitetest2;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class AddNew extends Activity {
	
	EditText editTextFirstName;
	EditText editTextLastName;
	EditText editTextAge;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new);
		// Show the Up button in the action bar.
		setupActionBar();
		
		editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
		editTextLastName = (EditText) findViewById(R.id.editTextLastName);
		editTextAge = (EditText) findViewById(R.id.editTextAge);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new, menu);
		return true;
	}

	public void saveEntry(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		
		// get all the info
		String first = editTextFirstName.getText().toString();
		String last = editTextLastName.getText().toString();
		int age = Integer.parseInt(editTextAge.getText().toString());
		
		// Add to bd
		MySQLitehelper database = new MySQLitehelper(getBaseContext());
		database.addEmployee(database, first, last, age);
		
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}

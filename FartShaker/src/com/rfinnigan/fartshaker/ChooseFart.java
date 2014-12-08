package com.rfinnigan.fartshaker;

import android.app.Activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;


public class ChooseFart extends Activity {
	static Intent resultIntent = new Intent();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_fart);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choose_fart, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	/**
	 * method called when ok button is pressed
	 */
	public void done(View view){

		
		finish();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_choose_fart,
					container, false);
			/**
			 * populate the spinner manually using the Fart Sound Resource Id's
			 */
			//first create an empty array adapter
			ArrayAdapter<CharSequence> adapter = 
					new ArrayAdapter <CharSequence>(
							rootView.getContext(),android.R.layout.simple_spinner_dropdown_item);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			//fill with Strings (of fart names from Resources)
			for (int i=0; i<MainActivity.getNumberOfFarts();i++){
				int j = MainActivity.getSpecificFartId(i);
				adapter.add(getResources().getResourceEntryName(j));
			}
			//pass the array adapter to the spinner
			Spinner fartSelector = (Spinner) rootView.findViewById(R.id.fart_selector);
			fartSelector.setAdapter(adapter);
			
			//set on item selected listener to set the fartId to the selected fart sound
			fartSelector.setOnItemSelectedListener(new OnItemSelectedListener() {
				
				//boolean to check if this is first selection
				boolean firstSelection = true;
				
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					//if this is first selection(ie spinner defaulting to option 1) do nothing except change boolean
					if(firstSelection==true){
						firstSelection=false;
					}
					//if this isnt the first selection set the fart to the number selected
					else{
					MainActivity.setFart(pos);
					}
				}


				public void onNothingSelected(AdapterView<?> parent) {
				}
			});
			return rootView;

		}
	}


}

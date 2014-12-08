package com.rfinnigan.fartshaker;



import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends Activity{

	private MediaPlayer mp;
	private static FartSound farts = new FartSound();
	private static MainActivity instance;

	
	private final static String TAG = "fart"; //string for logcat documentation

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	//getter and setter methods for fartIds
	public int getFartId(){
		return farts.getCurrentFartID();
	}
	public static void setFart(int i){
		farts.setFart(i);
		farts.getCurrentFart().playFart(getContext());
		
	}
	public static int getNumberOfFarts(){
		return farts.getNumberOfFarts();
	}
	public static int getSpecificFartId(int i){
		return farts.getSpecificFartID(i);
	}
	//getter method for context
	public static Context getContext(){
		return instance;
	}

	/**
	 * called when the fart button is pressed
	 * 
	 */
	public void playFart(View view) {

		farts.getCurrentFart().playFart(this);
	}
	
	/**
	 * called when select fart button is pressed
	 */
	public void launchFartSelection(View view){
		Intent pickFartIntent = new Intent(this, ChooseFart.class);
		startActivity(pickFartIntent);
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}



}

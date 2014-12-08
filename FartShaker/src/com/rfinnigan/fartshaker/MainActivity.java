package com.rfinnigan.fartshaker;


import java.lang.reflect.Field;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity{
	//public final static String EXTRA_MESSAGE = "com.rfinnigan.fartshaker.MESSAGE";

	private MediaPlayer mp;
	private static FartSound farts = new FartSound();

	private static final int PICK_FART_REQUEST=1; // request code for choose fart activity
	private final static String TAG = "fart"; //string for logcat documentation

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
	}
	public static int getNumberOfFarts(){
		return farts.getNumberOfFarts();
	}
	public static int getSpecificFartId(int i){
		return farts.getSpecificFartID(i);
	}

	/**
	 * called when the fart button is pressed
	 * 
	 */
	public void playFart(View view) {

		//create media player & have mediaplayer play selected fart sound
		mp = MediaPlayer.create(this, getFartId());
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
			public void onCompletion(MediaPlayer player) {
				player.release();  
				Log.i(TAG,"player released");	   
			}});
		mp.start();

	}
	/**
	 * called when select fart button is pressed
	 */
	public void launchFartSelection(View view){
		Intent pickFartIntent = new Intent(this, ChooseFart.class);
		startActivityForResult(pickFartIntent, PICK_FART_REQUEST);
	}
	/**
	 * onActivityResult method to handle fart choice
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PICK_FART_REQUEST){
			if(resultCode== RESULT_OK){
				setFart(data.getIntExtra("fart", 0));
			}
		}
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

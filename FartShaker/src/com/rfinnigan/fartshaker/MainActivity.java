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
	public final static String EXTRA_MESSAGE = "com.rfinnigan.fartshaker.MESSAGE";

	private MediaPlayer mp;
	private static int fartId=R.raw.fart0;
	private static int fartSoundsArray[] = fillSoundsArray();
	
	//string for logcat documentation
	private final static String TAG = "fart";

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
	public static int[] fillSoundsArray(){
		Field[] ID_Fields = R.raw.class.getFields();
		int[] resArray = new int[ID_Fields.length];
		for(int i = 0; i < ID_Fields.length; i++) {
		    try {
		        resArray[i] = ID_Fields[i].getInt(null);
		    } catch (IllegalArgumentException | IllegalAccessException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
		}
		return resArray;
	}

	/**
	 * called when fart button is pressed
	 */

	public void playFart(View view) {
		
		//create media player & have mediaplayer play selected fart sound
		mp = MediaPlayer.create(this, fartId);
		//boolean released = false;
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
			public void onCompletion(MediaPlayer player) {
			   player.release();  
			   Log.i(TAG,"player released");
			   
			   
			   
			}});
		mp.start();
		
	}

	/** 
	 * Called when the user clicks the send button
	 */
	public void sendMessage(View view){
		//do something in response to the button

		Intent intent = new Intent (this, DisplayMessageActivity.class); 
		//EditText editText = (EditText) findViewById(R.id.edit_message);
		//String message = editText.getText().toString();
		String message = "FART!!!";
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
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

			//populate the spinner for dropdown fart selection in the fragment
			Spinner fartSelector = (Spinner) rootView.findViewById(R.id.fart_selector);
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
					rootView.getContext(), R.array.fart_array, R.layout.dropdown_item);
			fartSelector.setAdapter(adapter);
			

			fartSelector.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					fartId=fartSoundsArray[pos];
					Toast.makeText(
							parent.getContext(),
							"The fart is "
									+ parent.getItemAtPosition(pos).toString(),
									Toast.LENGTH_LONG).show();
					
					
					
					
				}


				public void onNothingSelected(AdapterView<?> parent) {
				}
			});
			return rootView;
		}
	}



}

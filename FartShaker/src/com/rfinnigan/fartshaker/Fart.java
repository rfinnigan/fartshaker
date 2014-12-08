package com.rfinnigan.fartshaker;

import android.content.Context;
import android.media.MediaPlayer;


public class Fart {
	private int fartId;
	private MediaPlayer mp;


	//Constructor
	Fart(int Id){
		fartId=Id;

	}

	//getter method for fart resource Id
	public int getId() {
		
		return fartId;
	}
	//method to play fart sound
	public void playFart(Context context){
		//create media player & have mediaplayer play selected fart sound
		mp = MediaPlayer.create(context, getId());
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
			public void onCompletion(MediaPlayer player) {
				player.release();  
				//Log.i(MainActivity.TAG,"player released");	   
			}});
		mp.start();
	}




}

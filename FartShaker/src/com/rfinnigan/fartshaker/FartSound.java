package com.rfinnigan.fartshaker;

import java.lang.reflect.Field;

public class FartSound {

	/**
	 * @param args
	 */
	public final static int DEFAULT_FART = 0;
	private static int fartSoundsArray[] = fillSoundsArray();
	private int fart = DEFAULT_FART;
	

	//method to fill array with resource ID's of fart sounds
	private static int[] fillSoundsArray(){
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

	//setter and getter methods for fart
	public int setFart(int i){
		if(i<fartSoundsArray.length && i>=0){
			return fartSoundsArray[i];
		}
		else{
			return fartSoundsArray[DEFAULT_FART];
		}
	}
	public int getFart(){
		return fart;
	}
	public int getFartID(){

		return fartSoundsArray[fart];

	}

}

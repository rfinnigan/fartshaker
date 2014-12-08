package com.rfinnigan.fartshaker;

import java.lang.reflect.Field;


/**
 * Object for storing the array of resource ID's for the fart sounds and the currently selected fart sound
 * @author ruairidhfinnigan
 *
 */

public class FartSound {

	/**
	 * @param args
	 */
	public final static int DEFAULT_FART = 0;
	private static Fart[] fartSoundsArray = fillSoundsArray();
	private int currentFart = DEFAULT_FART;
	

	//method to fill array with resource ID's of fart sounds
	private static Fart[] fillSoundsArray(){
		Field[] ID_Fields = R.raw.class.getFields();
		
		Fart[] resArray = new Fart[ID_Fields.length];;
		for(int i = 0; i < ID_Fields.length; i++) {
			try {
				Fart temp = new Fart (ID_Fields[i].getInt(null));
				resArray[i] = temp;
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resArray;
	}

	//setter and getter methods for fart
	public void setFart(int i){
		if(i<fartSoundsArray.length && i>=0){
			currentFart=i;
		}
		else{
			currentFart=DEFAULT_FART;
			
		}
	}
	public Fart getCurrentFart(){
		return fartSoundsArray[currentFart];
	}
	public int getCurrentFartID(){
		return fartSoundsArray[currentFart].getId();

	}
	public Fart getSpecificFart(int i){
		return fartSoundsArray[i];
	}
	public int getSpecificFartID(int i){
		return fartSoundsArray[i].getId();

	}
	public int getNumberOfFarts(){
		return fartSoundsArray.length;
	}

}

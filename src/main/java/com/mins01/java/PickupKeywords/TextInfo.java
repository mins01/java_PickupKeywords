package com.mins01.java.PickupKeywords;

public class TextInfo {
	public String tag="";
	public String text="";
	public int score=0;
	public String toString(){
		return tag+","+text+","+Integer.toString(score,10);
	}

}

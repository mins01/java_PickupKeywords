package com.mins01.java.PickupKeywords;

public class WordInfo{
	public String word="";
	public long count=0;
	public long score=0;
	public String toString(){
		return word+","+Long.toString(count,10)+","+Long.toString(score,10);
	}
}

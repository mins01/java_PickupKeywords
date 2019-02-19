package com.mins01.java.PickupKeywords;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args){
		
		//========================
		System.out.println("START");
		System.out.println("====================");
		//========================
		
		PickupKeywords pk = new PickupKeywords();
//		String html = pk.getHTML("http://domeggook.com/main/");
//		pk.setHTML(html);
		try{
			pk.setUrl("http://domeggook.com/main/");
			ArrayList<TextInfo> texts= pk.getTexts(); //get texts
			ArrayList<WordInfo> words= pk.getWords(texts); // generate words from texts
			words.subList(0, 10).forEach(System.out::println);	
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		//========================
		System.out.println("====================");
		//========================
		
		GetAppInfoByPackagename gaibp = new GetAppInfoByPackagename();
		String pkname = "com.mins01.othello001";
		try{
			gaibp.setPackagename(pkname);
			ArrayList<TextInfo> texts2= gaibp.getTexts(); //get texts
			ArrayList<WordInfo> words2= gaibp.getWords(texts2); // generate words from texts
			words2.subList(0, 10).forEach(System.out::println);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//========================
		System.out.println("====================");
		System.out.println("END");
		//========================
		
	}
}

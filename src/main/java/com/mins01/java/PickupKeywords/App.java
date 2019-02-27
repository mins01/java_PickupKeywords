package com.mins01.java.PickupKeywords;

import java.util.ArrayList;
import java.util.function.Consumer;


/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args){
		
		
		Consumer<WordInfo> print = new Consumer<WordInfo>() {
			@Override
			public void accept(WordInfo x) {
				System.out.println(x);
			}
		};
		
		//========================
		System.out.println("START");
		//========================		
		PickupKeywords pk = new PickupKeywords();
		ArrayList<TextInfo> texts = null;
		ArrayList<WordInfo> words = null;
		GetAppInfoByPackagename gaibp = new GetAppInfoByPackagename();
		System.out.println("====================");
		
		String url = "http://domeggook.com/main/";
		System.out.println("url : "+url);
		try{
			pk.setUrl(url);
			texts= pk.getTexts(); //get texts
			words= pk.getWords(texts); // generate words from texts
			words.subList(0, Math.min(10, words.size())).forEach(print);	
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR");
		}
		
		System.out.println("====================");
		
		url = "https://www.amazon.com/s?k=cake&ref=is_s";
		System.out.println("url : "+url);
		try{
			pk.setUrl(url);
			texts= pk.getTexts(); //get texts
			words= pk.getWords(texts); // generate words from texts
			words.subList(0, Math.min(10, words.size())).forEach(print);	
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR");
		}
		
		
		
		System.out.println("====================");
		url = "http://aldkjlkjasdlja.asdjkljasd/main/";
		System.out.println("url : "+url);
		try{
			pk.setUrl(url);
			texts= pk.getTexts(); //get texts
			words= pk.getWords(texts); // generate words from texts
			words.subList(0, 10).forEach(print);	
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR");
		}
		System.out.println("====================");
		url = "https://www.google.com/asd";
		System.out.println("url : "+url);
		try{
			pk.setUrl(url);
			texts= pk.getTexts(); //get texts
			words= pk.getWords(texts); // generate words from texts
			words.subList(0, 10).forEach(print);	
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR");
		}
		
		
		//========================
		System.out.println("====================");
		String pkname = "com.mins01.othello001";
		System.out.println("packagename : "+pkname);
		try{
			gaibp.setPackagename(pkname);
			texts= gaibp.getTexts(); //get texts
			words= gaibp.getWords(texts); // generate words from texts
			words.subList(0, 10).forEach(print);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR");
		}

		System.out.println("====================");
		pkname = "com.mins01.othello001xxx";
		System.out.println("packagename : "+pkname);
		try{
			gaibp.setPackagename(pkname);
			texts= gaibp.getTexts(); //get texts
			words= gaibp.getWords(texts); // generate words from texts
			words.subList(0, 10).forEach(print);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR");
			
		}
		//========================
		System.out.println("====================");
		System.out.println("END");
		//========================
		
	}
}

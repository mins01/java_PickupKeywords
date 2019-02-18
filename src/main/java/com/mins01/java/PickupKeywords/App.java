package com.mins01.java.PickupKeywords;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) throws Exception{
		PickupKeywords pk = new PickupKeywords();
		String html = pk.getHTML("http://domeggook.com/main/");
		pk.setHTML(html);
//		System.out.println(html);
		
//		pk.setHTML(html);
//		pk.getCharset(html);
//		pk.getMetas();
//		System.out.println(pk.getTexts());
		System.out.println(pk.split_tags_string("123,232"));
	}
}

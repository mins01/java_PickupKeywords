package com.mins01.java.PickupKeywords;

import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GetAppInfoByPackagename extends PickupKeywords {
	public String jsonString_conf_scores = "{\"title\":100,\"meta-description\":90,\"meta-keywords\":100,\"meta-og:title\":100,\"meta-og:description\":90}";
//	public String search_tags = "title";
	public GetAppInfoByPackagename(){
//		super.jsonString_conf_scores = this.jsonString_conf_scores;
		super();
		this.search_tags = "title";

		conf_scores = (JsonObject)(new JsonParser()).parse(this.jsonString_conf_scores);
	}
	public void setPackagename(String packagename) throws Exception{
		this.setUrl("https://play.google.com/store/apps/details?id="+packagename);
	}
	
	public ArrayList<TextInfo> getTexts(){
		ArrayList<TextInfo> tags = super.getTexts();
		for(int i=0,m=tags.size();i<m;i++){
			tags.get(i).text = tags.get(i).text.replace("Apps on Google Play", "");
		}
		return tags;
	}
}

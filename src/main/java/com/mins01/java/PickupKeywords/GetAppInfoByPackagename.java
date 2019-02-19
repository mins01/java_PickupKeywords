package com.mins01.java.PickupKeywords;

public class GetAppInfoByPackagename extends PickupKeywords {
	public String jsonString_conf_scores = "{\"h1\":50,\"h2\":40,\"h3\":30,\"h4\":20,\"h5\":10,\"h6\":10,\"title\":100,\"span\":10,\"a\":20,\"li\":10,\"meta-description\":90,\"meta-keywords\":90,\"meta-og:title\":100,\"meta-og:description\":90}";
	public GetAppInfoByPackagename(){
		super();
	}
	public void setPackagename(String packagename) throws Exception{
		this.setUrl("https://play.google.com/store/apps/details?id="+packagename);
	}
}

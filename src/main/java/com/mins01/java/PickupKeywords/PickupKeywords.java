package com.mins01.java.PickupKeywords;
import org.jsoup.Jsoup; 
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PickupKeywords {
	Document doc = null;
	public String jsonString_conf_scores = "{\"h1\":50,\"h2\":40,\"h3\":30,\"h4\":20,\"h5\":10,\"h6\":10,\"title\":100,\"span\":5,\"a\":1,\"li\":5,\"meta-description\":50,\"meta-keywords\":50,\"meta-og:title\":100,\"meta-og:description\":25}";
	public JsonObject conf_scores = null;
	public PickupKeywords(){
		conf_scores = (JsonObject)(new JsonParser()).parse(this.jsonString_conf_scores);
//		System.out.println(conf_scores.get("h1"));
		
	}
	public String getHTML(String strURL) throws Exception{
		String html = Jsoup.connect(strURL).get().html();
		return html;
	}
	public String getHTML(URL iURL) throws Exception{

		return this.getHTML(iURL.toString());
	}
	public void setUrl(URL iURL) throws Exception{
		this.setHTML(this.getHTML(iURL));
	}
	public void setUrl(String strURL) throws Exception{
		this.setHTML(this.getHTML(strURL));
	}
	public void setHTML(String html){
		String charset = getCharset(html);
		if(charset != "utf-8"){
			html.replaceAll("(?i)"+charset, "utf-8");
		}
		this.doc = Jsoup.parse(html,charset);
//		System.out.print(doc.toString());
	}
	public String getCharset(String html){
//		System.out.println(html);
		Pattern p = Pattern.compile("(?:(?i)(?:charset=[\"\\']?)([^\"\\',\\s\\t\\n]+)(?:[\"\\',\\s\\t\\n]?))",1);
	    Matcher m = p.matcher(html);
	    if(m.find()){
	    	return m.group(1).toLowerCase();
	    }
		return "utf-8";
	}
	public int min_length = 2;
	public int max_length = 100;
	public String search_tags = "h1,h2,h3,h4,h5,title,span,div,li,a";
	public String search_metas = "meta[name=\"description\"],meta[name=\"keywords\"],meta[property=\"og:title\"],meta[property=\"og:description\"]";
	
	
			
	
	
	public ArrayList<TextInfo> getMetas(){
		Elements els = this.doc.select(this.search_metas);
		ArrayList<TextInfo> al = new ArrayList<TextInfo>();
		for(Element el : els){
			TextInfo ti = new TextInfo();
			if(el.hasAttr("property")){
				ti.tag = "meta-"+el.attr("property");
			}else{
				ti.tag = "meta-"+el.tagName();
			}
			ti.text = el.attr("content");
			syncScore(ti);
		
			al.add(ti);
//			System.out.println(ti.toString());
		}
		return al;
	}
	public ArrayList<TextInfo> getTags(){
		Elements els = this.doc.select(this.search_tags);
		ArrayList<TextInfo> al = new ArrayList<TextInfo>();
		for(Element el : els){
//			System.out.println(el.tagName()+","+el.text().length()+","+el.childNodeSize());
			if(el.childNodeSize()>1){
				continue;
			}
			
			TextInfo ti = new TextInfo();
			ti.tag = el.tagName();
			ti.text = el.text();
			syncScore(ti);
//			System.out.println(ti.toString());
			if(ti.text.length()==0){continue;}
			al.add(ti);
//			System.out.println(ti.toString());
		}
		return al;
	}
	public void syncScore(TextInfo ti){
		if(conf_scores.has(ti.tag)){
			ti.score = conf_scores.get(ti.tag).getAsInt();
		}else{
			ti.score = 1;
		}
	}

	
	public ArrayList<TextInfo> getTexts(){
		ArrayList<TextInfo> metas = this.getMetas();
		metas.addAll(this.getTags());
		return metas;
	}
	
	public ArrayList<String> split_tags_string(String str){
		String strPattern = "([^#\\t\\s\\n\\x00-\\x2C\\x2E-\\x2F\\x3A-\\x40\\x5B-\\x5E\\x60\\x7B-\\x7F、，。]{"+this.min_length+","+this.max_length+"})"; 
		
		Pattern p = Pattern.compile(strPattern);
	    Matcher m = p.matcher(str);
	    ArrayList<String> matches = new ArrayList<String>();
	    
	    while(m.find()){
	    	matches.add(m.group());
	    }
	    return matches;
	}
	

	
	public ArrayList<WordInfo> getWords(ArrayList<TextInfo> texts ){
		HashMap<String, WordInfo> kv = new HashMap<String, WordInfo>();
		ArrayList<WordInfo> alwi = new ArrayList<WordInfo>();
		WordInfo wi = null;
		for(TextInfo text : texts){
			ArrayList<String> splited_texts = split_tags_string(text.text);
			for(int i = 0,m=splited_texts.size();i<m;i++){
				String st = splited_texts.get(i);
				
				if((wi =kv.get(st))==null){
					wi = new WordInfo();
					wi.word = st;
					kv.put(st, wi);
					alwi.add(wi);
				}
				wi.count++;
				wi.score += text.score;				
			}
		}
		Collections.sort(alwi, new Comparator<WordInfo>() {
		    public int compare(WordInfo lhs, WordInfo rhs) {
		    	long r = rhs.score - lhs.score;
				if(r == 0){
					r = rhs.score/rhs.count - lhs.score/lhs.count;
				}
				return Math.round(r);
		    }
		});
		return alwi;
	}
}


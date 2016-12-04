package com.dream.logs.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Gifjia {
	public static final String GIFURL = "http://www.gifjia.com/neihan/page/";
	static final int timeout = 36000;
	static final String pageCon ="h2 > a";
	
	public Document getByUrl(String url) throws Exception{
		Document doc = Jsoup.connect(url).timeout(timeout).get();
		return doc;
	}
	public Document getByPage(int i) throws Exception{
		return getByUrl(GIFURL+i);
	}
	
	public List<GifjiaModel> getFromPage(int end){
		List<GifjiaModel> list = new ArrayList<GifjiaModel>();
		for(int i=1;i<end;i++){
			try {
				Document doc = getByPage(i);
				Elements elements = doc.select(pageCon);
				Iterator<Element> ite = elements.iterator();
				while(ite.hasNext()){
					Element ele = ite.next();
					System.out.println(ele.attr("href"));
					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		return list;
	}
	
	public static void main(String args[]) throws Exception{
		Gifjia gif =  new Gifjia();
		gif.getFromPage(2);
	
		
	}
	
}

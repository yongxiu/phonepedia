package cn.edu.nju.software.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Preferences {
	
	public static final String PREFS_NAME = "MyPrefsFile";
	public static final int BAIDU_PEDIA = 0;
	public static final int WIKI_PEDIA = 1;
	public static final int HUDONG_PEDIA = 2;
	
	public static final String PEDIA_SEARCH_URL = "http://pediault.appspot.com/search";
	
	public static final String POP_WD_URL = "http://pediault.appspot.com/pop";
	
	public static String getUrl(String wd, int type, int page) {
		try {
			return PEDIA_SEARCH_URL + "?type=" + type + "&page=" + page + "&wd=" + URLEncoder.encode(wd, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "http://pediault.appspot.com/error";
		}
	}
	
	public static String getPopUrl(int type) {
		return POP_WD_URL + "?type=" + type;
	}
}

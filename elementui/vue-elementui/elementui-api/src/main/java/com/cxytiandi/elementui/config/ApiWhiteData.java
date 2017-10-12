package com.cxytiandi.elementui.config;

import java.util.ArrayList;
import java.util.List;
/**
 * API白名单列表
 * @author yinjihuan
 *
 */
public class ApiWhiteData {
	private static List<String> whiteApis = new ArrayList<String>();
	static {
		whiteApis.add("/user/login");
		whiteApis.add("/file/upload");
	}
	public static List<String> getWhiteApis() {
		return whiteApis;
	}
	
	public static void add(String uri) {
		whiteApis.add(uri);
	}
}

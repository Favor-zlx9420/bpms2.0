package com.rong.console.center.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * cookie 相关操作
 * @author lether
 *
 */
public class CookieHelper {
	/**
	 * 设置一个cookie
	 * @param response 1
	 * @param cookie_name 2
	 * @param cookie_val 3
	 * @param minutes 存活时长，以分钟 为单位
	 * @return 4
	 */
	public static boolean addCookie(HttpServletResponse response,String cookie_name,String cookie_val,int minutes){
		try{
			cookie_val=URLEncoder.encode(cookie_val, "UTF-8");
		}catch (Exception e) {
			cookie_val="";
		}
		Cookie cookie=new Cookie(cookie_name, cookie_val);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		//cookie.setSecure(true);https专属
		if(minutes>0){
			cookie.setMaxAge(minutes*60);
		}
		response.addCookie(cookie);
		return true;
	}
	/**
	 * 编辑cookie
	 * @param request 1
	 * @param response 2
	 * @param cookie_name 3
	 * @param cookie_val 4
	 * @param duration 存活时长，以分钟为单位
	 * @return  4
	 */
	public static boolean editCookie(HttpServletRequest request,HttpServletResponse response,String cookie_name,String cookie_val,int duration){
		try{
			cookie_val=URLEncoder.encode(cookie_val, "UTF-8");
		}catch (Exception e) {
			cookie_val="";
		}
		Cookie[] cookies=request.getCookies();
		for(Cookie cookie:cookies){
			if(cookie.getName().equals(cookie_name)){
				cookie.setPath("/");
				cookie.setValue(cookie_val);
				cookie.setMaxAge(duration*60);
				//cookie.setSecure(true);https专属
				cookie.setHttpOnly(true);
				response.addCookie(cookie);
				return true;
			}
		}
		return false;
	}
	/**
	 * 清除一个cookie
	 * @param request 1
	 * @param response 2
	 * @param cookie_name 3
	 */
	public static void removeCookie(HttpServletRequest request,HttpServletResponse response,String cookie_name){
		Cookie cookie=new Cookie(cookie_name,null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	/**
	 * 根据名字获取cookie
	 * @param request 1
	 * @param name cookie名字
	 * @return 1
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
		Cookie cookies[] = request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals(name)){
					return cookie;
				}
			}
		}
		return null;
	}
	
}

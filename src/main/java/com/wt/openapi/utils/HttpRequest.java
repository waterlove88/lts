package com.wt.openapi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class HttpRequest {
	
	/** Logger */
	Log logger = LogFactory.getLog(getClass());

	protected HttpServletRequest req;
	protected HttpServletResponse res;
	protected String strNullParam ;
	protected String m_method ;
	
	/**
	 * 초기 설정
	 * @param req - Request를 받는다.
	 */
	public HttpRequest(HttpServletRequest req){
		this.req = req;
		strNullParam = null;
		m_method = req.getMethod();
	}
	
	/**
	 * 초기 설정
	 * @param res - Response을 받는다.
	 */
	public HttpRequest(HttpServletResponse res){
		this.res = res;
	}
	
	/**
	 * 초기 설정
	 * @param req - Request를 받는다.
	 * @param res - Response을 받는다.
	 */
	public HttpRequest(HttpServletRequest req, HttpServletResponse res){
		this.req = req;
		this.res = res;
		
		strNullParam = null;
		m_method = req.getMethod();
	}
	
	/**
	 * 초기 설정
	 * @param res - Response을 받는다.
	 * @param req - Request를 받는다.
	 */
	public HttpRequest(HttpServletResponse res, HttpServletRequest req){
		this.req = req;
		this.res = res;
		
		strNullParam = null;
		m_method = req.getMethod();
	}
	
	/**
	 * request로 받은 문자열을 String형으로 반환한다.<br>
	 * null일경우 cfg를 반환한다.
	 * @param val - 파라미터에서 넘어온 받는값
	 * @param cfg - 빈값일경우의 리턴값
	 * @return String
	 */
	public String getString(String val, String cfg){
		return isNull(req.getParameter(val),cfg);
	}
	
	/**
	 * request로 받은 문자열을 String형으로 반환한다.<br>
	 * null일경우 ""를 반환한다.
	 * @param val - 파라미터에서 넘어온 받는값
	 * @return String
	 */
	public String getString(String val){
		return getString(val,"");
	}
	
	/**
	 * request로 받은 문자열을 Integer형으로 반환한다.<br>
	 * null일경우 cfg를 반환한다.
	 * @param val - 파라미터에서 넘어온 받는값
	 * @param cfg - 빈값일경우의 리턴값
	 * @return Integer
	 */
	public int getInt(String val, int cfg){
		return isNull(req.getParameter(val), cfg);
	}
	
	/**
	 * request로 받은 문자열을 Integer형으로 반환한다.<br>
	 * null일경우 0를 반환한다.
	 * @param val - 파라미터에서 넘어온 받는값
	 * @return
	 */
	public int getInt(String val){
		return getInt(val,0);
	}
	
	/**
	 * 배열을 받아서 String[]로 반환한다.
	 * @param val - 파라미터에서 넘어온 받는값
	 * @return String[]
	 */
	public String[] getStrings(String val){
		String[] array = null;
		
		array = req.getParameterValues(val);
		
		return array;
	}
	
	/**
	 * request로 넘어온 파라메터 값을 자동으로 Map에 셋팅 한다.
	 * @return Map 
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Object> getParameterNameAndValueMap(){

		Map<String, Object>   map  = new HashMap<String, Object>() ; 
        Enumeration paramNames = req.getParameterNames() ;
        logger.debug("############# param Setting Start #############");
        while( paramNames.hasMoreElements() ) {
            String   paramName     = (String) paramNames.nextElement() ;
            String[] paramValueL   = req.getParameterValues(paramName) ;
            String   paramValue    = paramValueL[0] ;            
            
            logger.debug(paramName + ":" + paramValue);
            
            map.put(paramName, paramValue) ;
        }
        
        /**
         * header data setting
         */
        Enumeration e = req.getHeaderNames();
        String contentType = "";
        
        while( e.hasMoreElements() ) {        	
        	String n = (String) e.nextElement();        	
        	logger.debug(n + ":" + req.getHeader(n));        	
        	map.put(n, req.getHeader(n));
        	if( "Content-Type".equalsIgnoreCase(n) ){
        		contentType = req.getHeader(n);
        	}
        }

        
        /**
         * JSON Data setting
         */
        getHeaderParseJsonDataMap(map, req.getHeader("datas"), "");
        
        logger.debug("############# param Setting End #############");
        
        logger.debug("##################################");
        logger.debug("## > param : " + map.toString());
        logger.debug("##################################");
        
		return map;
	}
	
	/**
	 * request로 넘어온 파라메터 값을 자동으로 Map에 셋팅 한다.
	 * @return Map 
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Object> getParameterNameAndValueMap(String obj){
		
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
        Enumeration paramNames = req.getParameterNames() ;
        
        logger.debug("############# param Setting Start #############");
        while( paramNames.hasMoreElements() ) {
            String   paramName     = (String) paramNames.nextElement() ;
            String[] paramValueL   = req.getParameterValues(paramName) ;
            String   paramValue    = paramValueL[0] ;
            
            
            logger.debug(paramName + ":" + paramValue);
            //map.put(paramName, paramValue);
            
            if( paramName.length() > obj.length() ){
            	if( obj.equals(paramName.substring(0, obj.length())) ){
            		
                	String key = replace(paramName,"[", ".");
                	key = replace(key,"]", "");
                	
                	String num = key.replaceAll(obj+".", "");
                	num = num.substring(0, num.indexOf("."));
                	
                	if( lists.size() == 0 ){
                		Map<String, Object> datas = new HashMap<String, Object>();
                		lists.add(datas);
                	} else {
                		if( lists.size() < (Integer.parseInt(num) + 1) ){
                			Map<String, Object> datas = new HashMap<String, Object>();
                    		lists.add(datas);
                		}
                	}
                	
            		String dataKey = key.replaceAll(obj+"."+num+".", "");
                	lists.get(Integer.parseInt(num)).put(dataKey, paramValue);
                	
                }else{
                	map.put(paramName, paramValue);
                }
            }else{
            	map.put(paramName, paramValue);
            }
        }
        
        
        /**
         * header data setting
         */
        
        Enumeration e = req.getHeaderNames();
        String contentType = "";
        
        while( e.hasMoreElements() ) {        	
        	String n = (String) e.nextElement();
        	logger.debug(n + ":" + req.getHeader(n));        	
        	map.put(n, req.getHeader(n));
        	if( "Content-Type".equalsIgnoreCase(n) ){
        		contentType = req.getHeader(n);
        	}
        }
        
        getHeaderParseJsonDataMap(map, req.getHeader("datas"), obj);
        
        logger.debug("############# param Setting End #############");
        
        logger.debug("##################################");
        logger.debug("## > param[obj:" + obj + "] : " + map.toString());
        logger.debug("##################################");
        
        if( lists.size() != 0 ){
        	map.put(obj, lists) ;
        }
        
		return map;
	}
	
	
	@SuppressWarnings("rawtypes")
	public String getOMCPameter(){
		StringBuffer param = new StringBuffer();
		
        Enumeration paramNames = req.getParameterNames() ;
        while( paramNames.hasMoreElements() ) {
            String   paramName     = (String) paramNames.nextElement() ;
            String[] paramValueL   = req.getParameterValues(paramName) ;
            String   paramValue    = paramValueL[0];
            
            param.append("|").append(paramName).append("=").append(paramValue);
        }

		StringBuffer readerParam = new StringBuffer();
		
		try {
			
			BufferedReader r = new BufferedReader(new InputStreamReader(req.getInputStream()));
			String line = null;
			while( (line = r.readLine()) != null){
				readerParam.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        /**
         * JSON Data setting
         */
        getHeaderParseJsonDataMap(param, req.getHeader("datas"), "");
		
        
		return param.toString().length() == 0 ? "":param.toString().substring(1);
	}
	
	/**
	 * Header에 json으로 들어있는 데이터를 Map형식으로 변경하여 param에 넣는다.
	 * @param map
	 * @param jsonStr
	 * @param obj
	 */
	@SuppressWarnings("rawtypes")
	private void getHeaderParseJsonDataMap(Map<String, Object> map, String jsonStr , String obj) {
		// parse json
		JSONObject jsonObj;
	
		if( null != jsonStr && !jsonStr.equals("") ) {
			
			if( "{".equals(jsonStr.substring(0,1)) ){
				try {
					jsonObj = new JSONObject(jsonStr);
					Iterator itr = jsonObj.keys();
					while(itr.hasNext()){
						String key = (String)itr.next();
						logger.debug(key + ":" + jsonObj.get(key));
						map.put(key, jsonObj.get(key));					
						
						if( obj.equals(key) ){
							JSONArray datas = jsonObj.getJSONArray(obj);
							List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
							for( int i = 0 ; i < datas.length() ; i++ ){
								JSONObject json = datas.getJSONObject(i);
								
								Iterator itr2 = json.keys();
								Map<String, Object> v = new HashMap<String, Object>();
								while(itr2.hasNext()){
									String key2 = (String)itr2.next();
									logger.debug(key + "-" + key2 + ":" +json.get(key2));
									v.put(key2, json.get(key2));
								}
								lists.add(v);
							}
							map.put(key, lists);
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
    }
	
	/**
	 * Header에 json으로 들어있는 데이터를 Map형식으로 변경하여 param에 넣는다.
	 * @param map
	 * @param jsonStr
	 * @param obj
	 */
	@SuppressWarnings("rawtypes")
	private void getHeaderParseJsonDataMap(StringBuffer param, String jsonStr , String obj) {
		// parse json
		JSONObject jsonObj;
	
		if( null != jsonStr && !jsonStr.equals("") ) {
			
			if( "{".equals(jsonStr.substring(0,1)) ){
				try {
					jsonObj = new JSONObject(jsonStr);
					Iterator itr = jsonObj.keys();
					while(itr.hasNext()){
						String key = (String)itr.next();
						
						if( obj.equals(key) ){
							param.append("|").append(key).append("=[");
							
							JSONArray datas = jsonObj.getJSONArray(obj);
							for( int i = 0 ; i < datas.length() ; i++ ){
								JSONObject json = datas.getJSONObject(i);
								
								Iterator itr2 = json.keys();
								StringBuffer sb = new StringBuffer();
								param.append("{");
								while(itr2.hasNext()){
									String key2 = (String)itr2.next();
									sb.append("|").append(key2).append("=").append(json.get(key2));
								}
								if( sb.length() != 0 ){
									param.append(sb.toString().substring(1)).append("}");
								}
								
							}
							param.append("]");
							
						}else{
							param.append("|").append(key).append("=").append(jsonObj.get(key));
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
    }
	
	/**
	 * String형 replace로 안되는 구분자를 replace 시킨다.
	 * @param str : 받는 문자
	 * @param ch : 변경될 문자
	 * @param eh : 변할 문자
	 * @return String - 변경된 문자
	 */
	public static String replace(String str, String ch,String eh){
		if(str == null) return null;
		if(ch == null) return null;
		if(eh == null) return null;

		String sTemp="";
		String eTemp="";

		int chLen = ch.length();
		int ehLen = eh.length();

		int m=0;
		int s=0;

		while(true){
			s = str.indexOf(ch, m);
			if (s == -1)
				break;
			sTemp = str.substring(0, s);
			eTemp = str.substring(s + chLen);
			str = sTemp + eh + eTemp;
			m = s + ehLen;
		}

		return str;
	}
	
	/**
	 * Object형을 String형으로 반환한다. 빈값 또는 null일경우 입력값 cvf를 반환한다.
	 * @param str : object형 문자
	 * @param cvf : null또는 빈문자일경우 반환할 문자
	 * @return String - 반환값
	 */
	public static String isNull( Object str , String cvf){
		String s = "";
		if( str == null ){
			s = cvf;
		}else{
			s = str.toString();
		}

		return s;
	}

	/**
	 * String형을 String형으로 반환한다. 빈값 또는 null일경우 입력값 cvf를 반환한다.
	 * @param str : String형 문자
	 * @param cvf : null또는 빈문자일경우 반환할 문자
	 * @return String - 반환값
	 */
	public static String isNull( String str , String cvf ){
		if( str == null ){
			str = cvf;
		}else if( str.equals("") ){
			str = cvf;
		}else if( str.equals("null")){
			str = cvf;
		}

		return str;
	}

	/**
	 * String형을 int형으로 반환한다. 숫자형이 아니거나 빈값 또는 null일경우 입력값 cvf를 반환한다.
	 * @param str : String형 문자
	 * @param cvf : null또는 빈문자일경우 반환할 숫자
	 * @return int - 반환값
	 */
	public static int isNull( String str , int cvf ){
		try{
			cvf = Integer.parseInt(str);
		}catch(Exception e){

		}

		return cvf;
	}

	/**
	 * Object형을 int형으로 반환한다. 숫자형이 아니거나 빈값 또는 null일경우 입력값 cvf를 반환한다.
	 * @param str : Object형 문자
	 * @param cvf : null또는 빈문자일경우 반환할 숫자
	 * @return int - 반환값
	 */
	public static int isNull( Object str , int cvf ){
		if( str == null )
			str = Integer.toString(cvf);
		String s = str.toString();
		try{
			cvf = Integer.parseInt(s);
		}catch(Exception e){

		}

		return cvf;
	}

	/**
	 * Object형을 double형으로 반환한다. 숫자형이 아니거나 빈값 또는 null일경우 입력값 cvf를 반환한다.
	 * @param str : Object형 문자
	 * @param cvf : null또는 빈문자일경우 반환할 double
	 * @return double - 반환값
	 */
	public static double isNull( Object str , double cvf ){
		if( str == null )
			str = Double.toString(cvf);
		String s = str.toString();
		try{
			cvf = Double.parseDouble(s);
		}catch(Exception e){

		}

		return cvf;
	}

	/**
	 * Object형을 long형으로 반환한다. 숫자형이 아니거나 빈값 또는 null일경우 입력값 cvf를 반환한다.
	 * @param str : Object형 문자
	 * @param cvf : null또는 빈문자일경우 반환할 long
	 * @return long - 반환값
	 */
	public static long isNull( Object str, long cvf){
		if( str == null )
			str = Long.toString(cvf);
		String s = str.toString();
		try{
			cvf = Long.parseLong(s);
		}catch(Exception e){

		}

		return cvf;
	}

	/**
	 * String형을 String형으로 반환한다. 빈값 또는 null일경우 ""을 반환한다.
	 * @param str : String형 문자
	 * @return String - 반환값
	 */
	public static String isNull( String str ){
		return isNull(str,"");
	}

	/**
	 * 빈값 체크 boolean 형으로 반환
	 * @param str : String형 문자
	 * @return boolean - 값에 대한 true, false
	 */
	public static boolean isNullBool( String str ){
		boolean bool = false;
		if( !isNull(str,"").equals("") )
			bool = true;

		return bool;
	}

	/**
	 * Object형을 String형으로 반환한다. 빈값 또는 null일경우 ""을 반환한다.
	 * @param str : Object형 문자
	 * @return String - 반환값
	 */
	public static String isNull( Object str ){
		return isNull(str,"");
	}

	/**
	 * String형을 int형으로 반환한다. 숫자형이 아니거나 빈값 또는 null일경우 0을 반환한다.
	 * @param str : String형 문자
	 * @return int - 반환값
	 */
	public static int isNullInt( String str ){
		return isNull(str,0);
	}

	/**
	 * 문자열을 open obj close 형식으로 반환한다. <br>
	 * 단, 빈값일경우 값을 빈값만 반환한다.
	 * @param obj : String형 문자
	 * @param open : ex) 괄호 '('
	 * @param close: ex) 괄호 ')'
	 * @return String - 반환값
	 */
	public static String isNullsc(Object obj , String open, String close){
		String str = isNull(obj, "");

		if( str.equals("") ){
			str = "";
		}else{
			str = open + str + close;
		}
		return str;
	}
}

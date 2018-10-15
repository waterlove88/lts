package com.wt.openapi.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class StringUtils {
	public static String TCD_POC = "0";

	/** text 출력문자를 html 변경 */	
	public static String[][] toText(){
		return new String[][]{
			new String[] { "&", "&amp;" },
			new String[] { "#", "&#35;"},
			new String[] { "<", "&lt;" },
			new String[] { ">", "&gt;" },
			new String[] { "\"", "&quot;" },
			new String[] { "(", "&#40;" },
			new String[] { ")", "&#41;" }
		};
	}

	/** html 출력 문자를 text 로 변경 */
	public static String[][] toHtml(){
		return new String[][]{
			new String[] { "&quot;", "\"" }, 
			new String[] { "&gt;", ">" }, 
			new String[] { "&lt;", "<" },
			new String[] { "&#40;", "(" },
			new String[] { "&#41;", ")" },
			new String[] { "&#35;", "#" },
			new String[] { "&amp;", "&" }
		};
	}
	
	/** html 출력 문자를 text 로 변경 */
	public static String[][] toTextXSS(){
		return new String[][]{
			new String[] { "&", "&amp;" },
			new String[] { ">", "&gt;" }, 
			new String[] { "<", "&lt;" },
			new String[] { "\"", "&quot;" }
			
		};
	}
	
	/** 파라미터를  sql 안전 문자로 변경 변수 */
	public static String[][] toSafeSql(){
		return new String[][] {
			new String[] { "'", "''" },
			new String[] { ";", "" },
			new String[] { "--", "" },
			new String[] { "|", "" },
			new String[] { ":", "" },
			new String[] { "+", "" },
			new String[] { "\\", "" },
			new String[] { "/", "" },
			new String[] { "|", "" },
		};
	}


	/**
	 * <pre>
	 * 입력받은 스트링 중에서 해당 문자열을 해당 단어로 치환한다.
	 * ex)
	 * String var1="가방";
	 * System.out.println(StringUtils.replace(var1, "가", "나"));
	 * > "나방"
	 * </pre>
	 * 
	 * @param str String 변경 대상 전체 String
	 * @param src String 변경 전 문자열
	 * @param tgt String 변경 후 문자열
	 * @return String 결과
	 */
	public static String replace(String str, String src, String tgt) {
		StringBuffer buf = new StringBuffer();
		String ch = null;
		if (str == null || str.length() == 0)
			return "";
		int i = 0;
		int len = src.length();
		while (i < str.length() - len + 1) {
			ch = str.substring(i, i + len);
			if (ch.equals(src)) {
				buf.append(tgt);
				i = i + len;
			} else {
				buf.append(str.substring(i, i + 1));
				i++;
			}
		}
		if (i < str.length())
			buf.append(str.substring(i, str.length()));
		return buf.toString();
	}

	/**
	 * <pre>
	 * String[] 를 List로 변경한다.
	 * ex)
	 * String[] nameList={&quot;1&quot;,&quot;2&quot;};
	 * List tmp=StringUtils.arrayToList(nameList);
	 * &gt;tmp.size()=2
	 * </pre>
	 * @param parameterValues 문자열 배열
	 * @return List 변경결과
	 */
	public static List<String> arrayToList(String[] parameterValues) {
		List<String> list = new ArrayList<String>();
		int size = parameterValues.length;
		for (int i = 0; i < size; i++) {
			if (parameterValues[i] != null)
				list.add(parameterValues[i]);
		}
		return list;
	}

	/**
	 * <pre>
	 * 문자열이 화면에 보여질 길이보다 길 때 잘라내는 기능을 수행한다.
	 * 문자열(str)이 정해진 바이트(limit) 을 넘길때 잘라낸다.
	 * 
	 * 
	 * &#064;param str 원본 문자열
	 * &#064;param limit 자를 길이
	 * @param hangleByte 한글바이트 수
	 * </pre>
	 * @return String 결과
	 */
	private static String cutString(String str, int limit, int hangleByte) {

		int len = str.length();
		int cnt = 0, index = 0;

		while (index < len && cnt < limit) {
			if (str.charAt(index++) < 256)
				// 1바이트 문자라면...
				cnt++; // 길이 1 증가
			else {
				cnt += hangleByte; // 길이 2 증가
			}
		}

		if (index < len && limit >= cnt)
			str = str.substring(0, index);
		else if (index < len && limit < cnt)
			str = str.substring(0, index - 1);

		return str;
	}

	/**
	 * <pre>
	 * 문자열이 화면에 보여질 길이보다 길 때 잘라내는 기능을 수행한다.  
	 * 한글 2Byte 문자열 포함 잘라낸다.
	 * ex)    String tmp = StringUtil.cutString(&quot;ThisIsTestCase&quot;, 5);
	 * 	     System.out.println(&quot;TMP=&quot; + tmp);
	 *       --------------------------------------------------------
	 *       ==&gt; TMP=ThisI
	 * </pre>
	 * 
	 * @param str 원본 문자열
	 * @param limit 자를 길이
	 * @return String 결과
	 */
	public static String cutString(String str, int limit) {
		return cutString(str, limit, 2);
	}

	/**
	 * <pre>
	 * 문자열이 화면에 보여질 길이보다 길 때 잘라내는 기능을 수행한다.  
	 * 유니코드(UTF-8) 한글 3Byte 포함  잘라낸다. 
	 * ex)    String tmp = StringUtil.cutString(&quot;ThisIsTestCase&quot;, 5);
	 *        System.out.println(&quot;TMP=&quot; + tmp);
	 *       --------------------------------------------------------
	 *       ==&gt; TMP=ThisI
	 * </pre>
	 * 
	 * @param str 원본 문자열
	 * @param limit 자를길이
	 * @return String 결과
	 */
	public static String cutUniCodeString(String str, int limit) {
		return cutString(str, limit, 3);
	}
	/**
	 * <pre>
	 * HTML 문자열을 일반 문자열로 변환한다.
	 * HTML 코드가 출력시 HTML이 오류 나는것을 방지하기 위해 다음과 같은 문자는 변경한다.
	 *		"&"-> "&amp;" 
	 * 	    "<" -> "&lt;" 
	 *		">" -> "&gt;" 
	 *		"\""->"&quot;"
	 *		"#"->"&#35;"
	 *		"("->"&#40;"
	 *		")"->"&#41;"
	 * ex)
	 * String tmp=StringUtils.replaceHtml2String(&quot;&lt;table&gt;&quot;);
	 *  tmp=&quot;&lt;table&gt;&quot;
	 * </pre>
	 * 
	 * @param value 원본 문자열
	 * @return 치환한 결과
	 */
	public static String replaceHtml2String(String value) {
		return replaceMulti(value, toText());
	}
	
	/**
	 * <pre>
	 * HTML 문자열을 일반 문자열로 변환한다.
	 * HTML 코드가 출력시 HTML이 오류 나는것을 방지하기 위해 다음과 같은 문자는 변경한다.
	 *		"&"-> "&amp;" 
	 * 	    "<" -> "&lt;" 
	 *		">" -> "&gt;" 
	 *		"\""->"&quot;"
	 * ex)
	 * String tmp=StringUtils.replaceHtml2String(&quot;&lt;table&gt;&quot;);
	 *  tmp=&quot;&lt;table&gt;&quot;
	 * </pre>
	 * 
	 * @param value 원본 문자열
	 * @return 치환한 결과
	 */
	public static String replaceHtml2StringXSS(String value) {
		return replaceMulti(value, toTextXSS());
	}

	/**
	 * <pre>
	 * 일반 문자열을 HTML 문자열로 변환한다.
	 * HTML 코드가 출력시 HTML이 오류 나는것을 방지하기 변경 한것을 원래 HTML 형식으로 변경한다.
	 * ex)
	 * String tmp=StringUtils.replaceString2Html(&quot;&lt;table&gt;&quot;);
	 * &gt;tmp=&quot;&lt;table&gt;&quot;
	 * </pre>
	 * 
	 * @param value 원본 문자열
	 * @return 치환한 결과
	 */
	public static String replaceString2Html(String value) {
		// WEB에서 호출일경우 변환 하지 않는다.
		if( "2".equals(TCD_POC) ){
			return value;
		}else{
			return replaceMulti(value, toHtml());
		}
	}

	/**
	 * <pre>
	 *  문자열 을 원하는 값으로 String[][] 으로 바꿀문자를
	 *  String[] {&quot;가&quot;,&quot;나&quot;} 형태의 목록을 입력해주면된다.
	 *  ex)
	 *  String[][] toWord=new String[][]{
	 *  	new String[] {&quot;A&quot;, &quot;B&quot;},
	 *  	new String[] {&quot;Y&quot;, &quot;Z&quot;},
	 *  }
	 *  
	 *  String tmp=StringUtils.multiReplace(&quot;ABCDXYZ&quot;,toWord);
	 *  tmp&gt;BBCDXZZ
	 * </pre>
	 * 
	 * @param value 원본 전체 문자열
	 * @param arrReplace 바꿀 단어 String 배열목록
	 * @return 치환한 결과
	 */
	private static String replaceMulti(String value, String[][] arrReplace) {
		if (value == null || "".equals(value))
			return "";

		StringBuffer strBuffer = new StringBuffer(value);
		int start_index = 0, find_index = 0;

		for (int i = 0; i < arrReplace.length; i++) {
			start_index = find_index = 0;

			while ((find_index = strBuffer.indexOf(arrReplace[i][0], start_index)) != -1) {
				strBuffer.replace(find_index, find_index + arrReplace[i][0].length(), arrReplace[i][1]);
				start_index = find_index + arrReplace[i][1].length();
			}
		}

		return strBuffer.toString();
	}

	/**
	 * <pre>
	 * 입력된  전체 문자열에  입력된 구분자중 마지막 구분자만 삭제한다.
	 * ex)
	 * String tmp=StringUtils.removeLastDelimiter(&quot;http://sample.com/&quot;,&quot;/&quot;);
	 * tmp&gt;http://sample.com
	 * </pre>
	 * 
	 * @param srcData 원본문자열
	 * @param delim 구분자
	 * @return String 결과
	 */
	public static String removeLastDelimiter(String srcData, String delim) {
		String rtnData = "";
		if (srcData.length() > 0 && srcData.lastIndexOf(delim) == srcData.length() - delim.length())
			rtnData = srcData.substring(0, srcData.length() - delim.length());
		return rtnData;

	}

	/**
	 * <pre>
	 * 메일로 조언 구함
	 * 특수 문자들을 모두 제거한다.
	 * </pre>
	 * 
	 * @param value 원본 문자열
	 * @return 치환한 결과
	 */
	public static String replaceNonWordChr(String value) {
		if (value == null)
			return value;
		return value.replaceAll("(?im)\\W", "");
	}

	/**
	 * <pre>
	 * 잘못된 XML 문자열을 공백 문자로 변경한다
	 *  RSS 나 JSON 에서 CDATA 의 값에서 특수문자.Control문자 등은 
	 *  XML Parsing 시 에러를 발생하므로 다음 Character 범위가 아닌것은 스페이스 처리한다.
	 * ---W3G에서 XML 스펙으로 다음 CHARACTER 범위만 사용한다고 명시되어 있다.
	 * Char	   ::=   	#x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
	 * ex)
	 * String tmp=&quot;&lt;a&gt;&lt;/a&gt;:
	 * System.out.println(StringUtils.safeXML(tmp));
	 * &gt;&lt;a&gt;        &lt;/a&gt;
	 * </pre>
	 * 
	 * @param str 원본 문자열
	 * @return 치환한 결과
	 * 
	 */
	public static String safeXML(String str) {
		if (str == null)
			return str;
		char replaceChar = ' ';
		StringBuffer sb = new StringBuffer(str.length());

		char ch;
		for (int i = 0; i < str.length(); i++) {
			ch = str.charAt(i);
			if (!(ch == 0x0009 || ch == 0x000A || ch == 0x000D || (ch >= 0x0020 && ch <= 0xD7FF)
					|| (ch >= 0xE000 && ch <= 0xFFFD) || (ch >= 0x10000 && ch <= 0x10FFFF)))
				sb.append(replaceChar);
			else
				sb.append(ch);

		}

		return sb.toString();
	}

	/**
	 * <pre>
	 * 문자열로 입력된 &quot;Y&quot; 나 &quot;N&quot; 을 입력받아 java 기본형인 boolean 으로 반환한다.
	 * String var="Y";
	 * boolean tmp=StringUtils.getString2Boolean(var);
	 * >tmp=true;
	 * </pre>
	 * 
	 * @param tmp "Y" or "N"
	 * @return boolean true or false 반환
	 */
	public static boolean getString2Boolean(String tmp) {
		boolean returnStr = false;
		if (tmp != null && tmp.length() > 0) {
			tmp = tmp.trim();
			if (tmp.equals("Y")) {
				returnStr = true;
			}
		}
		return returnStr;
	}

	/**
	 * <pre>
	 * 전체 길이에서 입력받은 문자열 길이를 제외한 오른쪽에 부족한 길이만큼 입력된 char 로 채운다.
	 * 
	 * ex)  String tmp = StringUtil.RPAD(&quot;TEST&quot;, 10, '#');
	 *       System.out.println(&quot;TMP=&quot; + tmp);
	 *       ---------------------------------------------------
	 *       TMP=TEST######
	 * </pre>
	 * 
	 * @param str String 원본 문자열
	 * @param iLen 전체 길이
	 * @param cPad 나머지 채울 문자
	 * @return String 치환한 결과
	 */
	public static String RPAD(String str, int iLen, char cPad) {
		StringBuffer result = new StringBuffer(str);
		
		int iTempLen = iLen - result.length();
		for (int i = 0; i < iTempLen; i++) {
			result.append(cPad);
		}
		return result.toString();
	}

	/**
	 * <pre>
	 * 전체 길이에서 입력받은 문자열 길이를 제외한 왼쪽에 부족한 길이만큼 입력된 char 로 채운다. 
	 * ex)  String tmp = StringUtil.LPAD(&quot;TEST&quot;, 10, '#');
	 *  	 System.out.println(&quot;TMP=&quot; + tmp);
	 *       ---------------------------------------------------
	 *       TMP=######TEST
	 * </pre>
	 * 
	 * @param str String 원본문자열
	 * @param iLen 자릿 수
	 * @param cPad 채울 문자
	 * @return String 치환한 결과
	 */
	public static String LPAD(String str, int iLen, char cPad) {
		String result = str;
		int iTempLen = iLen - result.getBytes().length;
		for (int i = 0; i < iTempLen; i++)
			result = cPad + result;
		return result;
	}

	/**
	 * <pre>
	 * 입력받은 숫자 스트링을 천단위 마다 구분자 를 넣어서 반환한다.
	 * 소수점은 은 구분자 단위로 포함하지 않는다.
	 * String tmp = StringUtil.appendNumberDelimeter(&quot;123456789&quot;, &quot;,&quot;);
	 * System.out.println(&quot;TMP=&quot; + tmp);
	 *  --------------------------------------------------------
	 * TMP=123,456,789
	 * (예) 12345678 --&gt; 12,345,678
	 * </pre>
	 * 
	 * @param str 원본 정수 문자열
	 * @param del 구분자
	 * @return String 치환한 결과
	 */
	public static String appendNumberDelimeter(String str, String del) {
		DecimalFormat df = new DecimalFormat();
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setGroupingSeparator(del.charAt(0));
		df.setGroupingSize(3);
		df.setDecimalFormatSymbols(dfs);
		return (df.format(Double.parseDouble(str))).toString();
	}

	/**
	 * <pre>
	 * 입력한 값이 &quot;&quot; 공백문자이거나 null 인 경우
	 * 두번째 파라메타 값으로 초기화한다.
	 * ex)nvrStr:&quot;+StringUtils.nvlStr(null,&quot;0&quot;)
	 * > nvrStr: 0
	 * 
	 * </pre>
	 * &#064;param orgStr 원본 문자열
	 * &#064;param initStr 빈값 치환 문자열
	 * @return init string 치환한 결과
	 */
	public static String nvlStr(String orgStr, String initStr) {
		if (orgStr == null || orgStr.equals(""))
			return initStr;
		else
			return orgStr;
	}
	
	/**
	 * <pre>
	 * 입력한 값이 &quot;&quot; 공백문자이거나 null 인 경우
	 * 두번째 파라메타 값으로 초기화한다.
	 * ex)nvrStr:&quot;+StringUtils.nvlStr(null,&quot;0&quot;)
	 * > nvrStr: 0
	 * 
	 * </pre>
	 * &#064;param orgStr 원본 문자열
	 * &#064;param initStr 빈값 치환 문자열
	 * @return init string 치환한 결과
	 */
	public static String nvlStr(Object orgStr, String initStr) {
		String obj = "";
		if( orgStr != null ){
			obj = orgStr.toString();
		}else{
			obj = initStr;
		}
		
		if (obj == null || obj.equals("")){
			obj = initStr;
		}
		
		return obj;
	}
	
	/**
	 * <pre>
	 * 입력한 값이 &quot;&quot; 공백문자이거나 null 인 경우
	 * 두번째 파라메타 값으로 초기화한다.
	 * ex)nvrStr:&quot;+StringUtils.nvlStr(null,&quot;0&quot;)
	 * > nvrStr: 0
	 * 
	 * </pre>
	 * &#064;param orgStr 원본 문자열
	 * &#064;param initStr 빈값 치환 문자열
	 * @return init string 치환한 결과
	 */
	public static String nvlStr(String orgStr) {
		return nvlStr(orgStr, "");
	}
	
	/**
	 * <pre>
	 * 입력한 값이 &quot;&quot; 공백문자이거나 null 인 경우
	 * 두번째 파라메타 값으로 초기화한다.
	 * ex)nvrStr:&quot;+StringUtils.nvlStr(null,&quot;0&quot;)
	 * > nvrStr: 0
	 * 
	 * </pre>
	 * &#064;param orgStr 원본 문자열
	 * &#064;param initStr 빈값 치환 문자열
	 * @return init string 치환한 결과
	 */
	public static String nvlStr(Object orgStr) {
		String obj = "";
		if( orgStr != null ){
			obj = orgStr.toString();
		}
		return nvlStr(obj, "");
	}
	
	/**
	 * <pre>
	 * 입력한 값이 &quot;&quot; 공백문자이거나 null 인 경우
	 * TRUR, 아닌경우는 FALSE를 반환한다.
	 * ex)isNVL:&quot;+StringUtils.isNVL(&quot;한글&quot;)
	 * > isNVL: FALSE
	 * 
	 * </pre>
	 * &#064;param orgStr 원본 문자열
	 * @return boolean NULL or "" 비교 결과
	 */
	public static boolean isNVL(String orgStr) {
		return (orgStr == null || "".equals(orgStr.trim()));
	}
	
	/**
	 * <pre>
	 * 입력한 값이 &quot;&quot; 공백문자이거나 null 인 경우
	 * FALSE, 아닌경우는 TRUE를 반환한다.
	 * ex)isNotNVL:&quot;+StringUtils.isNotNVL(&quot;한글&quot;)
	 * > isNotNVL: TRUE
	 * 
	 * </pre>
	 * &#064;param orgStr 원본 문자열
	 * @return boolean NULL or "" 비교 결과
	 */
	public static boolean isNotNVL(String orgStr) {
		return !isNVL(orgStr);
	}
	
	/**
	 * <pre>
	 * SQL에 영향  있는 문자를 DB에서 안전한 문자열로 변환한다.
	 * select,update,delete,insert 문자 제거 
	 * </pre>
	 * 
	 * @param value 원본 문자열
	 * @return 치환한 결과
	 */
	public static String replaceSaftSql(String value) {
		String result = "";
	    result = nvlStr(replace(value.toLowerCase(), "select", ""),"");
	    result =  nvlStr(replace(value.toLowerCase(), "update", ""),"");
	    result = nvlStr(replace(value.toLowerCase(), "delete", ""),"");
	    result = nvlStr(replace(value.toLowerCase(), "insert", ""),"");		
		return replaceMulti(result, toSafeSql());
	}
	
	/**
	 * String number 값을 int로 반환 <br/>
	 * 숫자형 문자가 아니면 0을 반환
	 * @param obj
	 * @return
	 */
	public static int isInt(Object obj){
		return isInt(obj, 0);
	}
	
	/**
	 * String number 값을 int로 반환 </br>
	 * 숫자형 문자가 아니면 retValue을 반환 
	 * @param obj
	 * @param retValue
	 * @return
	 */
	public static int isInt(Object obj, int retValue){
		int cxf = 0;
		try{
			String number = obj.toString();
			cxf = Integer.parseInt(number);
		}catch(Exception e){
			cxf = retValue;
		}
		
		return cxf;
	}
	
	/**
	 * String number 값을 int로 반환<br/>
	 * 숫자형 문자가 아니거나, 0일경우 retValue을 반환 
	 * @param obj
	 * @param retValue
	 * @return
	 */
	public static int isIntZero(Object obj, int retValue){
		int cxf = 0;
		try{
			String number = obj.toString();
			cxf = Integer.parseInt(number);
			if( cxf <= 0 ){
				cxf = retValue;
			}
		}catch(Exception e){
			cxf = retValue;
		}
		
		return cxf;
	}
}

package com.wt.openapi.common.configuration;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

public class ConfigProperties {

    /**
     * 전역 Properties 오브젝트.
     */
    protected static final Properties properties = new Properties();

    public ConfigProperties() {}

    /**
     * property 값 얻어오기.
     * @param    key : 얻어올 Key 값
     * @return   String - value 값
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * property 값을 얻어오기, 만약 property 값이 아직 설정이 안되었다면 <i>defaultValue</i>값을 반환
     * @param    key : 얻어올 Key 값
     * @param    defaultValue : 디폴트값
     * @return   String - value 값
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }


    /**
     * property 이름 전부 가져오기 (Enumeration 타입)
     * @return   Enumeration - value 값
     */
    @SuppressWarnings({ "rawtypes" })
	public static Enumeration propertyNames() {
        return properties.propertyNames();
    }

    /**
     * Lists the properties to the PrintStream <i>out</i>.
     */
    public static void list(PrintStream out) {
        properties.list(out);
    }

    /**
     * Lists the properties to the PrintWriter <i>out</i>.
     */
    public static void list(PrintWriter out) {
        properties.list(out);
    }
}


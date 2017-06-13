package com.wt.openapi.utils;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.util.ResourceUtils;
import org.springframework.web.util.ServletContextPropertyUtils;
import org.springframework.web.util.WebUtils;

import com.wt.openapi.common.configuration.ConfigProperties;


public class ConfigLoad extends ConfigProperties implements ServletContextListener{
	
	/** Parameter specifying the location of the log4j config file */
	public static final String CONFIG_LOCATION_PARAM = "WTConfigLocation";

	/** Parameter specifying the refresh interval for checking the log4j config file */
	public static final String REFRESH_INTERVAL_PARAM = "WTRefreshInterval";
	
	/** Parameter specifying whether to expose the web app root system property */
	public static final String EXPOSE_WEB_APP_ROOT_PARAM = "WTExposeWebAppRoot";
	
	/**
	 * 처음 톰켓 시작시 실행
	 * @param sce
	 */
	public void contextInitialized(ServletContextEvent sce) {
		init(sce.getServletContext());
	}
	
	/**
	 * 톰켓 종료시 실행
	 * @param sce
	 */
	public void contextDestroyed(ServletContextEvent sce) {}

	public void init(ServletContext servletContext){
		
		if (exposeWebAppRoot(servletContext)) {
			WebUtils.setWebAppRootSystemProperty(servletContext);
		}
		
		String location = servletContext.getInitParameter(CONFIG_LOCATION_PARAM);
		
		String[] filelist = location.split("\n");
		
		for( int i = 0; i < filelist.length ; i++){
			filelist[i] = filelist[i].trim();
			
			try {
				// Resolve property placeholders before potentially resolving a real path.
				filelist[i] = ServletContextPropertyUtils.resolvePlaceholders(filelist[i], servletContext);

				// Leave a URL (e.g. "classpath:" or "file:") as-is.
				if (!ResourceUtils.isUrl(filelist[i])) {
					// Consider a plain file path as relative to the web
					// application root directory.
					filelist[i] = WebUtils.getRealPath(servletContext, filelist[i]);
				}
			}catch (Exception e) {
	            throw new Error("Can't load configuration: ", e);
	        }
			
			FileInputStream props = null;
			try{
				props = getFileInputStream(filelist[i]);
				
				if(props != null){
					properties.load(props);
				}
			}catch(IOException e){
				throw new Error("Can't load configuration: ", e);
			}
		}
	}
	
	
	/**
	 * Return whether to expose the web app root system property,
	 * checking the corresponding ServletContext init parameter.
	 * @see #EXPOSE_WEB_APP_ROOT_PARAM
	 */
	private static boolean exposeWebAppRoot(ServletContext servletContext) {
		String exposeWebAppRootParam = servletContext.getInitParameter(EXPOSE_WEB_APP_ROOT_PARAM);
		return (exposeWebAppRootParam == null || Boolean.valueOf(exposeWebAppRootParam));
	}
	
	public FileInputStream getFileInputStream(String str) throws IOException{
		return new FileInputStream(str);
	}
}

package com.designer.common.framework;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

public class ApplicationConfig {

	private static ApplicationConfig instance = null;
    private static Properties properties = null;
    private static String ApplicationPropertiesFileName = null;
    
    public static void init(String fileName) {
    	
    	if(instance == null)
    		instance = new ApplicationConfig(fileName);
    }
    
    public static ApplicationConfig getInstance() {
    	if(instance == null)
    		instance = new ApplicationConfig(ApplicationPropertiesFileName);
    		
    	return instance;
    }
    
    private ApplicationConfig(String fileName){
    	
    	properties = new Properties();
    	
    	//System.out.println("ApplicationConfig.ApplicationConfig() : " +getClass().getResource("/"+fileName).getPath());
    	
    	try{
    		ApplicationPropertiesFileName = fileName;
    		
    		InputStream in = getClass().getResourceAsStream("/" + fileName);
    		
    		if(in == null){
    			System.out.println("Failed to get input stream");
    		}
    		properties.load(in);
    	}catch(Exception ex){
    		ex.printStackTrace(System.err);
            throw new Error("Error loading fileName="+ fileName +"; reason:"+ ex.getMessage());
    	}
    	
    }
    
    public String getConfigValue(String propertyName){
        return (String)properties.get(propertyName);
    }
    
    public void getAllConfigProperties(){
    	Iterator iter = properties.keySet().iterator();
    	
    	while(iter.hasNext()){
    		String propName = (String)iter.next();
    		String propValue = getConfigValue(propName);
    		System.out.println("\t" + propName + " = " + propValue);
    	}
    }
    
    protected void finalize()
    {
		instance = this;
		System.out.println("ApplicationConfig was called by Garbage collector");
    }
}

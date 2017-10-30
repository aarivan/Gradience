package org.csc540.helper;

import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBReader {
	
	private static Properties prop = null;
	
	static {
		InputStream stream = null;
		try {
			prop = new Properties();
//			stream = new FileInputStream("/Users/shiprasingh/Desktop/Gradience-master/src/main/resources/database/db.properties");
			stream = ClassLoader.class.getResourceAsStream("/database/db.properties");
			
			prop.load(stream);
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	
	public static String getPropertyValue(String key) {
		return prop.getProperty(key);
	}
	
}

package org.csc540.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBReader {
	
	private static Properties prop;
	
	static {
		InputStream stream = null;
		try {
			prop = new Properties();
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

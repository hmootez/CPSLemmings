package Lemmings.preset;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Lemmings.factory.FactoryImpl;
import Lemmings.factory.IFacory;

public class AbstractEntityPreset {
	
	protected static String configFile = "config/entities.json";
	public static IFacory factory = new FactoryImpl(); 
	protected static String configuration = "";
	protected static boolean isConfigured = false;
	
	private static void getConfiguration() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(configFile));
			StringBuilder configBuilder = new StringBuilder();
			
			String line = null;
			String lineSep = System.getProperty("line.separator");
			while ((line = reader.readLine()) != null) {
				configBuilder.append(line);
				configBuilder.append(lineSep);
		    }
			configuration = configBuilder.toString();
			isConfigured = true;
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected static void configure() {
		if (!isConfigured) {
			getConfiguration();
		}
	}
}

package CallLogGenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class LoadData {

	public static HashMap<String, String> loadData() {
		HashMap<String, String> configMap = new HashMap<String, String>();
		System.out.println("Loading file config.properties ...");
		Properties mainProperties = new Properties();
		FileInputStream file;
		String path = "./config.properties";

		try {
			file = new FileInputStream(path);
			mainProperties.load(file);
			file.close();

		} catch (FileNotFoundException e) {
			System.out
					.println("Error. File Not Found!!!. Please place the config.properties file in the same directory as the jar file.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return setData(mainProperties, configMap);
	}

	public static HashMap<String, String> setData(Properties mainProperties,
			HashMap<String, String> configMap) {
		configMap.put("app.key", mainProperties.getProperty("app.key"));
		configMap.put("app.secret", mainProperties.getProperty("app.secret"));
		configMap.put("app.username",
				mainProperties.getProperty("app.username"));
		configMap.put("app.extension",
				mainProperties.getProperty("app.extension"));
		configMap.put("app.password",
				mainProperties.getProperty("app.password"));
		configMap.put("app.fromNumber",
				mainProperties.getProperty("app.fromNumber"));
		configMap.put("app.toNumber",
				mainProperties.getProperty("app.toNumber"));
		configMap.put("app.counter", mainProperties.getProperty("app.counter"));
		System.out.println("Done Loading Data!!!/n");
		return configMap;
	}
}

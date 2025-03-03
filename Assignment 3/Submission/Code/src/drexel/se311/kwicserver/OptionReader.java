package drexel.se311.kwicserver;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Properties;

public class OptionReader {
    private static HashMap<String, String> userOptions = new HashMap<>();
    private static KWICObjectLoader kwicObjLoader = new KWICObjectLoader();

    private OptionReader() {
        // Prevent instantiation
    }

    public static void readOptions(String propertiesPath) {
        Properties props = new Properties();
        String configPath = System.getProperty("config.file", propertiesPath); // Default to "config.properties" in the JAR directory

        try (FileInputStream input = new FileInputStream(configPath);
             InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) { // Explicit UTF-8
            props.load(reader);
            userOptions.clear();
            for (String key : props.stringPropertyNames()) {
                userOptions.put(key, props.getProperty(key));
            }
            System.out.println("Configuration loaded successfully from: " + configPath);
        } catch (IOException e) {
            System.err.println("Error loading config.properties: " + e.getMessage());
        }
    }

    public static Object getObjectFromKey(String keyStr) { 
        if (userOptions.containsKey(keyStr)) {
            String className = userOptions.get(keyStr);
            try {
                return kwicObjLoader.loadObject(className);
            } catch (Exception e) {
                System.err.println("Error loading object for key '" + keyStr + "': " + e.getMessage());
            }
        }
        return null;
    }

    public static Object getObjectFromStr(String objStr) {
        try {
            return kwicObjLoader.loadObject(objStr);
        } catch (Exception e) {
            System.err.println("Error loading object for class '" + objStr + "': " + e.getMessage());
        }
        return null;
    }

    public static String getString(String keyStr) {
        return userOptions.getOrDefault(keyStr, "");
    }
}

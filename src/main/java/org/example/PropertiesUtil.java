package org.example;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil() {
    }

    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
      try(var inputStream =  PropertiesUtil.class
              .getClassLoader()
              .getResourceAsStream("application.properties")) {
          PROPERTIES.load(inputStream);
      } catch (IOException ex) {
          ex.printStackTrace();
          throw new RuntimeException(ex);
      }
    }
}

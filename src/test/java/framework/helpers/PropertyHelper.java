package framework.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyHelper extends PropertyLoader{
    private static Properties properties;

    public static Properties loadProperties(String pathToPropertiesFile) {
        properties = PropertyLoader.initProperty(pathToPropertiesFile);
        return properties;
    }

    public static String getUrl() {
        return properties.getProperty("url");
    }

    public static int getDefaultImplicitlyWait() {
        return Integer.parseInt(properties.getProperty("defaultPageLoadTimeout"));
    }

    public static int getDefaultExplicitlyWait() {
        return Integer.parseInt(properties.getProperty("defaultElementLoadTimeOut"));
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }
    public static String getDownloadPath() {
        return properties.getProperty("pathDownloadPackage");
    }
}

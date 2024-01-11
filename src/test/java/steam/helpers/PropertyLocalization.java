package steam.helpers;

import framework.helpers.PropertyLoader;

import java.util.Properties;

public class PropertyLocalization extends PropertyLoader {
    private static Properties properties;

    public static Properties loadProperties(String pathToPropertiesFile) {
        properties = PropertyLoader.initProperty(pathToPropertiesFile);
        return properties;
    }

    public static String getBtnCategories() {
        return properties.getProperty("loc.categories.button");
    }

    public static String getBtnAction() {
        return properties.getProperty("loc.action.button");
    }

    public static String getBtnDiscount() {
        return properties.getProperty("loc.discount.button");
    }
}

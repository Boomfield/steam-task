package framework.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class PropertyLoader {
    public static Properties initProperty(String pathToPropertiesFile) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(pathToPropertiesFile)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}

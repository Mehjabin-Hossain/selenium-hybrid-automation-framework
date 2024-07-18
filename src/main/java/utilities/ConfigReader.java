package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
    private static final Properties configProperties = loadProperties("config.properties");
    private static final Properties testDataProperties = loadProperties("testdata.properties");

    private ConfigReader() {
    }

    public static String getProperty(String key) {
        return configProperties.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return configProperties.getProperty(key, defaultValue);
    }

    public static String getTestData(String key) {
        return testDataProperties.getProperty(key);
    }

    private static Properties loadProperties(String fileName) {
        Properties properties = new Properties();

        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IllegalStateException("Unable to find " + fileName + " in test resources.");
            }
            properties.load(inputStream);
        } catch (IOException exception) {
            throw new RuntimeException("Failed to load " + fileName, exception);
        }

        return properties;
    }
}

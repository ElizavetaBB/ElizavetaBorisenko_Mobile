package properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public static Properties getPropertyReader(String path) {
        Properties properties = new Properties();
        try (FileReader fileReader = new FileReader(path)) {
            properties.load(fileReader);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return properties;
    }
}

package properties;

import java.util.Properties;

public class WebProperties {

    private static final Properties properties = PropertyReader.getPropertyReader(
            "./src/test/resources/properties/web.properties");
    public static final String URL = properties.getProperty("url");
    public static final String QUERY = properties.getProperty("query");
    public static final String KEYWORD = properties.getProperty("keyword");
    public static final int LINKS_NUMBER = Integer.parseInt(properties.getProperty("links.number"));
}

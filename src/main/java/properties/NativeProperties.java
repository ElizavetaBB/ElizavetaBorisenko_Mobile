package properties;

import java.util.Properties;

public class NativeProperties {

    private static final Properties properties = PropertyReader.getPropertyReader(
            "./src/test/resources/properties/native.properties");
    public static final String EMAIL = properties.getProperty("email");
    public static final String PASSWORD = properties.getProperty("password");
    public static final String BUDGET_PAGE_TITLE = properties.getProperty("budget.page.title");
}

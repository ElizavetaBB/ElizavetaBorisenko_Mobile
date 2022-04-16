package properties;

import java.util.Properties;

public class AuthorizationProperties {

    private static final Properties properties = PropertyReader.getPropertyReader(
            "./src/test/resources/properties/authorization.properties");
    public static final String TOKEN_EPAM = properties.getProperty("token.epam");
    public static final String PROJECT_NAME_EPAM = properties.getProperty("project.name.epam");
    public static final String USERNAME_SAUCE_LABS = properties.getProperty("username.sauceLabs");
    public static final String TOKEN_SAUCE_LABS = properties.getProperty("token.sauceLabs");
}

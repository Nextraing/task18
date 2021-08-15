package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static utils.Log.LOG;

public class PropertiesLoader {

    private final Properties properties = new Properties();

    private static final String LOGIN_PAGE_PROPERTY = "site.address";
    private static final String MAIN_PAGE_PROPERTY = "site.mainpage";
    private static final String SHOPPING_CART_PAGE_PROPERTY = "site.shoppingcart";
    private static final String CHECKOUT_STEP_ONE_PAGE_PROPERTY = "site.checkoutstepone";
    private static final String CHECKOUT_STEP_TWO_PAGE_PROPERTY = "site.checkoutsteptwo";
    private static final String CHECKOUT_COMPLETE_PAGE_PROPERTY = "site.checkoutcomplete";

    public String getLoginPageProperty() {

        try {

            properties.load(new FileInputStream("./src/main/resources/site.properties"));

        } catch (IOException ioException) {

            LOG.error("Problem with file: ", ioException);

        }

        return properties.getProperty(LOGIN_PAGE_PROPERTY);
    }

    public String getMainPageProperty() {

        return getLoginPageProperty() + properties.getProperty(MAIN_PAGE_PROPERTY);
    }

    public String getShoppingCartPageProperty() {

        return getLoginPageProperty() + properties.getProperty(SHOPPING_CART_PAGE_PROPERTY);
    }

    public String getCheckoutStepOnePageProperty() {

        return getLoginPageProperty() + properties.getProperty(CHECKOUT_STEP_ONE_PAGE_PROPERTY);
    }

    public String getCheckoutStepTwoPageProperty() {

        return getLoginPageProperty() + properties.getProperty(CHECKOUT_STEP_TWO_PAGE_PROPERTY);
    }

    public String getCheckoutCompletePageProperty() {

        return getLoginPageProperty() + properties.getProperty(CHECKOUT_COMPLETE_PAGE_PROPERTY);
    }

    public String getUserProperty(String propertyName) {

        try {

            properties.load(new FileInputStream("./src/main/resources/user.properties"));

        } catch (IOException ioException) {

            LOG.error("Problem with file: ", ioException);
        }

        return properties.getProperty(propertyName);
    }
}

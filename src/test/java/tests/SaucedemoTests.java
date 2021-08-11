package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.saucedemo.*;
import org.junit.jupiter.api.*;
import user.User;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;
import static utils.Log.LOG;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SaucedemoTests {

    private static final String CLASS_SIMPLE_NAME = SaucedemoTests.class.getSimpleName();

    private final User user = new User();

    private final LoginPage loginPage = new LoginPage();
    private final MainPage mainPage = new MainPage();
    private final ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    private final CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage();
    private final CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage();
    private final CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();

    @BeforeAll
    void setUp() {

        LOG.info("Start of {}.", CLASS_SIMPLE_NAME);

        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        open();
    }

    @Test
    @Order(1)
    @DisplayName("LogIn Test")
    void logInTest() {

        loginPage.openLoginPage();
        LOG.info("{} login to the site.", user);
        loginPage.login(user);

        assertTrue(loginPage.getLogoutLink().is(Condition.enabled));
    }

    @Test
    @Order(2)
    @DisplayName("Buy any product Test")
    void buyAnyProductTest() {

        mainPage.openMainPage();
        mainPage.addAnyProductToCart();
        mainPage.openShoppingCart();
        shoppingCartPage.checkout();
        checkoutStepOnePage.fillInTheForm(user);
        checkoutStepTwoPage.finish();

        String expectedStatus = "Checkout: Complete!".toLowerCase();
        String actualStatus = checkoutCompletePage.getStatus().toLowerCase();

        assertEquals(expectedStatus, actualStatus, "Purchase error.");
    }

    @Test
    @Order(2)
    @DisplayName("Purchase cancellation Test")
    void purchaseCancellationTest() {

        mainPage.openMainPage();
        mainPage.addSomeProductsToCart();
        mainPage.openShoppingCart();
        shoppingCartPage.checkout();
        checkoutStepOnePage.fillInTheForm(user);
        checkoutStepTwoPage.cancel();

        String expectedURL = mainPage.getUrl();
        String actualURL = WebDriverRunner.url();

        assertEquals(expectedURL, actualURL, "Purchase cancellation error.");
    }

    @Test
    @Order(2)
    @DisplayName("Delete all products from shopping cart Test")
    void deleteAllProductsFromShoppingCartTest() {

        mainPage.openMainPage();
        mainPage.addSomeProductsToCart();
        mainPage.openShoppingCart();
        shoppingCartPage.deleteAllProducts();

        int expectedNumberOfProducts = 0;
        int actualNumberOfProductsAfter = shoppingCartPage.getNumberOfProducts();

        assertEquals(expectedNumberOfProducts, actualNumberOfProductsAfter, "Product deletion error.");
    }

    @Test
    @Order(2)
    @DisplayName("Sum up products cost Test")
    void sumUpProductsCostTest() {

        mainPage.openMainPage();
        mainPage.addSomeProductsToCart();
        mainPage.openShoppingCart();
        shoppingCartPage.checkout();
        checkoutStepOnePage.fillInTheForm(user);

        double expectedTotalCost = checkoutStepTwoPage.sumTotalCost();
        double actualTotalCost = checkoutStepTwoPage.getTotalCost();

        assertEquals(expectedTotalCost, actualTotalCost, "Summing up products error.");
    }

    @AfterEach
    void afterEach() {

        shoppingCartPage.openShoppingCartPage();

        if (shoppingCartPage.getNumberOfProducts() > 0) {

            LOG.info("Shopping cart is not empty. Delete all the products.");
            shoppingCartPage.deleteAllProducts();
        }

    }

    @AfterAll
    void tearDown() {

        LOG.info("End of {}.", CLASS_SIMPLE_NAME);
    }

}

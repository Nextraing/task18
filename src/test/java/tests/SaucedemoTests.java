package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.saucedemo.*;
import org.junit.jupiter.api.*;
import user.User;
import utils.RandomChoice;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;
import static utils.Log.LOG;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SaucedemoTests {

    private static final String CLASS_SIMPLE_NAME = SaucedemoTests.class.getSimpleName();

    User user = new User();

    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    CheckoutStepOnePage checkoutStepOnePage = new CheckoutStepOnePage();
    CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage();
    CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();

    RandomChoice randomNumber = new RandomChoice();

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

        LOG.info("LogIn Test.");

        loginPage.openLoginPage();
        loginPage.login(user);

        assertTrue(loginPage.getLogoutLink().is(Condition.enabled));
    }

    @Test
    @Order(2)
    @DisplayName("Buy any product Test")
    void buyAnyProductTest() {

        LOG.info("Buy any product Test.");

        mainPage.openMainPage();
        mainPage.addProductToCart(randomNumber.getNumber(0, mainPage.getNumberOfProductsOnPage() - 1));
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

        LOG.info("Purchase cancellation Test.");

        mainPage.openMainPage();
        int numberOfIterations = randomNumber.getNumber(0, mainPage.getNumberOfProductsOnPage() - 1);
        for (int i = 0; i <= numberOfIterations; i++) {

            mainPage.addProductToCart(randomNumber.getNumber(0, mainPage.getNumberOfProductsOnPage() - 1));

        }
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

        LOG.info("Delete all products from shopping cart Test.");

        mainPage.openMainPage();
        int numberOfIterations = randomNumber.getNumber(0, mainPage.getNumberOfProductsOnPage() - 1);
        for (int i = 0; i <= numberOfIterations; i++) {

            mainPage.addProductToCart(randomNumber.getNumber(0, mainPage.getNumberOfProductsOnPage() - 1));

        }
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

        LOG.info("Sum up products cost Test.");

        mainPage.openMainPage();

        int numberOfIterations = randomNumber.getNumber(0, mainPage.getNumberOfProductsOnPage() - 1);
        for (int i = 0; i <= numberOfIterations; i++) {

            mainPage.addProductToCart(randomNumber.getNumber(0, mainPage.getNumberOfProductsOnPage() - 1));

        }
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

            shoppingCartPage.deleteAllProducts();
        }

    }

    @AfterAll
    void tearDown() {

        closeWebDriver();

        LOG.info("End of {}.", CLASS_SIMPLE_NAME);
    }

}

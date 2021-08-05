package com.saucedemo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.*;
import static utils.Log.LOG;

public class MainPage {

    SelenideElement logoutLink = $("#logout_sidebar_link");
    SelenideElement shoppingCartLink = $(".shopping_cart_link");

    ElementsCollection addToCartButtonCollection = $$("button[id^='add-to-cart']");

    public String getUrl() {

        Properties properties = new Properties();

        try {

            properties.load(new FileInputStream("./src/main/resources/site.properties"));

        } catch (IOException ioException) {

            LOG.error("Problem with file: ", ioException);

        }

        return properties.getProperty("site.address") + properties.getProperty("site.mainpage");
    }

    public void openMainPage() {

        open(getUrl());
    }

    public void addProductToCart(int product) {

        LOG.info("Click to {}", addToCartButtonCollection.get(product).getAttribute("name"));

        addToCartButtonCollection.get(product).click();
    }

    public int getNumberOfProductsOnPage(){

        return addToCartButtonCollection.size();
    }

    public void openShoppingCart() {

        shoppingCartLink.click();
    }
}

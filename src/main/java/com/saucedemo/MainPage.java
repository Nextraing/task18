package com.saucedemo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import utils.PropertiesLoader;
import utils.RandomChoice;

import static com.codeborne.selenide.Selenide.*;
import static utils.Log.LOG;

public class MainPage {

    SelenideElement logoutLink = $("#logout_sidebar_link");
    SelenideElement shoppingCartLink = $(".shopping_cart_link");

    ElementsCollection addToCartButtonCollection = $$("button[id^='add-to-cart']");

    private final PropertiesLoader propertiesLoader = new PropertiesLoader();
    private final RandomChoice randomNumber = new RandomChoice();

    public String getUrl() {

        return propertiesLoader.getMainPageProperty();
    }

    public void openMainPage() {

        open(getUrl());
    }

    public void addProductToCart(int product) {

        LOG.info("Click to {}", addToCartButtonCollection.get(product).getAttribute("name"));
        addToCartButtonCollection.get(product).click();
    }

    public void addAnyProductToCart() {

        addProductToCart(randomNumber.getNumber(0, getNumberOfProductsOnPage() - 1));

    }

    public void addSomeProductsToCart() {

        int numberOfIterations = randomNumber.getNumber(0, getNumberOfProductsOnPage() - 1);

        for (int i = 0; i <= numberOfIterations; i++) {

            addAnyProductToCart();
        }
    }

    public int getNumberOfProductsOnPage() {

        return addToCartButtonCollection.size();
    }

    public void openShoppingCart() {

        shoppingCartLink.click();
    }
}

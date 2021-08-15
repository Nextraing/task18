package com.saucedemo;

import com.codeborne.selenide.SelenideElement;
import utils.PropertiesLoader;

import static com.codeborne.selenide.Selenide.*;

public class ShoppingCartPage {

    private final SelenideElement checkoutButton = $("#checkout");

    private final CartListElement cartList = new CartListElement();
    private final PropertiesLoader propertiesLoader = new PropertiesLoader();

    public String getUrl() {

        return propertiesLoader.getShoppingCartPageProperty();
    }

    public void openShoppingCartPage() {

        open(getUrl());
    }

    public double sumTotalCost() {

        return cartList.sumTotalCost();
    }

    public void checkout() {

        checkoutButton.click();
    }

    public int getNumberOfProducts() {

        return cartList.getNumberOfProducts();
    }

    public void deleteAllProducts() {

        cartList.allRemoveButtons.forEach(SelenideElement::click);

    }
}

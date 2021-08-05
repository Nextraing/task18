package com.saucedemo;

import com.codeborne.selenide.SelenideElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.*;
import static utils.Log.LOG;

public class ShoppingCartPage {

    SelenideElement checkoutButton = $("#checkout");

    CartListElement cartList = new CartListElement();

    public String getUrl() {

        Properties properties = new Properties();

        try {

            properties.load(new FileInputStream("./src/main/resources/site.properties"));

        } catch (IOException ioException) {

            LOG.error("Problem with file: ", ioException);

        }

        return properties.getProperty("site.address") + properties.getProperty("site.shoppingcart");
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

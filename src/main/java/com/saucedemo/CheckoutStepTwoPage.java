package com.saucedemo;

import com.codeborne.selenide.SelenideElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.*;
import static utils.Log.LOG;

public class CheckoutStepTwoPage {

    SelenideElement finishButton = $("#finish");
    SelenideElement cancelButton = $("#cancel");

    CartListElement cartList = new CartListElement();

    public String getUrl() {

        Properties properties = new Properties();

        try {

            properties.load(new FileInputStream("./src/main/resources/site.properties"));

        } catch (IOException ioException) {

            LOG.error("Problem with file: ", ioException);

        }

        return properties.getProperty("site.address") + properties.getProperty("site.checkoutsteptwo");
    }

    public void openCheckoutStepTwoPage() {

        open(getUrl());
    }

    public double getTotalCost() {

        return cartList.getTotalCost();
    }

    public double sumTotalCost() {

        return cartList.sumTotalCost();
    }

    public void finish() {

        finishButton.click();
    }

    public void cancel() {

        cancelButton.click();
    }
}

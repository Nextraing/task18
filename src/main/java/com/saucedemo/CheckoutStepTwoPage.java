package com.saucedemo;

import com.codeborne.selenide.SelenideElement;
import utils.PropertiesLoader;

import static com.codeborne.selenide.Selenide.*;

public class CheckoutStepTwoPage {

    private final SelenideElement finishButton = $("#finish");
    private final SelenideElement cancelButton = $("#cancel");

    private final CartListElement cartList = new CartListElement();
    private final PropertiesLoader propertiesLoader = new PropertiesLoader();

    public String getUrl() {

        return propertiesLoader.getCheckoutStepTwoPageProperty();
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

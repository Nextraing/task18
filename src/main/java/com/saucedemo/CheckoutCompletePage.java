package com.saucedemo;

import com.codeborne.selenide.SelenideElement;
import utils.PropertiesLoader;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckoutCompletePage {

    private final SelenideElement statusTitle = $(".title");

    private final PropertiesLoader propertiesLoader = new PropertiesLoader();

    public String getUrl() {

        return propertiesLoader.getCheckoutCompletePageProperty();
    }

    public void openCheckoutCompletePage() {

        open(getUrl());
    }

    public String getStatus() {

        return statusTitle.getText();
    }
}

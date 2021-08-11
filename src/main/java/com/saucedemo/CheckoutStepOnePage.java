package com.saucedemo;

import com.codeborne.selenide.SelenideElement;
import user.User;
import utils.PropertiesLoader;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckoutStepOnePage {

    private final SelenideElement firstNameField = $("#first-name");
    private final SelenideElement lastNameField = $("#last-name");
    private final SelenideElement postalCodeField = $("#postal-code");
    private final SelenideElement continueButton = $("#continue");

    private final PropertiesLoader propertiesLoader = new PropertiesLoader();

    public String getUrl() {

        return propertiesLoader.getCheckoutStepOnePageProperty();
    }

    public void openCheckoutStepOnePage() {

        open(getUrl());
    }

    public void fillInTheForm(User user) {

        firstNameField.sendKeys(user.getFirstName());
        lastNameField.sendKeys(user.getLastName());
        postalCodeField.sendKeys(user.getPostalCode());

        continueButton.click();
    }

}

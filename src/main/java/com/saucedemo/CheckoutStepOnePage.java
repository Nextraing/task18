package com.saucedemo;

import com.codeborne.selenide.SelenideElement;
import user.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static utils.Log.LOG;

public class CheckoutStepOnePage {

    SelenideElement firstNameField = $("#first-name");
    SelenideElement lastNameField = $("#last-name");
    SelenideElement postalCodeField = $("#postal-code");
    SelenideElement continueButton = $("#continue");

    public String getUrl() {

        Properties properties = new Properties();

        try {

            properties.load(new FileInputStream("./src/main/resources/site.properties"));

        } catch (IOException ioException) {

            LOG.error("Problem with file: ", ioException);

        }

        return properties.getProperty("site.address") + properties.getProperty("site.checkoutstepone");
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

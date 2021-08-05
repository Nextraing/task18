package com.saucedemo;

import com.codeborne.selenide.SelenideElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static utils.Log.LOG;

public class CheckoutCompletePage {

    SelenideElement statusTitle = $(".title");

    public String getUrl() {

        Properties properties = new Properties();

        try {

            properties.load(new FileInputStream("./src/main/resources/site.properties"));

        } catch (IOException ioException) {

            LOG.error("Problem with file: ", ioException);

        }

        return properties.getProperty("site.address") + properties.getProperty("site.checkoutcomplete");
    }

    public void openCheckoutCompletePage() {

        open(getUrl());
    }

    public String getStatus() {

        return statusTitle.getText();
    }
}

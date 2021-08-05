package com.saucedemo;

import com.codeborne.selenide.SelenideElement;
import user.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static utils.Log.LOG;

public class LoginPage extends MainPage {

    SelenideElement loginField = $("#user-name");
    SelenideElement passwordField = $("#password");
    SelenideElement loginButton = $("#login-button");

    public String getUrl() {

        Properties properties = new Properties();

        try {

            properties.load(new FileInputStream("./src/main/resources/site.properties"));

        } catch (IOException ioException) {

            LOG.error("Problem with file: ", ioException);

        }

        return properties.getProperty("site.address");
    }

    public void login(User user) {

        loginField.sendKeys(user.getLoginName());
        passwordField.sendKeys(user.getPassword());
        loginButton.click();
    }

    public void openLoginPage() {

        open(getUrl());
    }

    public SelenideElement getLogoutLink() {

        return super.logoutLink;
    }
}

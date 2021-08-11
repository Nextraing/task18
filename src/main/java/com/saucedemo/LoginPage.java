package com.saucedemo;

import com.codeborne.selenide.SelenideElement;
import user.User;
import utils.PropertiesLoader;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends MainPage {

    private final SelenideElement loginField = $("#user-name");
    private final SelenideElement passwordField = $("#password");
    private final SelenideElement loginButton = $("#login-button");

    private final PropertiesLoader propertiesLoader = new PropertiesLoader();

    public String getUrl() {

        return propertiesLoader.getLoginPageProperty();
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

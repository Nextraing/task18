package com.saucedemo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartListElement {

    SelenideElement totalPrices = $(By.xpath(".//div[@class='summary_subtotal_label']"));

    ElementsCollection products = $$(".cart_item");
    ElementsCollection prices = $$(By.xpath(".//div[@class='inventory_item_price']"));
    ElementsCollection allRemoveButtons = $$(By.xpath(".//button[text() = 'Remove']"));

    public int getNumberOfProducts() {

        return products.size();
    }

    public double getTotalCost() {

        return Double.parseDouble(totalPrices.getText().substring(totalPrices.getText().indexOf("$") + 1));
    }

    public double sumTotalCost() {

        return prices.stream()
                .map((p) -> Double.parseDouble(p.getText().substring(1)))
                .reduce(0.0, Double::sum);
    }
}

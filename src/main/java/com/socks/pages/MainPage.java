package com.socks.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

public class MainPage {

    public SelenideElement seeAllToolsButton = $("a.wt-button_mode_primary");
    public SelenideElement toolsMenu = $x("//div[contains(@class, 'menu-main__item') and text() = 'Tools']");
    public SelenideElement searchButton = $("[data-test=menu-main-icon-search]");
}

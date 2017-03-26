package ru.pozdravlala.selenium;

import org.openqa.selenium.By;

/**
 * Created by Alex on 12.03.2017.
 */
public class HomePage {

    public static final By REASON_SELECTOR = By.id("reason_selector");
    public static final By GENERATE_BUTTON = By.id("generate_button");
    public static final By CONGRATULATION_FIELD = By.id("congratulation");
    public static final By SPINNER = By.id("loader");
    public static final By MALE_CHECKBOX = By.id("male");
    public static final By FEMALE_CHECKBOX = By.id("female");
    public static final By POLITE_CHECKBOX = By.xpath("//label[input[@id='polite']]");
    public static final By LONG_CHECKBOX = By.xpath("//label[input[@id='long']]");

}
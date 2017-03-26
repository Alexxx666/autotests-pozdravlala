package ru.pozdravlala.selenium;

import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alex on 12.03.2017.
 */

public class PozdravlalaTests {

    @Test
    public void testCongratulation() {

        WebDriver driver = Utils.setDriverAndVisitURL("http://pozdravlala.ru");

        Select reason_selector = new Select (driver.findElement(HomePage.REASON_SELECTOR));
        WebElement congratulate_button = driver.findElement(HomePage.GENERATE_BUTTON);
        WebElement congratulation = driver.findElement(HomePage.CONGRATULATION_FIELD);

        int numberOfReasons = 5;
        String congratReason = "";
        String expectedResult = "";

        Random random = new Random();
        int randomReason = random.nextInt(numberOfReasons);

        switch (randomReason) {
            case 0:
                congratReason = "birthday";
                expectedResult = "(?s).*\\bднём\\b\\s\\bрождения\\b.*";
                break;

            case 1:
                congratReason = "23february";
                expectedResult = "(?s).*(\\b23-м\\b\\s\\bфевраля\\b|\\bЗащитников\\b\\sОтечества\\b).*";
                break;

            case 2:
                congratReason = "women_day";
                expectedResult = "(?s).*(\\bмарта\\b|\\bженским\\b\\sднём\\b).*";
                break;

            case 3:
                congratReason = "new_year";
                expectedResult = "(?s).*\\bНовым\\b\\s\\bГодом\\b.*";
                break;

            case 4:
                congratReason = "christmas";
                expectedResult = "(?s).*\\bРождеством\\b.*";
                break;
        }

        reason_selector.selectByValue(congratReason);

        for(int i = 0; i < 5; i++) {
            congratulate_button.click();
            Utils.waitForSomeTime(1000);
            System.out.println(congratulation.getText());
            assertTrue(congratulation.getText().matches(expectedResult));
        }

        Utils.waitForSomeTime(1000);
        driver.quit();
    }

    @Test
    public void checkGenderFor23FebAnd8March() {

        WebDriver driver = Utils.setDriverAndVisitURL("http://pozdravlala.ru");

        Select reason_selector = new Select (driver.findElement(HomePage.REASON_SELECTOR));
        WebElement congratulate_button = driver.findElement(HomePage.GENERATE_BUTTON);
        WebElement male_checkbox = driver.findElement(HomePage.MALE_CHECKBOX);
        WebElement female_checkbox = driver.findElement(HomePage.FEMALE_CHECKBOX);

        String congratReason = "";
        congratReason = "23february";

        reason_selector.selectByValue(congratReason);

        congratulate_button.click();
        Utils.waitForSomeTime(1000);

        assertTrue(male_checkbox.isSelected());
        assertFalse(female_checkbox.isEnabled());

        Utils.waitForSomeTime(1000);

        congratReason = "women_day";

        reason_selector.selectByValue(congratReason);

        congratulate_button.click();
        Utils.waitForSomeTime(1000);

        assertTrue(female_checkbox.isSelected());
        assertFalse(male_checkbox.isEnabled());

        Utils.waitForSomeTime(1000);
        driver.quit();
    }

    @Test
    public void checkPoliteness() {

        WebDriver driver = Utils.setDriverAndVisitURL("http://pozdravlala.ru");

        Select reason_selector = new Select (driver.findElement(HomePage.REASON_SELECTOR));
        WebElement congratulate_button = driver.findElement(HomePage.GENERATE_BUTTON);
        WebElement polite_checkbox = driver.findElement(HomePage.POLITE_CHECKBOX);
        WebElement long_checkbox = driver.findElement(HomePage.LONG_CHECKBOX);
        WebElement congratulation = driver.findElement(HomePage.CONGRATULATION_FIELD);

        reason_selector.selectByValue("birthday");
        Utils.waitForSomeTime(500);

        polite_checkbox.click();
        Utils.waitForSomeTime(500);

        long_checkbox.click();
        Utils.waitForSomeTime(500);

        String cong_text;

        for(int i = 0; i < 5; i++) {
            congratulate_button.click();
            Utils.waitForSomeTime(1000);
            cong_text = congratulation.getText();
            assertTrue(
                cong_text.contains("Вам") ||
                        cong_text.contains("Вас") ||
                        cong_text.contains("Ваш")
            );
        }

        Utils.waitForSomeTime(1000);
        driver.quit();
    }

    @Test
    public void getCongratulationAndSendIt() {

        WebDriver driver = Utils.setDriverAndVisitURL("http://pozdravlala.ru");
        driver.manage().window().maximize();

        Select reason_selector = new Select (driver.findElement(HomePage.REASON_SELECTOR));
        WebElement congratulate_button = driver.findElement(HomePage.GENERATE_BUTTON);
        WebElement congratulation = driver.findElement(HomePage.CONGRATULATION_FIELD);

        reason_selector.selectByValue("birthday");
        congratulate_button.click();
        Utils.waitForSomeTime(1500);
        String cong_text = congratulation.getText();
        Utils.waitForSomeTime(1000);

        driver.get("https://mail.google.com");
        driver.findElement(By.id("Email")).sendKeys("InsertYourEmail");
        driver.findElement(By.id("next")).click();
        Utils.waitForSomeTime(500);
        driver.findElement(By.id("Passwd")).sendKeys("insertYouPass");
        driver.findElement(By.id("signIn")).click();

        Utils.waitForSomeTime(8000);

        driver.findElement(By.xpath("//*[contains(text(), 'COMPOSE')]")).click();
        Utils.waitForSomeTime(2000);

        driver.findElement(By.xpath("//textarea[contains(@class, 'vO')]")).sendKeys("testtesttest666@mailinator.com");

        driver.findElement(By.xpath("//div[contains(@aria-label, 'Message Body')]")).sendKeys(cong_text);
        Utils.waitForSomeTime(2000);

        driver.findElement(By.xpath("//div[contains(text(), 'Send')]")).click();
        Utils.waitForSomeTime(2000);

        driver.quit();
    }

}


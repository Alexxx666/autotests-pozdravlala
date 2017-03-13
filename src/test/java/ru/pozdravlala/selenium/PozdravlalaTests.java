package ru.pozdravlala.selenium;

import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Created by Alex on 12.03.2017.
 */

public class PozdravlalaTests {

    @Test
    public void testCongratulation() {

        final File file = new File("driver/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        WebDriver driver = new ChromeDriver();

        driver.get("http://pozdravlala.ru/");
        waitForSomeTime(1000);

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
            waitForSomeTime(1000);
            System.out.println(congratulation.getText());
            assertTrue(congratulation.getText().matches(expectedResult));
        }

        waitForSomeTime(1000);
        driver.quit();
    }

    public void waitForSomeTime(int secs) {
        try{
            Thread.sleep(secs);
        }catch(InterruptedException e){
            System.out.println("Thread.sleep got interrupted!");
        }
    }

}


package ru.pozdravlala.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

/**
 * Created by Alex on 25.03.2017.
 */
public class Utils {

    public static WebDriver setDriverAndVisitURL(String URL) {
        final File file = new File("driver/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        WebDriver driver = new ChromeDriver();

        driver.get(URL);
        waitForSomeTime(1000);
        return driver;
    }

    public static void waitForSomeTime(int secs) {
        try{
            Thread.sleep(secs);
        }catch(InterruptedException e){
            System.out.println("Thread.sleep got interrupted!");
        }
    }

}

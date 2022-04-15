package cz.cvut.fel.ts1.lab09;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class SeleniumTests {

    @Test
    public void testGoogleSearch() throws InterruptedException {
        String pathToDriver = "C:\\Users\\podav\\Desktop\\cvut\\ts\\github\\B6B36TS1\\labs\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com/");
        Thread.sleep(5000);  // Let the user actually see something!
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("ChromeDriver");
        searchBox.submit();
        Thread.sleep(5000);  // Let the user actually see something!
        driver.quit();
    }
}

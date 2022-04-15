package cz.cvut.fel.ts1.lab09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeEach
    public void init() {
        String pathToDriver = "C:\\Users\\podav\\Desktop\\cvut\\ts\\github\\B6B36TS1\\labs\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        driver = new ChromeDriver();
    }

    @AfterEach
    public void clean() {
        driver.quit();
    }
}

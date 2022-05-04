package cz.cvut.fel.ts1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeEach
    public void init() {
        String pathToDriver = "chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        driver = new ChromeDriver();
    }

    @AfterEach
    public void clean() {
        driver.quit();
    }
}

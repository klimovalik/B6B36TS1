package cz.cvut.fel.ts1.lab11;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class StartPage {

    private final WebDriver driver;
    private WebElement prihlasitSe;

    public StartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get("");
    }
}

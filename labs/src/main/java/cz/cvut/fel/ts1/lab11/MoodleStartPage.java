package cz.cvut.fel.ts1.lab11;

import cz.cvut.fel.ts1.lab10.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MoodleStartPage {

    private final WebDriver driver;

    @FindBy(how = How.XPATH,
            using = "//li[@class='d-flex align-items-center ml-auto']/a[@class='nav-link']")
    private WebElement logIn;

    public MoodleStartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get("https://moodle.fel.cvut.cz/");
    }

    public MoodleLogInPage clickLogIn() {
        Utils.jsClick(logIn, driver);
        return new MoodleLogInPage(driver);
    }
}

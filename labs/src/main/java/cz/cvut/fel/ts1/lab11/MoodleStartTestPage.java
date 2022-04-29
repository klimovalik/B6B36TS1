package cz.cvut.fel.ts1.lab11;

import cz.cvut.fel.ts1.lab10.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MoodleStartTestPage {

    private final WebDriver driver;

    @FindBy(how = How.XPATH,
            using = "//button[text()='Začít další pokus']")
    private WebElement startTest;

    @FindBy(how = How.XPATH,
            using = "//input[@value='Zahájení pokusu']")
    private WebElement submitTest;

    public MoodleStartTestPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MoodleTestPage clickStartTest() {
        Utils.jsClick(startTest, driver);
        Utils.jsClick(submitTest, driver);
        return new MoodleTestPage(driver);
    }
}

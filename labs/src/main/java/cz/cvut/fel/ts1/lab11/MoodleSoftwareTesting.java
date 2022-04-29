package cz.cvut.fel.ts1.lab11;

import cz.cvut.fel.ts1.lab10.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MoodleSoftwareTesting {

    private final WebDriver driver;

    @FindBy(how = How.XPATH,
            using = "//li[@id='section-4']//span[@class='sectionname']")
    private WebElement labs;

    @FindBy(how = How.XPATH,
            using = "//li[@id='module-233484']//a[@class='aalink']")
    private WebElement robotTest;

    public MoodleSoftwareTesting(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MoodleSoftwareTesting clickLabs() {
        Utils.jsClick(labs, driver);
        return this;
    }

    public MoodleStartTestPage clickRobotTest() {
        Utils.jsClick(robotTest, driver);
        return new MoodleStartTestPage(driver);
    }
}

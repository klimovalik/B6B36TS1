package cz.cvut.fel.ts1.lab11;

import cz.cvut.fel.ts1.lab10.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MoodleCoursePage {

    private final WebDriver driver;

    @FindBy(how = How.XPATH,
            using = "//div[@name='B212']//h6[text()='B6B36TS1']")
    private WebElement softwareTesting;

    public MoodleCoursePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MoodleSoftwareTesting clickSoftwareTesting() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@name='B212']//h6[text()='B6B36TS1']")));
        Utils.jsClick(softwareTesting, driver);
        return new MoodleSoftwareTesting(driver);
    }
}

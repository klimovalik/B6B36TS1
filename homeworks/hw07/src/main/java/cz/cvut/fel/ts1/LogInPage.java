package cz.cvut.fel.ts1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInPage {

    private final WebDriver driver;

    private final String emailXPath = "//input[@id='login-box-email']";
    private final String passwordXPath = "//input[@id='login-box-pw']";
    private final String submitXPath = "//button[@title='Log in']";

    @FindBy(how = How.XPATH,
            using = emailXPath)
    private WebElement email;

    @FindBy(how = How.XPATH,
            using = passwordXPath)
    private WebElement password;

    @FindBy(how = How.XPATH,
            using = submitXPath)
    private WebElement submit;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LogInPage inputEmail(String input) {
        waitWebElement(emailXPath);
        email.sendKeys(input);
        return this;
    }

    public LogInPage inputPassword(String input) {
        waitWebElement(passwordXPath);
        password.sendKeys(input);
        return this;
    }

    public MainPage clickSubmit() {
        waitWebElement(submitXPath);
        submit.click();
        return new MainPage(driver);
    }

    private void waitWebElement(String xPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }
}

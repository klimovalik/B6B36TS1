package cz.cvut.fel.ts1.lab11;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SSOGatePage {

    private final WebDriver driver;

    @FindBy(how = How.XPATH,
            using = "//div/input[@id='username']")
    private WebElement username;

    @FindBy(how = How.XPATH,
            using = "//div/input[@id='password']")
    private WebElement password;

    @FindBy(how = How.XPATH,
            using = "//div/button[@type='submit']")
    private WebElement SSOLogIn;

    public SSOGatePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SSOGatePage fillUsernameAndPassword() {
        username.sendKeys("klimoval");
        password.sendKeys("password");
        return this;
    }

    public MoodleCoursePage clickSSOLogIn() {
        SSOLogIn.click();
        return new MoodleCoursePage(driver);
    }
}

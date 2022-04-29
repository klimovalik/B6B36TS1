package cz.cvut.fel.ts1.lab11;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class MoodleLogInPage {

    private final WebDriver driver;

    @FindBy(how = How.XPATH,
            using = "//div[@id='sso-form']/a[@class='btn btn-primary w-100']")
    private WebElement SSOLogIn;

    public MoodleLogInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SSOGatePage clickSSOLogIn() {
        SSOLogIn.click();
        return new SSOGatePage(driver);
    }
}

package cz.cvut.fel.ts1.lab10;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DemoQAMainPage {

    private final WebDriver driver;

    @FindBy(how = How.XPATH,
            using = "//h5[text()='Forms']//ancestor::div[@class='card mt-4 top-card']")
    private WebElement formsButton;

    public DemoQAMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get("https://demoqa.com/");
    }

    public FormsPage clickForms() {
//        formsButton.click();
        Utils.jsClick(formsButton, driver);
        return new FormsPage(driver);
    }
}

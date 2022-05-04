package cz.cvut.fel.ts1;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class MainPage {

    private final WebDriver driver;

    private final String acceptCookiesXPath = "//button[@data-cc-action='accept']";

    private final String menuXPath = "//button[@class='pillow-btn open-menu']";
    private final String menuLogInXPath = "//div[@class='panel-menu']//a[@class='register-link flyout-caption']";
    private final String wideLogInXPath = "//div[@class='cross-nav cross-nav--wide']//a[@class='register-link flyout-caption']";

    private final String searchOptionsXPath = "//div[@id='search-options']";
    private final String advancedSearchXPath = "//a[@id='advanced-search-link']";

    @FindBy(how = How.XPATH,
            using = acceptCookiesXPath)
    private WebElement acceptCookies;

    @FindBy(how = How.XPATH,
            using = menuXPath)
    private WebElement menu;

    @FindBy(how = How.XPATH,
            using = menuLogInXPath)
    private WebElement menuLogIn;

    @FindBy(how = How.XPATH,
            using = wideLogInXPath)
    private WebElement wideLogIn;

    @FindBy(how = How.XPATH,
            using = searchOptionsXPath)
    private WebElement searchOptions;

    @FindBy(how = How.XPATH,
            using = advancedSearchXPath)
    private WebElement advancedSearch;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get("https://link.springer.com/");
    }

    public MainPage clickAcceptCookies() {
        waitWebElement(acceptCookiesXPath);
        acceptCookies.click();
        return this;
    }

    public LogInPage clickLogIn() {
        try {
            waitWebElement(menuXPath);
            menu.click();
            waitWebElement(menuLogInXPath);
            menuLogIn.click();
        } catch (Exception ignored){
            waitWebElement(wideLogInXPath);
            wideLogIn.click();
        }
        return new LogInPage(driver);
    }

    public MainPage clickSearchOptions() {
        waitWebElement(searchOptionsXPath);
        searchOptions.click();
        return this;
    }

    public AdvancedSearchPage clickAdvancedSearch() {
        waitWebElement(advancedSearchXPath);
        advancedSearch.click();
        return new AdvancedSearchPage(driver);
    }

    private void waitWebElement(String xPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(TimeoutException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }
}

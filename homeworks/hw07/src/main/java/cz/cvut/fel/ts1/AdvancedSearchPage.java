package cz.cvut.fel.ts1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Year;

public class AdvancedSearchPage {

    private final WebDriver driver;

    private final String andWordsXPath = "//input[@id='all-words']";
    private final String orWordsXPath = "//input[@id='least-words']";

    private final String yearOptionsXPath = "//select[@id='date-facet-mode']";
    private final String startYearXPath = "//input[@id='facet-start-year']";
    private final String endYearXPath = "//input[@id='facet-end-year']";

    private final String titleXPath = "//input[@id='title-is']";

    private final String searchXPath = "//button[@id='submit-advanced-search']";

    @FindBy(how = How.XPATH,
            using = andWordsXPath)
    private WebElement andWords;

    @FindBy(how = How.XPATH,
            using = orWordsXPath)
    private WebElement orWords;

    @FindBy(how = How.XPATH,
            using = yearOptionsXPath)
    private WebElement yearOptions;

    @FindBy(how = How.XPATH,
            using = startYearXPath)
    private WebElement startYear;

    @FindBy(how = How.XPATH,
            using = endYearXPath)
    private WebElement endYear;

    @FindBy(how = How.XPATH,
            using = titleXPath)
    private WebElement title;

    @FindBy(how = How.XPATH,
            using = searchXPath)
    private WebElement search;

    public AdvancedSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AdvancedSearchPage inputAndWords(String input) {
        waitWebElement(andWordsXPath);
        andWords.sendKeys(input);
        return this;
    }

    public AdvancedSearchPage inputOrWords(String input) {
        waitWebElement(orWordsXPath);
        orWords.sendKeys(input);
        return this;
    }

    public AdvancedSearchPage selectYearOption(String option) {
        waitWebElement(yearOptionsXPath);
        Select selectOption = new Select(yearOptions);
        selectOption.selectByValue(option);
        return this;
    }

    public AdvancedSearchPage inputBetweenYear() {
        waitWebElement(startYearXPath);
        startYear.sendKeys(Year.now().toString());
        waitWebElement(endYearXPath);
        endYear.sendKeys(Year.now().toString());
        return this;
    }

    /**
     * Option "in" does not filter the result as expected.
     */
    public AdvancedSearchPage inputInYear() {
        waitWebElement(startYearXPath);
        startYear.sendKeys(Year.now().toString());
        return this;
    }

    public AdvancedSearchPage inputTitle(String input) {
        waitWebElement(titleXPath);
        title.sendKeys(input);
        return this;
    }

    public SearchPage clickSearch() {
        waitWebElement(searchXPath);
        search.click();
        return new SearchPage(driver);
    }

    private void waitWebElement(String xPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }
}

package cz.cvut.fel.ts1;

import cz.cvut.fel.ts1.article.Article;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchPage {

    private final WebDriver driver;

    private final String articleContentTypeXPath = "//span[text()='Article']";

    private final String firstArticleXPath = "//a[text()='SRI 2022: Scientific Abstracts']";
    private final String secondArticleXPath = "//a[text()='Suggesting model transformation repairs for rule-based languages using a contract-based testing approach']";
    private final String thirdArticleXPath = "//a[text()='TOAST: Automated Testing of Object Transformers in Dynamic Software Updates']";
    private final String fourthArticleXPath = "//a[text()='Data flow testing of feature-oriented programs']";

    private final String articleResultXPath = "//ol[@id='results-list']//a";

    @FindBy(how = How.XPATH,
            using = articleContentTypeXPath)
    private WebElement articleContentType;

    @FindBy(how = How.XPATH,
            using = firstArticleXPath)
    private WebElement firstArticle;

    @FindBy(how = How.XPATH,
            using = secondArticleXPath)
    private WebElement secondArticle;

    @FindBy(how = How.XPATH,
            using = thirdArticleXPath)
    private WebElement thirdArticle;

    @FindBy(how = How.XPATH,
            using = fourthArticleXPath)
    private WebElement fourthArticle;

    @FindBy(how = How.XPATH,
            using = articleResultXPath)
    private WebElement articleResult;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SearchPage clickArticleContentType() {
        waitWebElement(articleContentTypeXPath);
        articleContentType.click();
        return this;
    }

    public List<Article> getArticles() {
        List<Article> articles = new ArrayList<>();

        List<WebElement> articleResults = new ArrayList<>();
        articleResults.add(firstArticle);
        articleResults.add(secondArticle);
        articleResults.add(thirdArticle);
        articleResults.add(fourthArticle);

        System.out.println(articleResults);

        for (WebElement result : articleResults) {
            result.click();
            articles.add(new ArticlePage(driver).getArticle());
            driver.navigate().back();
        }

        return articles;
    }

    public Article getArticleByTitle() {
        waitWebElement(articleResultXPath);
        articleResult.click();
        return new ArticlePage(driver).getArticle();
    }

    private void waitWebElement(String xPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }
}

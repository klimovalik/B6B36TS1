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

public class ArticlePage {

    private final WebDriver driver;

    private final String titleXPath = "//h1[@class='c-article-title']";
    private final String DOIXPath = "//li[contains(@class,'doi')]//span[contains(@class,'value')]";
    private final String publishedXPath = "//p[text()='Published']//time";

    @FindBy(how = How.XPATH,
            using = titleXPath)
    private WebElement title;

    @FindBy(how = How.XPATH,
            using = DOIXPath)
    private WebElement DOI;

    @FindBy(how = How.XPATH,
            using = publishedXPath)
    private WebElement published;

    public ArticlePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Article getArticle() {
        waitWebElement(titleXPath);
        String titleStr = title.getText();

        waitWebElement(DOIXPath);
        String DOIStr = DOI.getText();

        waitWebElement(publishedXPath);
        String publishedStr = published.getText();

        // System.out.println(titleStr + "\n" + DOIStr + "\n" + publishedStr);

        return new Article(titleStr, DOIStr, publishedStr);
    }

    private void waitWebElement(String xPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }
}

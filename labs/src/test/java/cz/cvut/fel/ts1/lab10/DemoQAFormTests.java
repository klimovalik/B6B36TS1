package cz.cvut.fel.ts1.lab10;

import cz.cvut.fel.ts1.lab09.TestCase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DemoQAFormTests extends TestCase {

    @Test
    public void endToEndFillForm() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://demoqa.com/");

        WebElement formsButton = driver.findElement(By.xpath("//h5[text()='Forms']//ancestor::div[@class='card mt-4 top-card']"));
        wait.until(ExpectedConditions.visibilityOf(formsButton));
        jsClick(formsButton);

        WebElement practiceFormButton = driver.findElement(By.xpath("//span[text()='Practice Form']//../../li[@id='item-0']"));
        wait.until(ExpectedConditions.visibilityOf(practiceFormButton));
        jsClick(practiceFormButton);
    }

    @Test
    public void jsClick(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", element);
    }

    @Test
    public void mainPage() {
        DemoQAMainPage mainPage = new DemoQAMainPage(getDriver());
        mainPage.clickForms().clickPracticeForm();
    }
}

package cz.cvut.fel.ts1.lab09;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DemoQATests extends TestCase {

    @Test
    public void openDemoQA() {
        WebDriver driver = getDriver();
        driver.get("https://demoqa.com/");
    }

    @Test
    public void findElementsTest() {
        WebDriver driver = getDriver();
        driver.get("https://demoqa.com/automation-practice-form");
        WebElement firstNameID = driver.findElement(By.id("firstName"));
        WebElement lastNameXpath = driver.findElement(By.xpath("//div/input[@id='lastName']"));
        System.out.println();
    }

    @Test
    public void fillFormTest() {
        WebDriver driver = getDriver();
        driver.get("https://demoqa.com/automation-practice-form");

        WebElement firstNameID = driver.findElement(By.id("firstName"));
        WebElement lastNameXpath = driver.findElement(By.xpath("//input[@id='lastName']"));
        WebElement emailID = driver.findElement(By.id("userEmail"));
        WebElement mobilXpath = driver.findElement(By.xpath("//div/input[@id='userNumber']"));
        WebElement hobbiesCheckbox = driver.findElement(By.xpath("//label[@for='hobbies-checkbox-1']"));

        firstNameID.sendKeys("Valeriia");
        lastNameXpath.sendKeys("Klimova");
        emailID.sendKeys("klimoval@fel.cvut.cz");
        mobilXpath.sendKeys("9999111555");
        hobbiesCheckbox.click();

        System.out.println();
    }
}

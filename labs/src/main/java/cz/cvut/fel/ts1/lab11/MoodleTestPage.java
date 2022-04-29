package cz.cvut.fel.ts1.lab11;

import cz.cvut.fel.ts1.lab10.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class MoodleTestPage {

    private final WebDriver driver;

    @FindBy(how = How.XPATH,
            using = "//textarea[contains(@id,':1_answer_id')]")
    private WebElement answer1;

    @FindBy(how = How.XPATH,
            using = "//input[contains(@id,':2_answer')]")
    private WebElement answer2;

    @FindBy(how = How.XPATH,
            using = "//select[contains(@id,'_3_p1')]")
    private WebElement answer3;

    @FindBy(how = How.XPATH,
            using = "//select[contains(@id,'_4_p1')]")
    private WebElement answer4;

    @FindBy(how = How.XPATH,
            using = "//div[@class='submitbtns']//a[text()='Dokončit prohlídku']")
    private WebElement end;

    public MoodleTestPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MoodleTestPage fillAnswers() {
        answer1.sendKeys("Valeriia Klimova 106");
        answer2.sendKeys("86400");
        Select selectAnswer3 = new Select(answer3);
        selectAnswer3.selectByVisibleText("Oberon");
        Select selectAnswer4 = new Select(answer4);
        selectAnswer4.selectByVisibleText("Rumunsko");
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        return this;
    }

    public MoodleTestPage clickEnd() {
        Utils.jsClick(end, driver);
        return this;
    }
}

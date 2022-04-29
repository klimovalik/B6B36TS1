package cz.cvut.fel.ts1.lab11;

import cz.cvut.fel.ts1.lab09.TestCase;
import org.junit.jupiter.api.Test;

public class MoodleTest extends TestCase {

    @Test
    public void startPage() {
        MoodleStartPage startPage = new MoodleStartPage(getDriver());
        startPage.clickLogIn().clickSSOLogIn()
                .fillUsernameAndPassword().clickSSOLogIn()
                .clickSoftwareTesting().clickLabs().clickRobotTest()
                .clickStartTest().fillAnswers().clickEnd();
    }
}

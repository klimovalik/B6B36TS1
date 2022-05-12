package cz.cvut.fel.ts1;

import cz.cvut.fel.ts1.article.Article;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpringerLinkTest extends TestCase {

    private static List<Article> articles = new ArrayList<>();

    @Test
    @Order(1)
    public void main() {
        MainPage mainPage = new MainPage(getDriver());
        SearchPage searchPage = mainPage.clickAcceptCookies()
                                        .clickSearchOptions()
                                        .clickAdvancedSearch()
                                        .inputAndWords("Page Object Model")
                                        .inputOrWords("Selenium Testing")
                                        .selectYearOption("between")
                                        .inputBetweenYear(Year.now().toString())
                                        .clickSearch()
                                        .clickArticleContentType();

        articles = searchPage.getArticles();
    }

    static Stream<Arguments> argumentsSource() {
        Article firstArticle = articles.get(0);
        Article secondArticle = articles.get(1);
        Article thirdArticle = articles.get(2);
        Article fourthArticle = articles.get(3);

        return Stream.of(
                Arguments.of(firstArticle.getTitle(), firstArticle.getDOI(), firstArticle.getPublished()),
                Arguments.of(secondArticle.getTitle(), secondArticle.getDOI(), secondArticle.getPublished()),
                Arguments.of(thirdArticle.getTitle(), thirdArticle.getDOI(), thirdArticle.getPublished()),
                Arguments.of(fourthArticle.getTitle(), fourthArticle.getDOI(), fourthArticle.getPublished())
        );
    }

    @ParameterizedTest(name = "Article {0} has DOI {1} and was published on {2}")
    @MethodSource("argumentsSource")
    @Order(2)
    public void checkArticle_getsArticleTitle_returnsExpectedDOIAndPublished(String title, String expectedDOI, String expectedPublished) {
        MainPage mainPage = new MainPage(getDriver());
        Article foundArticle = mainPage.clickAcceptCookies()
                                       .clickLogIn()
                                       .inputEmail("klimoval@fel.cvut.cz")
                                       .inputPassword("password")
                                       .clickSubmit()
                                       .clickSearchOptions()
                                       .clickAdvancedSearch()
                                       .inputTitle(title)
                                       .clickSearch()
                                       .getArticleByTitle();

        Assertions.assertEquals(expectedDOI, foundArticle.getDOI());
        Assertions.assertEquals(expectedPublished, foundArticle.getPublished());
    }
}

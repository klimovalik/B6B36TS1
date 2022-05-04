package cz.cvut.fel.ts1.article;

public class Article {
    private final String title;
    private final String DOI;
    private final String published;

    public Article(String title, String DOI, String published) {
        this.title = title;
        this.DOI = DOI;
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public String getDOI() {
        return DOI;
    }

    public String getPublished() {
        return published;
    }
}

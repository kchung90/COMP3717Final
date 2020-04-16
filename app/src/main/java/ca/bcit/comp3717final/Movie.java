package ca.bcit.comp3717final;

public class Movie {
    String title;
    String description;
    String url;

    Movie() {

    }

    Movie(String title, String description, String url) {
        this.title = title;
        this.description = description;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}

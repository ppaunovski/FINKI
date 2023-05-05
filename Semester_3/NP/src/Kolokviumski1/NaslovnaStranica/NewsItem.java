package Kolokviumski1.NaslovnaStranica;

import java.util.Date;

public abstract class NewsItem {
    String title;
    Date date;
    Category category;

    public NewsItem(String title, Date date, Category category) {
        this.date = date;
        this.title = title;
    }
    public abstract String getTeaser();
}

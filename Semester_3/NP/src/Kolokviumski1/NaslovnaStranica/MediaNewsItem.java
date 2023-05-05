package Kolokviumski1.NaslovnaStranica;

import java.util.Date;

public class MediaNewsItem extends NewsItem{
    String url;
    int previews;

    public MediaNewsItem(String title, Date date, Category category, String url, int previews) {
        super(title, date, category);
        this.url = url;
        this.previews = previews;
    }

    @Override
    public String getTeaser() {
        return String.format("%s\n%s\n%s\n%d", this.title, Date.from(date.toInstant()).toString(), this.url, this.previews);
    }

    @Override
    public String toString() {
        return category.toString();
    }
}

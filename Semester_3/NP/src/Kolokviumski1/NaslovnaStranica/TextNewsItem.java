package Kolokviumski1.NaslovnaStranica;

import java.util.Arrays;
import java.util.Date;

public class TextNewsItem extends NewsItem {
    String text;

    public TextNewsItem(String title, Date date, Category category, String text) {
        super(title, date, category);
        this.text = text;
        this.category = category;
    }

    @Override
    public String getTeaser() {
        StringBuilder sb = new StringBuilder();
        if(text.length() >= 80){
            sb.append(text, 0, 80);
        }
        else sb.append(text);
        return String.format("%s\n%s\n%s", this.title, Date.from(date.toInstant()).toString(), sb.toString());
    }

    @Override
    public String toString() {
        return category.toString();
    }
}

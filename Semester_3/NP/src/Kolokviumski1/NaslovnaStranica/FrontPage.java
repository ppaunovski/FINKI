package Kolokviumski1.NaslovnaStranica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FrontPage {
    List<NewsItem> news;
    Category[] categories;

    public FrontPage(Category[] categories) {
        news = new ArrayList<>();
        this.categories = categories;
    }

    void addNewsItem(NewsItem newsItem){
        news.add(newsItem);
    }

    List<NewsItem> listByCategory(Category category){
        System.out.println(news);
        return null;
//        return news.stream()
////                .filter(newsItem -> newsItem.category != null)
//                .filter(newsItem -> newsItem.category.name.compareTo(category.name) == 0)
//                .collect(Collectors.toList());
    }

    List<NewsItem> listByCategoryName(String category) throws CategoryNotFoundException {
        if(!Arrays.stream(categories).collect(Collectors.toList()).contains(new Category(category))){
            throw new CategoryNotFoundException(category);
        }
        return news.stream()
//                .filter(newsItem -> newsItem.category != null)
                .filter(newsItem -> newsItem.category.toString().compareTo(category) == 0)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        news.forEach(newsItem -> stringBuilder.append(newsItem.getTeaser()));
        return stringBuilder.toString();
    }
}

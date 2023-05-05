package Kolokviumski2.BookCollection;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Book implements Comparable<Book>{
    String title;
    String category;
    float price;

    public Book(String title, String category, float price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) %.2f",title, category, price);
    }

    @Override
    public int compareTo(Book o) {
        return this.title.compareTo(o.title) == 0 ? Float.compare(this.price, o.price) : this.title.compareTo(o.title);
    }
}

class BookCollection{
    Map<String, List<Book>> booksByCategory;

    public BookCollection() {
        this.booksByCategory = new HashMap<>();
    }

    public void addBook(Book book){
        booksByCategory.putIfAbsent(book.category.toLowerCase(), new ArrayList<>());
        booksByCategory.get(book.category.toLowerCase()).add(book);
    }

    public void printByCategory(String cat){
        booksByCategory.get(cat.toLowerCase()).stream()
                .sorted()
                .forEach(System.out::println);
    }

    public List<Book> getCheapestN(int n){
        return booksByCategory.values().stream()
                .reduce(new ArrayList<>(), (books, books2) -> Stream.concat(books.stream(), books2.stream()).collect(Collectors.toList()))
                .stream()
                .sorted(Comparator.comparing(Book::getPrice).thenComparing(Book::getTitle))
                .limit(n)
                .collect(Collectors.toList());
    }
}


public class BooksTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        BookCollection booksCollection = new BookCollection();
        Set<String> categories = fillCollection(scanner, booksCollection);
        System.out.println("=== PRINT BY CATEGORY ===");
        for (String category : categories) {
            System.out.println("CATEGORY: " + category);
            booksCollection.printByCategory(category);
        }
        System.out.println("=== TOP N BY PRICE ===");
        print(booksCollection.getCheapestN(n));
    }

    static void print(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    static TreeSet<String> fillCollection(Scanner scanner,
                                          BookCollection collection) {
        TreeSet<String> categories = new TreeSet<String>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            Book book = new Book(parts[0], parts[1], Float.parseFloat(parts[2]));
            collection.addBook(book);
            categories.add(parts[1]);
        }
        return categories;
    }
}

package Kolokviumski1.NaslovnaStranica;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException(String message) {
        super(String.format("Category %s was not found", message));
    }
}

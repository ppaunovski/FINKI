package Kolokviumski1.FileSystem;

public class FileNameExistsException extends Exception {
    public FileNameExistsException(String message) {
        super(String.format("There is already a file named %s in the folder test", message));
    }
}

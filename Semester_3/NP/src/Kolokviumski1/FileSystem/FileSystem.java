package Kolokviumski1.FileSystem;

public class FileSystem {
    Folder rootDirectory;

    public FileSystem() {
        this.rootDirectory = new Folder("root");
    }

    void addFile (IFile file) throws FileNameExistsException {
        rootDirectory.addFile(file);
    }

    long findLargestFile (){
        return rootDirectory.findLargestFile();
    }

    void sortBySize(){
        rootDirectory.sortBySize();
    }

    @Override
    public String toString() {
        return rootDirectory.getFileInfo(rootDirectory);
    }
}

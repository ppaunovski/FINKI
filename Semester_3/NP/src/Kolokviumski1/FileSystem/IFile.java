package Kolokviumski1.FileSystem;

import java.util.List;

public interface IFile extends Comparable<IFile>{
    String getFileName();
    long getFileSize();
    String getFileInfo(IFile file);
    void sortBySize();
    long findLargestFile ();
    String getType();
}

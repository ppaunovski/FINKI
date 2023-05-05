package Kolokviumski1.FileSystem;

import java.util.List;

public class File implements IFile{
    String name;
    long size;
    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getFileName() {
        return this.name;
    }

    @Override
    public long getFileSize() {
        return size;
    }

    @Override
    public String getFileInfo(IFile file) {
        return String.format("File name %10s File size: %10d\n", file.getFileName(), file.getFileSize());
    }

    @Override
    public String toString() {
        return this.getFileInfo(this);
    }

    @Override
    public void sortBySize() {

    }

    @Override
    public long findLargestFile() {
        return getFileSize();
    }

    @Override
    public String getType() {
        return "file";
    }

    @Override
    public int compareTo(IFile o) {
        return Long.compare(this.getFileSize(), o.getFileSize());
    }
}

package Kolokviumski1.FileSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;

public class Folder implements IFile {
    String name;
    long size;
    List<IFile> iFileList;

    public Folder(String name) {
        this.name = name;
        this.size = 0;
        iFileList = new ArrayList<>();
    }

    void addFile (IFile file) throws FileNameExistsException{
        boolean isPresent = iFileList.stream()
                .anyMatch(iFile -> iFile.getFileName().equals(file.getFileName()));
        if(isPresent)
            throw new FileNameExistsException(file.getFileName());

        iFileList.add(file);
    }

    @Override
    public String getFileName() {
        return name;
    }

    @Override
    public long getFileSize() {
        return iFileList.stream()
                .map(IFile::getFileSize)
                .reduce(0L, Long::sum);
    }

    @Override
    public String getFileInfo(IFile file) {
        StringBuilder stringBuilder = new StringBuilder();

        //stringBuilder.append(String.format("Folder name: %10s Folder size: %10d\n", this.getFileName(), this.getFileSize()));
        iFileList.forEach(iFile -> file.getFileInfo(iFile));

        stringBuilder.append(String.format("\t%s", this));
        return stringBuilder.toString();
    }

    @Override
    public void sortBySize() {
        iFileList.stream()
                .sorted()
                .forEach(IFile::sortBySize);
    }

    @Override
    public long findLargestFile() {
        return iFileList.stream()
                .filter(iFile -> iFile.getType().equals("file"))
                .map(IFile::getFileSize)
                .max(Comparator.naturalOrder())
                .orElse(0L);
    }

    @Override
    public String getType() {
        return "dir";
    }

    @Override
    public int compareTo(IFile o) {
        return Long.compare(this.getFileSize(), o.getFileSize());
    }

    @Override
    public String toString() {
        return String.format("Folder name: %10s Folder size: %10d\n", this.getFileName(), this.getFileSize());
    }
}

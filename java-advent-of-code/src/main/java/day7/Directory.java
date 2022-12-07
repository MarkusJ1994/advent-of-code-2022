package day7;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Directory {

    private final String name;
    private final Directory parent;
    private final int depth;
    private List<Directory> directories = new ArrayList<>();
    private List<File> files = new ArrayList<>();


    public Directory(String name, Directory parent, int depth) {
        this.name = name;
        this.parent = parent;
        this.depth = depth;
    }

    public void addDirectory(Directory directory) {
        directories.add(directory);
    }

    public void addFile(File file) {
        files.add(file);
    }

    public int sizeOfDirectory() {
        return files.stream().mapToInt(file -> file.size()).sum() + directories.stream().mapToInt(dir -> dir.sizeOfDirectory()).sum();
    }

    public int getDepth() {
        return depth;
    }

    public String getName() {
        return name;
    }

    public Directory getParent() {
        return parent;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public List<File> getFiles() {
        return files;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directory directory = (Directory) o;
        return Objects.equals(name, directory.name) && Objects.equals(parent, directory.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent);
    }

    @Override
    public String toString() {
        return String.format("- %s (dir)", name);
    }
}

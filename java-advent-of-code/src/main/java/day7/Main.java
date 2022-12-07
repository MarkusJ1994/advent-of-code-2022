package day7;

import shared.FileReaderUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> input = new FileReaderUtil().readFile("day7_input.txt");

        FileSystem fileSystem = new FileSystem(input);
        fileSystem.interpretStructure();
        printFileSystem(fileSystem);

        part2(fileSystem);
    }

    private static void part1(FileSystem fileSystem) {
        HashMap<Directory, Integer> sizeMap = new HashMap<>();

        Main.mapSizeOfEachDirectory(fileSystem.getRoot(), sizeMap);

        int sum = sizeMap.values().stream().mapToInt(size -> size).filter(size -> size <= 100000).sum();

        System.out.println("Total size of directories with a max size of " + 100000 + " is: " + sum);
    }

    private static void part2(FileSystem fileSystem) {
        int requiredUpdateSize = 30000000;

        HashMap<Directory, Integer> sizeMap = new HashMap<>();

        Main.mapSizeOfEachDirectory(fileSystem.getRoot(), sizeMap);

        Directory smallestDirectoryToDeleteForDesiredSpace = findSmallestDirectoryToDeleteForDesiredSpace(sizeMap, fileSystem.getRoot().sizeOfDirectory(), requiredUpdateSize);

        System.out.println("Smallest possible directory to delete to make space is: " + smallestDirectoryToDeleteForDesiredSpace.getName());
    }

    protected static Directory findSmallestDirectoryToDeleteForDesiredSpace(HashMap<Directory, Integer> sizeMap, int currentSpace, int requiredFreeSpace) {
        int currentFreeSpace = FileSystem.MAX_SIZE - currentSpace;
        int spaceToBeFreed = requiredFreeSpace - currentFreeSpace;

        LinkedHashMap<Directory, Integer> sortedBySize = sizeMap.entrySet().stream()
                .filter(e -> e.getValue() >= spaceToBeFreed)
                .sorted(Map.Entry.<Directory, Integer>comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        Map.Entry<Directory, Integer> directoryToDelete = sortedBySize.entrySet().stream().findFirst().orElse(null);

        System.out.println(sortedBySize);
        return directoryToDelete.getKey();
    }

    protected static void printFileSystem(FileSystem fileSystem) {
        traverseAndPrintDirectory(fileSystem.getRoot());
    }

    private static void traverseAndPrintDirectory(Directory directory) {
        System.out.println(String.format("%" + directory.getDepth() + "s", "") + directory);
        directory.getFiles().forEach(file -> System.out.println(String.format("%" + (directory.getDepth() + 1) + "s", "") + file));
        directory.getDirectories().forEach(dir -> traverseAndPrintDirectory(dir));
    }

    protected static void mapSizeOfEachDirectory(Directory directory, Map<Directory, Integer> directoryAndSize) {
        directoryAndSize.put(directory, directory.sizeOfDirectory());
        directory.getDirectories().forEach(dir -> mapSizeOfEachDirectory(dir, directoryAndSize));
    }

}

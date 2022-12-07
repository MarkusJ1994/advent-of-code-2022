package day7;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileSystemTest {

    String testData = "- / (dir)\n" +
            "  - a (dir)\n" +
            "    - e (dir)\n" +
            "      - i (file, size=584)\n" +
            "    - f (file, size=29116)\n" +
            "    - g (file, size=2557)\n" +
            "    - h.lst (file, size=62596)\n" +
            "  - b.txt (file, size=14848514)\n" +
            "  - c.dat (file, size=8504156)\n" +
            "  - d (dir)\n" +
            "    - j (file, size=4060174)\n" +
            "    - d.log (file, size=8033020)\n" +
            "    - d.ext (file, size=5626152)\n" +
            "    - k (file, size=7214296)";

    List<String> testInput = List.of(
            "$ cd /",
            "$ ls",
            "dir a",
            "14848514 b.txt",
            "8504156 c.dat",
            "dir d",
            "$ cd a",
            "$ ls",
            "dir e",
            "29116 f",
            "2557 g",
            "62596 h.lst",
            "$ cd e",
            "$ ls",
            "584 i",
            "$ cd /",
            "$ cd d",
            "$ ls",
            "4060174 j",
            "8033020 d.log",
            "5626152 d.ext",
            "7214296 k"
    );

    @Test
    public void testStructure() {
        FileSystem fileSystem = new FileSystem(testInput);

        fileSystem.interpretStructure();

        Main.printFileSystem(fileSystem);
    }

    @Test
    public void testSizeOfDirectories() {
        FileSystem fileSystem = new FileSystem(testInput);

        fileSystem.interpretStructure();

        Main.printFileSystem(fileSystem);

        HashMap<Directory, Integer> sizeMap = new HashMap<>();

        Main.mapSizeOfEachDirectory(fileSystem.getRoot(), sizeMap);

        sizeMap.entrySet().forEach(e -> {
            switch (e.getKey().getName()) {
                case "e" -> assertEquals(584, e.getValue());
                case "a" -> assertEquals(94853, e.getValue());
                case "d" -> assertEquals(24933642, e.getValue());
                case "/" -> assertEquals(48381165, e.getValue());
            }

        });

    }

    @Test
    public void testPart1() {
        FileSystem fileSystem = new FileSystem(testInput);

        fileSystem.interpretStructure();

        Main.printFileSystem(fileSystem);

        HashMap<Directory, Integer> sizeMap = new HashMap<>();

        Main.mapSizeOfEachDirectory(fileSystem.getRoot(), sizeMap);

        int sum = sizeMap.values().stream().mapToInt(val -> val).filter(size -> size <= 100000).sum();

        assertEquals(95437, sum);
    }

}
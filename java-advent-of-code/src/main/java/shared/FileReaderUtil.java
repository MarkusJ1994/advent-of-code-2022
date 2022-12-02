package shared;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class FileReaderUtil {

    public List<String> readFile(String fileName) {
        return readFileAsStream(fileName).toList();
    }

    public String readFileAsString(String fileName) {
        try {
            return Files.readString(getFilePath(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Stream<String> readFileAsStream(String fileName) {
        try {
            return Files.lines(getFilePath(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getFilePath(String fileName) {
        URL url = getClass().getClassLoader().getResource(fileName);
        try {
            return Paths.get(url.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}

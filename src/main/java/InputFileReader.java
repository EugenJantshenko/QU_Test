import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class InputFileReader {

    List<String> readFromTxt(String path) {
        Stream<String> lines;
        try {
            Path filePath = Paths.get(getClass().getClassLoader().getResource(path).toURI());
            lines = Files.lines(filePath);
            return lines.collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}

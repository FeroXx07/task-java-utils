package level_2;

import level_1.FileManager;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

public class Main {
    FileManager fileManager = new FileManager();
    private final Path root = FileManager.findProjectRoot();
     void main(String[] args) throws IOException {
         Properties props = loadProperties();
         Path directoryToRead = Path.of(props.getProperty("level_2.directoryToRead"));

         Path txtFilePath = Path.of(props.getProperty("level_2.txt.directoryToSave"),
                 props.getProperty("level_2.txt.fileName") + props.getProperty("level_2.txt.fileExtension"));

         List<String> content = fileManager.traverseTreeAndSaveToFile(directoryToRead, txtFilePath);
         IO.println("The contents of directory: " + directoryToRead + "have been saved in path: " + txtFilePath);
         content.forEach(System.out::println);
    }

    private static Properties loadProperties() throws IOException {
        Properties props = new Properties();

        try (InputStream in = Main.class.getResourceAsStream("/app.properties")){
            if (in == null) {
                throw new IllegalStateException(
                        "app.properties not found");
            }
            props.load(in);
        }
        return props;
    }

}

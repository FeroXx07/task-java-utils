package level_1;

import java.nio.file.Path;
import java.util.List;

public class Main {
    FileManager fileManager = new FileManager();
    private final Path root = FileManager.findProjectRoot();
    private final Path txtFilePath = root.resolve(Path.of("src", "main", "resources", "data.txt"));
    private final Path objFilePath = root.resolve(Path.of("src", "main", "resources", "vehicle.ser"));

    void main(String[] args) {
        Vehicle vehicle = new Vehicle("BMW", "Black", 4, 150);

        List<String> content = fileManager.traverseTreeAndSaveToFile(root, txtFilePath);
        content.forEach(System.out::println);
        fileManager.readContentsFromFile(txtFilePath);
        fileManager.saveObjectToFile(vehicle, objFilePath);
        Vehicle readObj = (Vehicle) fileManager.readObjectFromFile(objFilePath);
    }

}

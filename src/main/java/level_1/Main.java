package level_1;

import java.nio.file.Path;

public class Main {
    FileManager fileManager = new FileManager();
    private final Path root = FileManager.findProjectRoot();
    private final Path txtFilePath = root.resolve(Path.of("src", "main", "resources", "data.txt"));

    void main(String[] args) {
        fileManager.traverseTreeAndSaveToFile(root, txtFilePath);
        fileManager.readContentsFromFile(txtFilePath);
    }

}

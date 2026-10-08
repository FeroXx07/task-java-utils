package level_1;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    FileManager fileManager = new FileManager();
    void main(String[] args) {

//        exercise_1();
        exercise_2();
    }

    void exercise_1(){
        Path currentDir = Paths.get(System.getProperty("user.dir"));
        IO.println("Exercise_1: Current Directory: " + currentDir.toString() + "and its contents: ");
        List<String> fileNames = fileManager.getDirectoryContentByAZOrder(currentDir);
        fileNames.forEach(f -> IO.println("- " + f));
    }

    void exercise_2(){
        Path currentDir = Paths.get(System.getProperty("user.dir"));
        IO.println("Exercise_2: Current Directory: " + currentDir.toString() + "and its tree: ");
//        fileManager.walkFileTree(currentDir);
        try {
            fileManager.printTree(currentDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

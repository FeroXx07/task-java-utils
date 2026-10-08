package level_1;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public FileManager() {
    }

    public List<String> getDirectoryContentByAZOrder(Path path) {
        List<Path> list = getDirectoryContent(path);
        list.sort(new ComparatorPathAlphOrder());
        return list.stream()
                .map(p->p.getFileName().toString())
                .toList();
    }

    void printTree(Path dir) throws IOException {
        List<Path> children = getDirectoryContent(dir);
        children.sort(new ComparatorPathAlphOrder());
        for (Path child : children) {
            // print (D) o (F) + lastModifiedTime
            if (Files.isDirectory(child)) {
                IO.println("(D) : " + child);
                printTree(child);
            }
            else  {
                IO.println("(F) : " + child + " " + Files.getLastModifiedTime(child));
            }
        }
    }

    public void walkFileTree(Path startDir) {
        try {
            Files.walkFileTree(startDir, fileTraverser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Path> getDirectoryContent(Path dir){
        List<Path> list = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file: stream) {
                list.add(file);
            }
        } catch (IOException | DirectoryIteratorException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(String.valueOf(x));
        }

        return list;
    }
}

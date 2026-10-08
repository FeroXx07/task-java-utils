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

    public List<String> traverseTree(Path dir) throws IOException {
        List<String> tree = new ArrayList<>();
        List<Path> children = getDirectoryContent(dir);
        children.sort(new ComparatorPathAlphOrder());
        for (Path child : children) {
            if (Files.isDirectory(child)) {
                tree.add("(D) : " + child);
                tree.addAll(traverseTree(child));
            } else {
                tree.add("(F) : " + child + " " + Files.getLastModifiedTime(child));
            }
        }
        return tree;
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

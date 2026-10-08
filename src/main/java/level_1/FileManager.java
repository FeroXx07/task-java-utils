package level_1;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public FileManager() {
    }
    public static Path findProjectRoot() {
        Path dir = Path.of(System.getProperty("user.dir")).toAbsolutePath().normalize();
        while (dir != null) {
            if (Files.isDirectory(dir.resolve("src"))) {
                return dir;
            }
            dir = dir.getParent();
        }
        throw new IllegalStateException(
                "Could not find parent of src from " + System.getProperty("user.dir"));
    }

    public List<Path> getDirectoryContentByAZOrder(Path path) {
        List<Path> list = getDirectoryContent(path);
        list.sort(new ComparatorPathAlphOrder());
        return list;
    }

    public List<String> traverseTree(Path dir) {
        List<String> tree = new ArrayList<>();
        List<Path> children = getDirectoryContentByAZOrder(dir);
        children.sort(new ComparatorPathAlphOrder());
        for (Path child : children) {
            if (Files.isDirectory(child)) {
                tree.add("(D) : " + child);
                tree.addAll(traverseTree(child));
            } else {
                try {
                    tree.add("(F) : " + child + " " + Files.getLastModifiedTime(child));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return tree;
    }

    public List<String> traverseTreeAndSaveToFile(Path dirToTraverse, Path toSavePath) {
        List<String> tree = traverseTree(dirToTraverse);
        saveContentsToFile(tree, toSavePath);
        return tree;
    }

    public List<String> readContentsFromFile(Path file)  {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException at path " + file, e);
        }
        return list;
    }

    public void saveContentsToFile(List<String> contents, Path file){
        try (BufferedWriter bw = Files.newBufferedWriter(file)) {
            for (String content : contents) {
                bw.write(content);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException at path " + file, e);
        }
    }

    public void saveObjectToFile(Serializable object, Path file){
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(file))) {
            oos.writeObject(object);
        } catch (IOException e) {
            throw new RuntimeException("IOException at path " + file, e);
        }
    }

    public Object readObjectFromFile(Path file){
        Object object = null;
        try (ObjectInputStream oos = new ObjectInputStream(Files.newInputStream(file))) {
            object = oos.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Exception at path " + file, e);
        }
        return object;
    }

    private List<Path> getDirectoryContent(Path dir){
        List<Path> list = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file: stream) {
                list.add(file);
            }
        } catch (IOException | DirectoryIteratorException e) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            throw new RuntimeException("IOException at path " + dir, e);
        }

        return list;
    }
}

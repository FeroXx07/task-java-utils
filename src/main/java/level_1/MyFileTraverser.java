package level_1;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class MyFileTraverser extends SimpleFileVisitor<Path> {
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if (Files.isHidden(dir)) {
            return FileVisitResult.CONTINUE;
        }
        System.out.format("(D): %s%n", dir);
        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isHidden(file)) {
            return FileVisitResult.CONTINUE;
        }

        if (!attrs.isRegularFile()) {
            return FileVisitResult.CONTINUE;
        }

        System.out.format("(F) Regular file: %s ", file);
        System.out.println("(" + attrs.lastModifiedTime() + ")");
//        if (attrs.isSymbolicLink()) {
//            System.out.format("(F) Symbolic link: %s ", file);
//        } else if (attrs.isRegularFile()) {
//            System.out.format("(F) Regular file: %s ", file);
//        } else {
//            System.out.format("(F) Other: %s ", file);
//        }
        return super.visitFile(file, attrs);
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.err.println(exc);
        return super.visitFileFailed(file, exc);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return super.postVisitDirectory(dir, exc);
    }
}

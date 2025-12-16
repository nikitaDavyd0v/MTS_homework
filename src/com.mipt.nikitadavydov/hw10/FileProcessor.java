import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    public List<Path> splitFile(String sourcePath, String outputDir, int partSize) throws IOException {
        Path source = Paths.get(sourcePath);
        Path dir = Paths.get(outputDir);

        Files.createDirectories(dir);

        List<Path> partPaths = new ArrayList<>();

        try (FileChannel sourceChannel = FileChannel.open(source, StandardOpenOption.READ)) {
            long fileSize = sourceChannel.size();
            long position = 0;
            int partIndex = 0;

            while (position < fileSize) {
                int currentPartSize = (int) Math.min(partSize, fileSize - position);
                ByteBuffer buffer = ByteBuffer.allocate(currentPartSize);

                int bytesRead = sourceChannel.read(buffer, position);
                if (bytesRead == -1) break;

                buffer.flip();

                String fileName = source.getFileName().toString() + ".part" + (++partIndex);
                Path partPath = dir.resolve(fileName);

                try (FileChannel partChannel = FileChannel.open(partPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
                    partChannel.write(buffer);
                }

                partPaths.add(partPath);
                position += currentPartSize;
            }
        }

        return partPaths;
    }

    public void mergeFiles(List<Path> partPaths, String outputPath) throws IOException {
        Path output = Paths.get(outputPath);

        for (Path part : partPaths) {
            if (!Files.exists(part)) {
                throw new IOException("Часть файла не существует: " + part);
            }
        }

        try (FileChannel outputChannel = FileChannel.open(output, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            for (Path part : partPaths) {
                try (FileChannel partChannel = FileChannel.open(part, StandardOpenOption.READ)) {
                    partChannel.transferTo(0, partChannel.size(), outputChannel);
                }
            }
        }
    }
}

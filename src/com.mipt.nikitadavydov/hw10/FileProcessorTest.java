import org.junit.jupiter.api.*;
import java.nio.file.*;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class FileProcessorTest {

    private FileProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new FileProcessor();
    }

    @Test
    void testSplitAndMergeFile() throws IOException {
        Path testFile = Files.createTempFile("test", ".dat");
        byte[] testData = new byte[1500];
        new Random().nextBytes(testData);
        Files.write(testFile, testData);

        String outputDir = Files.createTempDirectory("parts").toString();
        List<Path> parts = processor.splitFile(testFile.toString(), outputDir, 500);

        assertEquals(3, parts.size());

        Path mergedFile = Files.createTempFile("merged", ".dat");
        processor.mergeFiles(parts, mergedFile.toString());

        assertArrayEquals(Files.readAllBytes(testFile), Files.readAllBytes(mergedFile));

        Files.deleteIfExists(testFile);
        Files.deleteIfExists(mergedFile);
        for (Path part : parts) {
            Files.deleteIfExists(part);
        }
        Files.deleteIfExists(Paths.get(outputDir));
    }
}

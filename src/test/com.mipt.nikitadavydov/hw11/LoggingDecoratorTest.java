import com.mipt.nikitadavydov.hw11.LoggingDecorator;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class LoggingDecoratorTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private LoggingDecorator decorator;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        decorator = new LoggingDecorator(new SimpleDataService());
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testFindDataByKeyLogs() {
        decorator.findDataByKey("key");
        assertTrue(outContent.toString().contains("findDataByKey: key"));
    }

    @Test
    void testSaveDataLogs() {
        decorator.saveData("key", "data");
        assertTrue(outContent.toString().contains("saveData: key=key, data=data"));
    }

    @Test
    void testDeleteDataLogs() {
        decorator.deleteData("key");
        assertTrue(outContent.toString().contains("deleteData: key"));
    }
}

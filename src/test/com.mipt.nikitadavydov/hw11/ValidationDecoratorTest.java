import com.mipt.nikitadavydov.hw11.ValidationDecorator;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ValidationDecoratorTest {

    private ValidationDecorator decorator;

    @BeforeEach
    void setUp() {
        decorator = new ValidationDecorator(new SimpleDataService());
    }

    @Test
    void testFindDataByKeyWithValidKey() {
        decorator.saveData("key", "data");
        Optional<String> result = decorator.findDataByKey("key");
        assertTrue(result.isPresent());
    }

    @Test
    void testFindDataByKeyWithNullKeyThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            decorator.findDataByKey(null);
        });
    }

    @Test
    void testFindDataByKeyWithEmptyKeyThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            decorator.findDataByKey("");
        });
    }

    @Test
    void testSaveDataWithValidData() {
        decorator.saveData("key", "data");
        Optional<String> result = decorator.findDataByKey("key");
        assertTrue(result.isPresent());
    }

    @Test
    void testSaveDataWithNullKeyThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            decorator.saveData(null, "data");
        });
    }

    @Test
    void testSaveDataWithNullDataThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            decorator.saveData("key", null);
        });
    }

    @Test
    void testDeleteDataWithValidKey() {
        decorator.saveData("key", "data");
        boolean result = decorator.deleteData("key");
        assertTrue(result);
    }

    @Test
    void testDeleteDataWithNullKeyThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            decorator.deleteData(null);
        });
    }
}

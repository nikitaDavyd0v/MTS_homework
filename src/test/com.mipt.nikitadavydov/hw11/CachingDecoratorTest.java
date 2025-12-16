import com.mipt.nikitadavydov.hw11.CachingDecorator
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CachingDecoratorTest {

    private CachingDecorator decorator;

    @BeforeEach
    void setUp() {
        decorator = new CachingDecorator(new SimpleDataService());
    }

    @Test
    void testFindDataByKeyWithCache() {
        decorator.saveData("key", "data");
        decorator.findDataByKey("key"); // Первый вызов — из базы
        Optional<String> result = decorator.findDataByKey("key"); // Второй — из кеша
        assertTrue(result.isPresent());
        assertEquals("data", result.get());
    }

    @Test
    void testSaveDataUpdatesCache() {
        decorator.saveData("key", "old");
        decorator.saveData("key", "new");
        Optional<String> result = decorator.findDataByKey("key");
        assertTrue(result.isPresent());
        assertEquals("new", result.get());
    }

    @Test
    void testDeleteDataInvalidatesCache() {
        decorator.saveData("key", "data");
        decorator.deleteData("key");
        Optional<String> result = decorator.findDataByKey("key");
        assertFalse(result.isPresent());
    }
}

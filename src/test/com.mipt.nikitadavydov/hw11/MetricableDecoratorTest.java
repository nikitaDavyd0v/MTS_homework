import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class MetricableDecoratorTest {

    private MetricableDecorator decorator;

    @BeforeEach
    void setUp() {
        decorator = new MetricableDecorator(new SimpleDataService());
    }

    @Test
    void testFindDataByKeySendsMetric() {
        decorator.findDataByKey("key");
        // Проверить вывод в консоль — вручную или через перехват stdout
    }

    @Test
    void testSaveDataSendsMetric() {
        decorator.saveData("key", "data");
        // Проверить вывод в консоль
    }

    @Test
    void testDeleteDataSendsMetric() {
        decorator.deleteData("key");
        // Проверить вывод в консоль
    }
}

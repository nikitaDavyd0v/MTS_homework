import java.util.Optional;

public class LoggingDecorator implements DataService {
    private final DataService delegate;

    public LoggingDecorator(DataService delegate) {
        this.delegate = delegate;
    }

    @Override
    public Optional<String> findDataByKey(String key) {
        System.out.println("findDataByKey: " + key);
        Optional<String> result = delegate.findDataByKey(key);
        System.out.println("findDataByKey result: " + result);
        return result;
    }

    @Override
    public void saveData(String key, String data) {
        System.out.println("saveData: key=" + key + ", data=" + data);
        delegate.saveData(key, data);
    }

    @Override
    public boolean deleteData(String key) {
        System.out.println("deleteData: " + key);
        boolean result = delegate.deleteData(key);
        System.out.println("deleteData result: " + result);
        return result;
    }
}

import java.util.Optional;

public class ValidationDecorator implements DataService {
    private final DataService delegate;

    public ValidationDecorator(DataService delegate) {
        this.delegate = delegate;
    }

    @Override
    public Optional<String> findDataByKey(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Ключ не может быть null или пустым");
        }
        return delegate.findDataByKey(key);
    }

    @Override
    public void saveData(String key, String data) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Ключ не может быть null или пустым");
        }
        if (data == null) {
            throw new IllegalArgumentException("Данные не могут быть null");
        }
        delegate.saveData(key, data);
    }

    @Override
    public boolean deleteData(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Ключ не может быть null или пустым");
        }
        return delegate.deleteData(key);
    }
}

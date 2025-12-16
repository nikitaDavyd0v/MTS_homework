public class ArrayUtils {
    public static <T> int findFirst(T[] array, T element) {
        // Проверка на null
        if (array == null) {
            return -1;
        }

        for (int i = 0; i < array.length; i++) {
            // Если элемент null — сравниваем через ==
            if (element == null) {
                if (array[i] == null) {
                    return i;
                }
            } else {
                // Иначе — через equals()
                if (element.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    // Пример использования
    public static void main(String[] args) {
        final String[] names = {"Alice", "Bob", "Charlie"};
        final int index = ArrayUtils.findFirst(names, "Bob");
        System.out.println(index); // Ожидаем: 1
    }
}

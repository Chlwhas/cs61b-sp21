package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        T result = get(0);
        for (T t : this) {
            if (c.compare(t, result) > 0) {
                result = t;
            }
        }
        return result;
    }
}

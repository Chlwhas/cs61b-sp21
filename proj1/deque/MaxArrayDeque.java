package deque;

import java.util.Comparator;

public class MaxArrayDeque<E> extends ArrayDeque<E>{
    private final Comparator<E> comparator;
    public MaxArrayDeque(Comparator<E> comparator){
        this.comparator = comparator;
    }

    public E max(){
        return max(comparator);
    }

    public E max(Comparator<E> c){
        if (size() == 0) return null;
        E result = get(0);
        for (E e : this) {
            if (c.compare(e, result) > 0) {
                result = e;
            }
        }
        return result;
    }
}

package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> , Iterable<T> {
    private Object[] items;
    private int size;
    private static final int INITIAL_CAPACITY = 8;
    private static final double MIN_USAGE_RATE = 0.25;
    private int front;
    private int back;

    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        front = 0;
        back = 0;
    }
    @Override
    public void addFirst(T t) {
        if (size == items.length) {
            items = resize(2 * size);
        }
        front = ((front == 0) ? items.length - 1 : front - 1);
        items[front] = t;
        size += 1;
    }
    @Override
    public void addLast(T t) {
        if (size == items.length) {
            items = resize(2 * size);
        }
        items[back] = t;
        back = (back + 1) % items.length;
        size += 1;
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (items.length > 16 && (double) size / items.length < MIN_USAGE_RATE) {
            items = resize(items.length / 2);
        }
        int actualIndex = front;
        T item = (T) items[actualIndex];
        items[actualIndex] = null;
        front = (actualIndex + 1) % items.length;
        size -= 1;
        return item;
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length > 16 && (double) size / items.length < MIN_USAGE_RATE) {
            items = resize(items.length / 2);
        }
        int actualIndex = (back == 0 ? items.length - 1 : back - 1);
        T item = (T) items[actualIndex];
        items[actualIndex] = null;
        back = actualIndex;
        size -= 1;
        return item;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int actualIndex = (front + i) % items.length;
            System.out.print(items[actualIndex] + " ");
        }
        System.out.println();
    }
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int actualIndex = (front + index) % items.length;
        return (T) items[actualIndex];
    }

    private Object[] resize(int capacity) {
        Object[] newArray = new Object[capacity];
        for (int i = 0; i < size; i++) {
            int actualIndex = (front + i) % items.length;
            newArray[i] = items[actualIndex];
        }
        front = 0;
        back = size;
        return newArray;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i = front;
            int count = 0;
            @Override
            public boolean hasNext() {
                return count != size;
            }

            @Override
            public T next() {
                T item = (T) items[i];
                i = (i + 1) % items.length;
                count += 1;
                return item;
            }
        };
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof ArrayDeque || o instanceof LinkedListDeque)) {
            return false;
        }
        if (o instanceof ArrayDeque) {
            ArrayDeque<?> otherDeque = (ArrayDeque<?>) o;
            if (otherDeque.size() != size) {
                return false;
            }
            Iterator<T> iter1 = iterator();
            Iterator<?> iter2 = otherDeque.iterator();
            while (iter1.hasNext() && iter2.hasNext()) {
                Object item1 = iter1.next();
                Object item2 = iter2.next();
                if (!item1.equals(item2)) {
                    return false;
                }
            }
            return true;

        }else {
            LinkedListDeque<?> otherDeque = (LinkedListDeque<?>) o;
            if (otherDeque.size() != size) {
                return false;
            }
            Iterator<T> iter1 = iterator();
            Iterator<?> iter2 = otherDeque.iterator();
            while (iter1.hasNext() && iter2.hasNext()) {
                Object item1 = iter1.next();
                Object item2 = iter2.next();
                if (!item1.equals(item2)) {
                    return false;
                }
            }
            return true;
        }
    }
}

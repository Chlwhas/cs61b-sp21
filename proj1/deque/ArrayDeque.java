package deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E>, Iterable<E>{
    private Object[] items;
    private int size;
    private static final int INITIAL_CAPACITY = 8;
    private static final double MIN_USAGE_RATE = 0.25;
    private int front;
    private int back;

    public ArrayDeque(){
        items = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
        front = 0;
        back = 0;
    }
    @Override
    public void addFirst(E e){
        if (size == items.length) {
            items = resize(2 * size);
        }
        front = ((front == 0) ? items.length - 1 : front - 1);
        items[front] = e;
        size += 1;
    }
    @Override
    public void addLast(E e){
        if (size == items.length) {
            items = resize(2 * size);
        }
        items[back] = e;
        back = (back + 1) % items.length;
        size += 1;
    }
    @Override
    public E removeFirst(){
        if (size == 0) return null;
        if (items.length > 16 && (double) size / items.length < MIN_USAGE_RATE) {
            items = resize(items.length / 2);
        }
        int actualIndex = front;
        E item = (E) items[actualIndex];
        items[actualIndex] = null;
        front = (actualIndex + 1) % items.length;
        size -= 1;
        return item;
    }
    @Override
    public E removeLast(){
        if (size == 0) return null;
        if (items.length > 16 && (double) size / items.length < MIN_USAGE_RATE) {
            items = resize(items.length / 2);
        }
        int actualIndex = (back == 0 ? size - 1 : back - 1);
        E item = (E) items[actualIndex];
        items[actualIndex] = null;
        back = actualIndex;
        size -= 1;
        return item;
    }
    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque(){
        for (int i = 0; i < size; i++) {
            int actualIndex = (front + i) % items.length;
            System.out.print(items[actualIndex] + " ");
        }
        System.out.println();
    }
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int actualIndex = (front + index) % items.length;
        return (E) items[actualIndex];
    }

    private Object[] resize(int capacity){
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
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int i = front;
            int count = 0;
            @Override
            public boolean hasNext() {
                return count != size;
            }

            @Override
            public E next() {
                E item = (E) items[i];
                i = (i + 1) % items.length;
                count += 1;
                return item;
            }
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ArrayDeque)) {
            return false;
        }

        ArrayDeque<?> otherDeque = (ArrayDeque<?>) obj;

        if (otherDeque.size() != size()) {
            return false;
        }

        Iterator<E> iter1 = iterator();
        Iterator<?> iter2 = otherDeque.iterator();

        while (iter1.hasNext() && iter2.hasNext()) {
            if (!iter1.next().equals(iter2.next())) {
                return false;
            }
        }

        return true;

    }
}

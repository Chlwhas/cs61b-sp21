package deque;

import java.util.Collection;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class Node {
        private final T item;
        private Node next;
        private Node prev;

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;

    // sentinel
    private final Node first = new Node(null, null, null);
    private final Node last = new Node(null, null, null);

    public LinkedListDeque() {
        size = 0;
        first.next = last;
        last.prev = first;
    }

    @Override
    public void addFirst(T t) {
        Node node = new Node(t, null, null);
        node.next = first.next;
        first.next = node;
        node.next.prev = node;
        node.prev = first;
        size += 1;
    }

    @Override
    public void addLast(T t) {
        Node node = new Node(t, null, null);
        last.prev.next = node;
        node.prev = last.prev;
        last.prev = node;
        node.next = last;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        Node pointer = first.next;
        while (pointer != last) {
            System.out.print(pointer.item + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = first.next.item;
        first.next = first.next.next;
        first.next.prev = first;
        size -= 1;
        return item;
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = last.prev.item;
        last.prev = last.prev.prev;
        last.prev.next = last;
        size -= 1;
        return item;
    }
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursive(index, first.next);
    }

    private T getRecursive(int index, Node node) {
        if (index == 0) {
            return node.item;
        }
        return getRecursive(index - 1, node.next);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node pointer = first.next;
            @Override
            public boolean hasNext() {
                return pointer != last;
            }
            @Override
            public T next() {
                T i = pointer.item;
                pointer = pointer.next;
                return i;
            }
        };
    }
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o instanceof ArrayDeque<?>) {
            return isEqualDeque((ArrayDeque<?>) o);
        } else if (o instanceof LinkedListDeque<?>) {
            return isEqualDeque((LinkedListDeque<?>) o);
        } else {
            return false;
        }
    }

    private boolean isEqualDeque(Iterable<?> otherDeque) {
        // Assuming you have a size() method available for your deque.
        // Note: You'd need to cast otherDeque to its specific type if
        // you're calling specific methods not present in the Iterable interface.
        if (((Collection<?>) otherDeque).size() != size) {
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

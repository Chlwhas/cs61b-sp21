package deque;

import java.util.Iterator;

public class LinkedListDeque<E> implements Deque<E>, Iterable<E>{

    private class Node {
        private final E item;
        private Node next;
        private Node prev;

        public Node(E item, Node prev, Node next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;

    // sentinel
    private final Node first = new Node(null, null, null);
    private final Node last = new Node(null, null, null);

    public LinkedListDeque(){
        size = 0;
        first.next = last;
        last.prev = first;
    }

    @Override
    public void addFirst(E e) {
        Node node = new Node(e, null, null);
        node.next = first.next;
        first.next = node;
        node.next.prev = node;
        node.prev = first;
        size += 1;
    }

    @Override
    public void addLast(E e) {
        Node node = new Node(e, null, null);
        last.prev.next = node;
        node.prev = last.prev;
        last.prev = node;
        node.next = last;
        size += 1;
    }

    @Override
    public int size(){
        return size;
    }
    @Override
    public void printDeque(){
        Node pointer = first.next;
        while (pointer != last) {
            System.out.print(pointer.item + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }
    @Override
    public E removeFirst(){
        if (size == 0) {
            return null;
        }
        E item = first.next.item;
        first.next = first.next.next;
        first.next.prev = first;
        size -= 1;
        return item;
    }
    @Override
    public E removeLast(){
        if (size == 0) {
            return null;
        }
        E item = last.prev.item;
        last.prev = last.prev.prev;
        last.prev.next = last;
        size -= 1;
        return item;
    }
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    public E getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursive(index, first.next);
    }

    private E getRecursive(int index, Node node){
        if (index == 0) return node.item;
        return getRecursive(index - 1, node.next);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node pointer = first.next;
            @Override
            public boolean hasNext() {
                return pointer != last;
            }
            @Override
            public E next() {
                E i = pointer.item;
                pointer = pointer.next;
                return i;
            }
        };
    }
    @Override
    public boolean equals(Object o){

        if (!(o instanceof LinkedListDeque)) {
            return false;
        }

        LinkedListDeque<?> otherDeque = (LinkedListDeque<?>) o;
        if (otherDeque.size() != size) {
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

package deque;

public interface Deque <E> {
    void addFirst(E e);
    void addLast(E e);
    int size();
    void printDeque();
    E removeFirst();
    E removeLast();
    E get(int index);

    default boolean isEmpty(){
        return size() == 0;
    }
}

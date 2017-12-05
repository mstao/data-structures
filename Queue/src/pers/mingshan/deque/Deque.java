package pers.mingshan.deque;

/**
 * 双向队列
 * @author mingshan
 *
 * @param <E>
 */
public interface Deque<E> {
    void addFirst(E e);
    void addLast(E e);
    boolean offerFirst(E e);
    boolean offerLast(E e);
    E removeFirst();
    E removeLast();
    E pollFirst();
    E pollLast();
    E getFirst();
    E getLast();
    E peekFirst();
    E peekLast();
}

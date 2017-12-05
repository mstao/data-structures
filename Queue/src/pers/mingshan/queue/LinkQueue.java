package pers.mingshan.queue;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 链式队列
 * @author mingshan
 *
 */
public class LinkQueue<E> implements Queue<E>, Serializable {
    private static final long serialVersionUID = -1134881933704209286L;
    private final AtomicInteger size = new AtomicInteger();
    private final int capacity;

    private class Node {
        private E data;
        private Node next;

        public Node(E data) {
            this.data = data;
        }
    }

    // 队列的头节点
    private Node head;
    // 队列的尾节点
    private Node tail;

    public LinkQueue() {
        this(Integer.MAX_VALUE);
    }

    public LinkQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException();
        this.capacity = capacity;
        tail = head = new Node(null);
    }

    public LinkQueue(E element) {
        this(Integer.MAX_VALUE);
        // 初始Node，只有一个节点
        head = new Node(element);
        tail = head;
        size.incrementAndGet();
    }

    @Override
    public boolean add(E e) {
        if (offer(e))
            return true;
        else
            throw new IllegalStateException("Queue full");
    }

    @Override
    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        if (size.get() == capacity)
            return false;
        if (head == null) {
            head = new Node(e);
            tail = head;
        } else {
            Node newNode = new Node(e);
            tail.next = newNode;
            tail = newNode;
        }

        size.incrementAndGet();
        return true;
    }

    @Override
    public E poll() {
        if (!isEmpty()) {
            Node node = head;
            head = head.next;
            size.decrementAndGet();
            return node.data;
        }

        return null;
    }

    @Override
    public E peek() {
        if (!isEmpty()) {
            return head.data;
        }

        return null;
    }

    @Override
    public boolean isEmpty() {
        return size.get() == 0;
    }

    @Override
    public int size() {
        return size.get();
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size.set(0);
    }

    public String toString() {
        //链队列为空链队列时  
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = head; current != null; current = current.next) {
                sb.append(current.data.toString() + ", ");
            }
            int len = sb.length();  
            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}

package pers.mingshan.queue;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * 循环队列
 * 
 * @author mingshan
 *
 */
public class CircularQueue<E> implements Queue<E>, Serializable {
    private static final long serialVersionUID = 1873273966471362494L;

    // 队列内部数组默认容量
    private static final int DEFAULT_CAPACITY = 8;

    // 队列内部数组的容量
    private int capacity;

    // 保存元素的数组
    private Object[] elements;

    // 指向队列头部
    private int head;

    // 指向队列尾部
    private int tail;

    /**
     * 默认构造函数初始化
     */
    public CircularQueue() {
        capacity =  DEFAULT_CAPACITY;
        elements = new Object[capacity];
    }

    /**
     * 指定队列内部数组容量进行初始化
     * @param capacity 指定容量
     */
    public CircularQueue(int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
    }

    /**
     * 指定队列的第一个元素和容量进行初始化
     * @param e 队列的第一个元素
     * @param capacity 队列内部数组容量
     */
    public CircularQueue(E e, int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
        elements[0] = e;
        tail++;
    }

    @Override
    public boolean add(E e) {
        Objects.requireNonNull(e);
        // 判断队列是否满了
        if ((tail + 1) % capacity == head) {
            throw new IllegalStateException("Queue full");
        }

        elements[tail] = e;
        tail = (tail + 1) % capacity;
        return true;
    }

    @Override
    public boolean offer(E e) {
        Objects.requireNonNull(e);
        // 判断队列是否满了
        if ((tail + 1) % capacity == head) {
            return false;
        }

        elements[tail] = e;
        tail = (tail + 1) % capacity;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E poll() {
        // 如果 head == tail, 队列就为null
        if (isEmpty()) {
            return null;
        }

        E value = (E) elements[head];
        head = (head + 1) % capacity;
        return value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        // 如果 head == tail, 队列就为null
        if (isEmpty()) {
            return null;
        }

        return (E) elements[head];
    }

    @Override
    public boolean isEmpty() {
        // 如果 head == tail, 队列就为null
        if (head == tail) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public void clear() {
        // 将底层数组所有元素赋为null  
        Arrays.fill(elements, null);
        head = 0;
        tail = 0;
    }

    /**
     * 打印
     */
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = head; i != tail; i = (i + 1) % capacity) {
              sb.append(elements[i].toString() + ", ");
            }
            int len = sb.length();  
            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}

package pers.mingshan.queue;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 顺序队列
 * @author mingshan
 *
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E>, Serializable {
    private static final long serialVersionUID = -5343093498654305994L;

    // 队列内部数组默认容量
    private static final int DEFAULT_SIZE = 10;

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
    public ArrayQueue() {
        capacity = DEFAULT_SIZE;
        elements = new Object[capacity];
    }

    /**
     * 指定队列内部数组容量进行初始化
     * @param capacity 指定容量
     */
    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
    }

    /**
     * 指定队列的第一个元素进行初始化
     * @param e 队列的第一个元素
     */
    public ArrayQueue(E e) {
        this.capacity = DEFAULT_SIZE;
        elements = new Object[capacity];
        elements[0] = e;
        tail++;
    }

    /**
     * 指定队列的第一个元素和容量进行初始化
     * @param e 队列的第一个元素
     * @param capacity 队列内部数组容量
     */
    public ArrayQueue(E e, int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
        elements[0] = e;
        tail++;
    }

    @Override
    public boolean add(E e) {
        if (e != null) {
            // 获取当前的数组的长度
            int oldLength = elements.length;
            // 如果原来数组的长度小于当前需要的长度，那么直接抛异常IllegalStateException
            if (oldLength < tail + 1) {
                throw new IllegalStateException("Queue full");
            } else {
                elements[tail++] = e;
            }
        } else {
            throw new NullPointerException();
        }

        return true;
    }

    @Override
    public boolean offer(E e) {
        if (e != null) {
            // 获取当前的数组的长度
            int oldLength = elements.length;
            // 如果原来数组的长度小于当前需要的长度，那么直接抛异常IllegalStateException
            if (oldLength < tail + 1) {
                return false;
            } else {
                elements[tail++] = e;
            }
        } else {
            throw new NullPointerException();
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E poll() {
        if (!isEmpty()) {
            E value = (E) elements[head];
            // 移除头部元素
            elements[head] = null;
            head++;
            return value;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {
        if (!isEmpty()) {
            return (E) elements[head];
        }

        return null;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public int size() {
        return tail - head;
    }

    @Override
    public void clear() {
        //将底层数组所有元素赋为null  
        Arrays.fill(elements, null);
        head = 0;
        tail = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = head; i < tail; i++) {  
              sb.append(elements[i].toString() + ", ");
            }
            int len = sb.length();  
            return sb.delete(len - 2, len).append("]").toString();  
        }
    }
}

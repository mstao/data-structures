package pers.mingshan.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Stack(栈) 后进先出
 * 可以存null
 * @author mingshan
 *
 */
public class Stack<E> {
    // 存放栈内元素的数组，默认大小为10
    private Object[] elementData;
    // 元素的数量
    private int elementCount;
    // 指定要增加的容量大小
    private int capacityIncrement;

    /**
     * 通过传入自定义的值来初始化数组
     * @param initialCapacity 数组容初始量
     * @param capacityIncrement 扩容增加的容量
     */
    public Stack(int initialCapacity, int capacityIncrement) {
        super();
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        this.elementData = new Object[initialCapacity];
        this.capacityIncrement = capacityIncrement;
    }

    /**
     * 通过传入自定义的值来初始化数组
     * @param initialCapacity 数组初始容量
     */
    public Stack(int initialCapacity) {
        this(initialCapacity, 0);
    }

    /**
     * 构造方法初始化数组容量
     */
    public Stack() {
        this(10);
    }

    /**
     * 入栈
     * @param data
     * @return 入栈的数据
     */
    public E push(E data) {
        addElement(data);
        return data;
    }

    /**
     * 出栈，移除栈顶的元素
     * @return 被移除的元素
     */
    public E pop() {
        E obj = peek();
        if (size() > 0) {
            // 移除栈顶元素
            elementData[elementCount - 1] = null;
            elementCount--;
        }
        return obj;
    }

    /**
     * 获取栈顶的元素，但不移除
     * @return 栈顶的元素
     */
    @SuppressWarnings("unchecked")
    public E peek() {
        int len = size();

        if (len == 0)
            throw new EmptyStackException();
        E obj = (E) elementData[elementCount - 1];
        return obj;
    }

    /**
     * 判断栈是否为空
     * @return 如果栈为空，返回{@true}, 否则返回{@false}
     */
    public boolean empty() {
        return size() == 0;
    }

    /**
     * 返回对象在堆栈中的位置，以 0 为基数。
     * @param element
     * @return 元素第一次出现的位置，找不到返回-1
     */
    public int search(Object element) {
        int z = elementCount - 1;
        if (element == null) {
            for (int i = z; i > 0; i--) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = z; i > 0; i--) {
                if (element.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 获取栈元素数量
     * @return 栈元素的数组
     */
    public int size() {
        return elementCount;
    }

    /**
     * 向栈顶添加元素
     * @param obj
     */
    private void addElement(E obj) {
        ensureCapacity(elementCount + 1);
        elementData[elementCount++] = obj;
    }

    /**
     * 确保栈容量，扩容
     * @param minCapacity 
     */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        // 判断是否需要扩容
        if (oldCapacity < minCapacity) {
            // 指定要扩大多少，否则就扩容2倍
            int newCapacity = oldCapacity + (this.capacityIncrement > 0 
                    ? this.capacityIncrement : oldCapacity);
            // 将原数组的容量拷贝到扩容后的数组
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    @Override
    public String toString() {
        if (empty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < elementCount; i++) {
                if (elementData[i] == null) {
                    sb.append("null" + ", ");
                } else {
                    sb.append(elementData[i].toString() + ", ");
                }
            }
            int len = sb.length();  
            return sb.delete(len - 2, len).append("]").toString();  
        }
    }
}

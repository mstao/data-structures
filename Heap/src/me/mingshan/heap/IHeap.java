package me.mingshan.heap;

/**
 * 堆 接口
 * 
 * @author mingshan
 *
 */
public interface IHeap<T extends Comparable<T>> {

    /**
     * 将一个值添加到堆中
     * 
     * @param value 要添加的值
     */
    void add(T value);

    /**
     * 获取Head value
     * 
     * @return head
     */
    T get(int index);

    /**
     * 移除元素
     * 
     * @param value
     * @return 移除的元素
     */
    T remove();

    /**
     * 清空堆
     */
    void clear();

    /**
     * 判断堆中是否包含某个元素
     * 
     * @param value
     * @return 如果包含，返回{@code true}，否则返回{@code false}
     */
    boolean contains(T value);

    /**
     * 获取堆的大小
     * 
     * @return 堆的大小
     */
    int size();

    /**
     * 将堆中的元素转为集合
     * 
     * @return 集合
     */
    java.util.Collection<T> toCollection();
}

package me.mingshan.linkedlist;

/**
 * 链表接口
 * @author mingshan
 *
 * @param <E>
 */
public interface LinkedList<E> {

    /**
     * 根据索引获取节点的值
     * @param index 传入的索引值， 从0开始
     * @return 节点的值
     */
    E get(int index);

    /**
     * 设置某个结点的的值
     * @param index 传入的索引值， 从0开始
     * @param data 要插入的元素
     * @return 旧的节点的值
     */
    E set(int index, E data);

    /**
     * 根据index添加结点
     * @param index 传入的索引值， 从0开始
     * @param data 要插入的元素
     * @return 插入是否成功
     */
    void add(int index, E data);

    /**
     * 添加结点
     * @param data
     * @return 插入是否成功
     */
    boolean add(E data);

    /**
     * 根据index移除结点
     * @param index 传入的索引值， 从0开始
     * @return 移除成功返回该索引处的旧值
     */
    E remove(int index);

    /**
     * 根据data移除结点
     * @param data
     * @return 是否移除成功
     */
    boolean removeAll(E data);

    /**
     * 清空链表
     */
    void clear();

    /**
     * 是否包含data结点
     * @param data
     * @return 包含返回{@code true}, 不包含返回 {@code false}
     */
    boolean contains(E data);

    
    /**
     * 获取链表长度
     * @return 链表长度
     */
    int length();

    /**
     * 判断链表是否为空
     * @return 链表为空返回{@code true}, 不为空返回 {@code false}
     */
    boolean isEmpty();

    /**
     * 链表反转
     */
    void reverse();
}

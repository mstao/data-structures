package me.mingshan.queue;

/**
 * 队列接口
 * @author mingshan
 *
 * @param <E>
 */
public interface Queue<E> {
    /**
     * 添加元素， 如果没有可用的空间，抛出IllegalStateException异常
     * @param e 将要添加的元素
     * @return
     */
    boolean add(E e) throws IllegalStateException;

    /**
     * 添加元素。成功时返回 true，如果当前没有可用的空间，则返回 false，不会抛异常
     * @param e 将要添加的元素
     * @return
     */
    boolean offer(E e);

    /**
     * 获取并移除此队列的头部,如果队列为空，则返回null
     * @return 头部元素
     */
    E poll();

    /**
     * 获取队列头部元素, 不移除头部元素
     * @return 头部元素
     */
    E peek();

    /**
     * 判断队列是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 获取队列的长度
     * @return 队列的长度
     */
    int size();

    /**
     * 清空队列
     */
    void clear();
}

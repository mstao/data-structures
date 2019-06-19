package me.mingshan.tree;

/**
 * 树的接口
 * @author mingshan
 *
 */
public interface Tree<E> {

    /**
     * 插入操作
     * @param value
     * @return 插入成功 ，返回 {@true}，否则返回{@false}
     */
    boolean add(E value);

    /**
     * 移除
     * @param value
     * @return 移除的元素
     */
    E remove(E value);

    /**
     * 清空二叉树
     */
    void clear();

    /**
     * 判断二叉树中是否有此元素
     * @param value
     * @return 如果包含，返回{@true}，否则返回{@false}
     */
    boolean contains(E value);

    /**
     * 获取二叉树中结点的数量
     * @return 二叉树中结点的数量
     */
    int size();
}

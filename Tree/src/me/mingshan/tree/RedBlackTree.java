package me.mingshan.tree;

/**
 * 红黑树实现
 * <br>
 * 
 * 红黑树的特性：
 * <ul>
 *  <li>每个节点或者是黑色，或者是红色。</li>
 *  <li>根节点是黑色。</li>
 *  <li>每个叶子节点是黑色。 [注意：这里叶子节点，是指为空的叶子节点！]</li>
 *  <li>每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)</li>
 *  <li>从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点。</li>
 * </ul>
 * 
 * @author mingshan
 *
 * @param <E>
 */
public class RedBlackTree<E extends Comparable<E>> implements Tree<E> {

    @Override
    public boolean add(E value) {
        return false;
    }

    @Override
    public E remove(E value) {
        return null;
    }

    @Override
    public void clear() {
        
    }

    @Override
    public boolean contains(E value) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

}

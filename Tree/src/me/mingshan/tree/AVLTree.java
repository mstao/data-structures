package me.mingshan.tree;

/**
 * 平衡二叉树:<br/>
 * 它是一 棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 *
 * @author mingshan
 */
public class AVLTree<E> implements Tree<E> {
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

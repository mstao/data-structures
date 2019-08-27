package me.mingshan.tree;

/**
 * 平衡二叉树:<br/>
 * 它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 *
 * @author mingshan
 */
public class AVLTree<E extends Comparable<E>> implements Tree<E> {
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

    private static class AVLNode<E extends Comparable<E>> {
        E item;
        AVLNode<E> left;
        AVLNode<E> right;
        int height = 1;

        protected AVLNode (E item) {
            this.item = item;
        }

        /**
         * 获取平衡因子，计算方式：左树的高度减去右树的高度
         *
         * @return 平衡因子bf
         */
        protected int getBalanceFactor() {
            int leftHeight = 0;
            if (left != null) {
                leftHeight = left.height;
            }
            int rightHeight = 0;
            if (right != null) {
                rightHeight = right.height;
            }
            return leftHeight - rightHeight;
        }

        @Override
        public String toString() {
            return "item=" + item + " left="
                + ((left != null) ? left.item : "NULL") + " right=" + ((right != null) ? right.item : "NULL");
        }
    }

}

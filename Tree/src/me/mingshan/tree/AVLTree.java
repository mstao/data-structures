package me.mingshan.tree;

/**
 * 平衡二叉树:<br/>
 * 它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一棵平衡二叉树。
 *
 * @author mingshan
 */
public class AVLTree<E extends Comparable<E>> implements Tree<E> {
    // AVL树根结点
    private AVLNode<E> root;
    // AVL树节点数量
    private int size;

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
        root = null;
        size = 0;
    }

    @Override
    public boolean contains(E value) {
        // 先序遍历二叉树
        AVLNode<E> node = root;
        if (root.item.compareTo(value) == 0) {
            return true;
        }

        while (node != null) {
            // 如果当前值比父节点的值小
            if (node.item.compareTo(value) > 0) {
                // 此时应该从父节点的左子树进行搜索
                if (node.left != null
                    && (node.left.item.compareTo(value) == 0)) {
                    return true;
                }
                node = node.left;
            } else {
                // 如果当前结点的值比父结点的值大，说明应该从父节点的右子树搜索
                // 并且新结点作为叶子结点，其父节点的右子结点应为null
                if (node.right != null
                    && (node.right.item.compareTo(value) == 0)) {
                    return true;
                }
                node = node.right;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 左旋
     *
     * @param node
     */
    private void rotateLeft(AVLNode<E> node) {

    }

    /**
     * 右旋
     *
     * @param node
     */
    private void rotateRight(AVLNode<E> node) {

    }

    /**
     * AVL树的失衡类型枚举，包括: LL, LR, RL, RR
     */
    private enum Balance {
        LEFT_LEFT, LEFT_RIGHT, RIGHT_LEFT, RIGHT_RIGHT
    }

    /**
     * AVL树的节点
     *
     * @param <E> 节点的值
     */
    private static class AVLNode<E extends Comparable<E>> {
        E item;
        AVLNode<E> left;
        AVLNode<E> right;
        int height = 1;

        AVLNode(E item) {
            this(item, null, null);
        }

        AVLNode(E item, AVLNode<E> left, AVLNode<E> right) {
          this.item = item;
          this.left = left;
          this.right = right;
        }

        /**
         * 获取平衡因子，计算方式：左树的高度减去右树的高度
         *
         * @return 平衡因子bf
         */
        int getBalanceFactor() {
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

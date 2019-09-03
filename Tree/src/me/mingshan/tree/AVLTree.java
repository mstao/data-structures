package me.mingshan.tree;

import java.util.Objects;

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

    /**
     * AVL树的失衡类型枚举，包括: LL, LR, RL, RR
     */
    private enum Balance {
        LEFT_LEFT, LEFT_RIGHT, RIGHT_LEFT, RIGHT_RIGHT
    }


    @Override
    public boolean add(E value) {
        AVLNode<E> node = addNode(value);
        return node != null;
    }

    /**
     * 添加节点
     *
     * @param value 节点值
     * @return 新添加的节点
     */
    private AVLNode<E> addNode(E value) {
        // 生成新结点
        AVLNode<E> newNode = new AVLNode<>(value);
        // 如果根结点不存在
        if (root == null) {
            root = newNode;
            size++;
            return newNode;
        }

        AVLNode<E> node = root;
        // 按照先序进行遍历二叉树
        while (node != null) {
            // 如果新结点的值比父节点的值小
            if (node.item.compareTo(newNode.item) > 0) {
                // 此时应该从父节点的左子树进行搜索
                // 并且新结点作为叶子结点，其父节点的左子结点应为null
                if (node.left == null) {
                    node.left = newNode;
                    size++;
                    return newNode;
                }
                node = node.left;
            } else {
                // 如果当前结点的值比父结点的值大，说明应该从父节点的右子树搜索
                // 并且新结点作为叶子结点，其父节点的右子结点应为null
                if (node.right == null) {
                    node.right = newNode;
                    size++;
                    return newNode;
                }
                node = node.right;
            }
        }

        // 更新当前节点的高度
        newNode.updateHeight();
        rebalanced(newNode);
        return newNode;
    }

    /**
     * 重新平衡AVL树
     *
     * @param node 当前节点
     */
    private void rebalanced(AVLNode<E> node) {
        Objects.requireNonNull(node, "node not be null");

        // 获取节点的平衡因子
        int balanceFactor = node.getBalanceFactor();
        // 此时需要分四种情况来考虑

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

        /**
         * 判断当前节点是否为叶子结点
         *
         * @return true，是叶子结点；false，不是叶子结点
         */
        boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * 更新当前节点的高度
         *
         * @return
         */
        int updateHeight() {
            int leftHeight = 0, rightHeight = 0;

            if (left != null)
                leftHeight = left.height;
            if (right != null)
                rightHeight = right.height;

            height = 1 + Math.max(leftHeight, rightHeight);

            return height;
        }

        @Override
        public String toString() {
            return "item=" + item + " left="
                + ((left != null) ? left.item : "NULL") + " right=" + ((right != null) ? right.item : "NULL");
        }
    }

}

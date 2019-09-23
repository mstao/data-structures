package me.mingshan.tree;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;
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

    public void initRoot(AVLNode<E> root) {
        this.root = root;
    }

    @Override
    public boolean add(@NotNull E value) {
        AVLNode<E> node = addNode(value);
        return node != null;
    }

    /**
     * 添加节点
     *
     * @param value 节点值
     * @return 新添加的节点
     */
    private AVLNode<E> addNode(@NotNull E value) {
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
    private void rebalanced(@NotNull AVLNode<E> node) {
        Objects.requireNonNull(node, "node must be not null");

        // 获取节点的平衡因子
        int balanceFactor = node.getBalanceFactor();

        // 平衡因子大于1或者小于-1，表示当前树失衡了，需要进行平衡处理
        if (balanceFactor > 1 && balanceFactor < -1) {
            Balance balance = null;
            AVLNode<E> childNode = null;

            // 右子树比左子树高，左旋操作
            if (balanceFactor < 0) {
                // 获取当前节点的右子节点
                childNode = node.right;
                // 如果右子节点的平衡因子小于0，此时对应RR
                if (childNode.getBalanceFactor() < 0) {
                    balance = Balance.RIGHT_RIGHT;
                } else if (childNode.getBalanceFactor() > 0) {
                    // 如果右子节点的平衡因子大于0，说明此时出现左边子树比右边高的情况，对应RL
                    balance = Balance.RIGHT_LEFT;
                }
            } else {
                // 获取当前节点的左子节点
                childNode = node.left;
                if (childNode.getBalanceFactor() > 0) {
                    balance = Balance.LEFT_LEFT;
                } else if (childNode.getBalanceFactor() < 0) {
                    balance = Balance.LEFT_RIGHT;
                }
            }

            // 此时需要分四种情况来考虑
            // 1. 平衡因子大于1，说明左子树比右子树高，需要进行右旋操作
            if (Balance.LEFT_LEFT.equals(balance)) {
                rotateRight(node);
            } else if (Balance.RIGHT_RIGHT.equals(balance)) {
                // 2. 平衡因子小于-1，说明右子树比左子树高，需要进行左旋操作
                rotateLeft(node);
            } else if (Balance.RIGHT_LEFT.equals(balance)) {
                rotateRight(childNode);
                rotateLeft(node);
            } else if (Balance.LEFT_RIGHT.equals(balance)) {
                //
                rotateLeft(childNode);
                rotateRight(node);
            }
        }
    }

    /**
     * 左旋，失衡情况对应RR
     *
     * @param node 当前子树根结点
     * @return 旋转后的根结点
     */
    public AVLNode<E> rotateLeft(@NotNull AVLNode<E> node) {
        Objects.requireNonNull(node, "node must be not null");

        // 暂存当前节点
        AVLNode<E> originNode = node;
        // 当前节点的右子结点
        AVLNode<E> rightNode = node.right;
        if (rightNode == null)
            return node;
        // 以当前根节点的右子树根结点作为根结点
        originNode.right = rightNode.left;
        // 替换根结点
        node = rightNode;
        node.left = originNode;

        return node;
    }

    /**
     * 右旋，失衡情况对应LL
     *
     * @param node 当前子树根结点
     * @return 旋转后的根结点
     */
    public AVLNode<E> rotateRight(AVLNode<E> node) {
        Objects.requireNonNull(node, "node must be not null");

        // 暂存当前节点
        AVLNode<E> originNode = node;
        // 当前节点的左子节点
        AVLNode<E> leftNode = node.left;
        if (leftNode == null)
            return node;
        originNode.left = leftNode.right;
        // 替换根结点
        node = leftNode;
        node.right = originNode;

        return node;
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
    public static class AVLNode<E extends Comparable<E>> {
        E item;
        AVLNode<E> left;
        AVLNode<E> right;
        int height = 1;

        public AVLNode(E item) {
            this(item, null, null);
        }

        public AVLNode(E item, AVLNode<E> left, AVLNode<E> right) {
          this.item = item;
          this.left = left;
          this.right = right;
        }

        public void setLeft(AVLNode<E> left) {
            this.left = left;
        }

        public void setRight(AVLNode<E> right) {
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


    @Override
    public String toString() {
        return TreePrinter.getString(this);
    }

    public static class TreePrinter {

        public static <E extends Comparable<E>> String getString(AVLTree<E> tree) {
            if (tree.root == null)
                return "Tree has no nodes.";
            return getString(tree.root, "", true);
        }

        private static <E extends Comparable<E>> String getString(AVLNode<E> node, String prefix, boolean isTail) {
            StringBuilder builder = new StringBuilder();

            builder.append(prefix).append(isTail ? "└── " : "├── ").append(node.item).append("\n");
            List<AVLNode<E>> children = null;
            if (node.left != null || node.right != null) {
                children = new ArrayList<AVLNode<E>>(2);
                if (node.left != null)
                    children.add(node.left);
                if (node.right != null)
                    children.add(node.right);
            }
            if (children != null) {
                for (int i = 0; i < children.size() - 1; i++) {
                    builder.append(getString(children.get(i), prefix + (isTail ? "    " : "│   "), false));
                }
                if (children.size() >= 1) {
                    builder.append(getString(children.get(children.size() - 1), prefix + (isTail ? "    " : "│   "), true));
                }
            }

            return builder.toString();
        }
    }
}

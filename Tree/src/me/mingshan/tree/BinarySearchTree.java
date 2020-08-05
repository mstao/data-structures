package me.mingshan.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树
 * 
 * 二叉搜索树，一个结点的左结点的值必须小于此结点的值，右结点的值大于或等于此结点的值
 * 下面是搜索步骤：
 * 
 * 1. 如果x等于根节点，那么找到x，停止搜索 (终止条件)
 * 2. 如果x小于根节点，那么搜索左子树
 * 3. 如果x大于根节点，那么搜索右子树
 * 
 * @author mingshan
 *
 */
public class BinarySearchTree<E extends Comparable<E>> implements Tree<E> {
    // 根结点
    private Node<E> root;
    // 二叉树结点数量
    private int size;

    public static class Node<E extends Comparable<E>> {
        E item;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        public Node (Node<E> parent, E item) {
            this.parent = parent;
            this.item = item;
        }

        @Override
        public String toString() {
            return "item=" + item + " parent=" + ((parent != null) ? parent.item : "NULL") + " left="
                    + ((left != null) ? left.item : "NULL") + " right=" + ((right != null) ? right.item : "NULL");
        }
    }

    @Override
    public boolean add(E value) {
        Node<E> node = addNode(value);
        return (node != null);
    }

    private Node<E> addNode(E value) {
        // 生成新结点
        Node<E> newNode = new Node<E>(null, value);
        // 如果根结点不存在
        if (root == null) {
            root = newNode;
            size++;
            return newNode;
        }

        Node<E> node = root;
        // 按照先序进行遍历二叉树
        while (node != null) {
            // 如过新结点的值比父节点的值小
            if (node.item.compareTo(newNode.item) > 0) {
                // 此时应该从父节点的左子树进行搜索
                // 并且新结点作为叶子结点，其父节点的左子结点应为null
                if (node.left == null) {
                    node.left = newNode;
                    newNode.parent = node;
                    size++;
                    return newNode;
                }
                node = node.left;
            } else {
                // 如果当前结点的值比父结点的值大，说明应该从父节点的右子树搜索
                // 并且新结点作为叶子结点，其父节点的右子结点应为null
                if (node.right == null) {
                    node.right = newNode;
                    newNode.parent = node;
                    size++;
                    return newNode;
                }
                node = node.right;
            }
        }

        return newNode;
    }

    @Override
    public E remove(E value) {
        Node<E> node = this.removeValue(value);
        return (node != null ? node.item : null);
    }

    private Node<E> removeValue(E value) {
        Node<E> curr = this.getNode(value);
        if (curr != null) {
            curr = removeNode(curr);
        }

        return curr;
    }

    /**
     * 删除结点，分下面几种情况考虑
     * <ul>
     *   <li>要删除的结点为叶子结点，没有左右子节点</li>
     *   <li>要删除的结点只有左子结点(树)或者右子结点(树)</li>
     *   <li>要删除的结点左右结点(树)都有</li>
     * </ul>
     * @param nodeToRemoved
     * @return 删除的结点
     */
    private Node<E> removeNode(Node<E> nodeToRemoved) {
        // 判断当前节点是否为叶子结点（叶子结点的特点是没有子结点）
        // 直接删除叶子结点
        if (nodeToRemoved.left == null && nodeToRemoved.right == null) {
            // 判断该二叉树是否只有根结点一个结点
            if (nodeToRemoved == root) {
                root = null;
                return root;
            }
            // 如果二叉树不是只有根结点一个结点，那么当前要删除的结点一定有父结点
            Node<E> targetParent = nodeToRemoved.parent;
            // 判断当前结点是其父结点的左子结点还是右子结点
            if (targetParent.left.item.compareTo(nodeToRemoved.item) == 0) {
                // 如果当前结点是其父结点的左子结点
                targetParent.left = null;
            } else if (targetParent.right.item.compareTo(nodeToRemoved.item) == 0){
                // 如果当前结点是其父结点的右子结点
                targetParent.right = null;
            } else {
                // 此时二叉树有问题
                return null;
            }
        } else if (nodeToRemoved.left != null && nodeToRemoved.right != null) {
            // 要删除的结点左右结点(树)都有
            // 此时结点的左右子结点(树)都有，用其右子树中的最小值代替该节点上的值,删除其右子树上的最小值
            // 所以此时需要先找出其右子树的最小值
            Node<E> minNode = findMinNode(nodeToRemoved);
            // 将当前要删除结点的值替换为其子树的最小节点
            nodeToRemoved.item = minNode.item;
            // 删除找到的最小节点
            removeNode(minNode);
        } else {
            // 要删除的结点只有左子结点(树)或者右子结点(树)
            // 此时需要将该结点的子结点(树)指向该结点(树)的父结点
            Node<E> targetLeft = nodeToRemoved.left;
            Node<E> targetRight = nodeToRemoved.right;
            Node<E> targetParent = nodeToRemoved.parent;
            // 判断当前要删除的结点是其父结点的左结点还是右结点
            if (targetParent.left.item.compareTo(nodeToRemoved.item) == 0) {
                // 左
                if (targetLeft != null) {
                    targetParent.left = targetLeft;
                    targetLeft.parent = targetParent;
                    targetLeft = null;
                }
                if (targetRight != null) {
                    targetParent.left = targetRight;
                    targetRight.parent = targetParent;
                    targetRight = null;
                }
            } else if (targetParent.right.item.compareTo(nodeToRemoved.item) == 0) {
                // 右
                if (targetLeft != null) {
                    targetParent.right = targetLeft;
                    targetLeft.parent = targetParent;
                    targetLeft = null;
                }
                if (targetRight != null) {
                    targetParent.right = targetRight;
                    targetRight.parent = targetParent;
                    targetRight = null;
                }
            }
        }

        size--;
        return nodeToRemoved;
    }

    /**
     * 找到给定结点的子树的最小结点(值)
     * 此时应该考虑到如果为最小结点，那么该结点必然没有左子树(结点)，所以可以选择递归进行遍历
     * @param nodeToRemoved
     * @return 给定结点的子树的最小结点(值)
     */
    private Node<E> findMinNode(Node<E> nodeToRemoved) {
        if (nodeToRemoved == null) {
            return null;
        }
        if (nodeToRemoved.left == null) {
            return nodeToRemoved;
        }

        return findMinNode(nodeToRemoved.left);
    }

    /**
     * 通过传入的值来搜索结点
     * @param value 传入的值
     * @return 结点
     */
    private Node<E> getNode(E value) {
        Node<E> node = root;
        while (node != null && node.item != null) {
            if (node.item.compareTo(value) > 0) {
                node = node.left;
            } else if (node.item.compareTo(value) < 0) {
                node = node.right;
            } else {
                return node;
            }
        }

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
        Node<E> node = root;
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

    @Override
    public String toString() {
        return TreePrinter.getString(this);
    }

    protected static class TreePrinter {

        public static <T extends Comparable<T>> String getString(BinarySearchTree<T> tree) {
            if (tree.root == null) {
                return "Tree has no nodes.";
            }
            return getString(tree.root, "", true);
        }

        private static <E extends Comparable<E>> String getString(Node<E> node, String prefix, boolean isTail) {
            StringBuilder builder = new StringBuilder();

            if (node.parent != null) {
                String siteme = "left";
                if (node.equals(node.parent.right)) {
                    siteme = "right";
                }
                builder.append(prefix + (isTail ? "└── " : "├── ") + "(" + siteme + ") " + node.item + "\n");
            } else {
                builder.append(prefix + (isTail ? "└── " : "├── ") + node.item + "\n");
            }
            List<Node<E>> children = null;
            if (node.left != null || node.right != null) {
                children = new ArrayList<Node<E>>(2);
                if (node.left != null) {
                    children.add(node.left);
                }
                if (node.right != null) {
                    children.add(node.right);
                }
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

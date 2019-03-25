package pers.mingshan.tree;

import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author mingshan
 */
public class BinaryTree<E extends Comparable<E>> {
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

    public Node init() {
        Node root = new Node(null, 1);
        Node node1 = new Node(root, 2);
        Node node2 = new Node(root, 3);
        root.left = node1;
        root.right = node2;

        Node node3 = new Node(node1, 4);
        Node node4 = new Node(node1, 5);
        node1.left = node3;
        node1.right = node4;

        Node node5 = new Node(node2, 6);
        Node node6 = new Node(node2, 7);
        node2.left = node5;
        node2.right = node6;

        Node node10 = new Node(node4, 8);
        node4.left = node10;

        return root;
    }

    /**
     * 前序遍历：
     *
     * 对于当前结点，先输出该结点，然后输出它的左孩子，最后输出它的右孩子
     */

    /**
     * 前序遍历（递归）
     *
     * @param node
     */
    public void preOrderRec(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node); // 先输出该结点
        preOrderRec(node.left);   // 输出它的左孩子
        preOrderRec(node.right);  // 输出它的右孩子
    }

    /**
     * 前序遍历（非递归）<br/>
     *
     * <ul>
     *  <li>1. 对于任何结点node，如果该结点不为空，打印当前节点将自己压入栈内，然后将当前结点的左子结点赋值给node，直至node为null</li>
 *      <li>2. 若左子树为空，则栈顶元素出栈，并将当前node的右子结点赋值给node</li>
 *      <li>3. 重复1，2步操作，直至node为空，并且栈为空</li>
     * <ul/>
     *
     * @param node
     */
    public void preOrderNonRec(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node); // 先输出当前结点

        Stack<Node> stack = new Stack<>();
        stack.push(node);
        node = node.left;

        while (node != null || !stack.isEmpty()) {

            while (node != null) {
                System.out.println(node); // 先输出当前结点
                stack.push(node);         // 入栈
                node = node.left;         // 遍历左孩子
            }

            node = stack.pop();
            node = node.right;
        }
    }

    /**
     * 中序遍历：
     *
     * 对于当前结点，先输出它的左孩子，然后输出该结点，最后输出它的右孩子。
     */

    /**
     * 中序遍历（递归）
     *
     * @param node
     */
    public void inOrderRec(Node node) {
        if (node == null) {
            return;
        }

        inOrderRec(node.left);
        System.out.println(node);
        inOrderRec(node.right);
    }


    /**
     * 中序遍历（非递归）
     *
     * <ul>
     *  <li>1. 对于任何结点node，如果该结点不为空，将当前结点的左子结点赋值给node，直至node为null</li>
     *  <li>2. 若左子结点为空，栈顶节点出栈，输出该结点后将该结点的右子结点置为node</li>
     *  <li>3. 重复1，2操作</li>
     * </ul>
     *
     * @param node
     */
    public void inOrderNonRec(Node node) {
        if (node == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(node);
        node = node.left;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            System.out.println(node);
            node = node.right;
        }
    }

    /**
     * 后序遍历：
     *
     *
     * 对于当前结点，先输出它的左孩子，然后输出它的右孩子，最后输出该结点。
     */

    /**
     * 后序遍历（递归）
     *
     * @param node
     */
    public void postOrderRec(Node node) {
        if (node == null) {
            return ;
        }

        postOrderRec(node.left);
        postOrderRec(node.right);
        System.out.println(node);
    }


    /**
     * 后序遍历（非递归）
     *
     * 对于结点node，可分三种情况考虑：
     *
     * 1. node如果是叶子结点，直接输出
     * 2. node如果有孩子，且孩子没有被访问过，则按照右孩子，左孩子的顺序依次入栈
     * 3. node如果有孩子，而且孩子都已经访问过，则访问node节点
     *
     * 注意结点的右孩子先入栈，左孩子再入栈，这样才会先访问左孩子
     *
     * @param node
     */
    public void postOrderNonRec(Node node) {
        if (node == null) {
            return ;
        }

        Stack<Node> stack = new Stack<>();
        Node pre = root;
        stack.push(node);

        while (!stack.isEmpty()) {
            node = stack.peek();

            if ((node.left == null && node.right == null) ||
                    (node.right == null && pre == node.left) || (pre == node.right)) {
                System.out.println(node);
                pre = node;
                stack.pop();
            } else {
                // 右孩子先入栈，才会先访问结点的左孩子
                if (node.right != null) {
                    stack.push(node.right);
                }

                if (node.left != null) {
                    stack.push(node.left);
                }
            }

        }
    }

    /**
     * 层次遍历
     *
     * @param node 根结点
     */
    public void levelTraverse(Node node) throws InterruptedException {
        if(node == null) {
            return;
        }

        BlockingQueue<Node> queue = new LinkedBlockingQueue<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node item = queue.take();
            System.out.println(item);

            if (item.left != null) {
                queue.add(item.left);
            }

            if (item.right != null) {
                queue.add(item.right);
            }
        }
    }


    /**
     * 计算二叉树的深度
     * @param node 当前结点点
     * @return 二叉树的深度
     */
    public int getDepth(Node node) {
        if (node == null)
            return 0;

        int m = getDepth(node.left);
        int n = getDepth(node.right);

        return m > n ? m + 1 : n + 1;
    }


    /**
     * 计算结点的数量
     *
     * @param node 当前结点
     * @return 结点的数量
     */
    public int countNode(Node node) {
        if (node == null)
            return 0;
        return countNode(node.left) + countNode(node.right) + 1;
    }

    /**
     * 计算叶子结点的数量
     *
     * @param node 当前结点
     * @return 结点的数量
     */
    public int countLeafNode(Node node) {
        if (node == null)
            return 0;

        if (node.left == null && node.right == null) {
            return 1;
        }

        return countLeafNode(node.left) + countLeafNode(node.right);
    }


    /**
     * 获取二叉树第k层结点的数量
     *
     * @param node 根结点
     * @param k 第k层
     * @return 结点的数量
     */
    public int countKLevelNode(Node node, int k) {
        if (node == null || k <= 0) {
            return 0;
        }

        if (k == 1) {
            return 1;
        }

        return countKLevelNode(node.left, k - 1) + countKLevelNode(node.right, k - 1);
    }


    /**
     * 获取二叉树第k层叶子结点的个数
     *
     * @param node 根结点
     * @param k 第k层
     * @return 结点的数量
     */
    public int countKLevelLeafNode(Node node, int k) {
        if (node == null || k <= 0) {
            return 0;
        }

        if (k == 1) {
            if (node.left == null && node.right == null) {
                return 1;
            } else {
                return 0;
            }
        }

        return countKLevelNode(node.left, k - 1) + countKLevelNode(node.right, k - 1);
    }

    /**
     * 判断一个结点是否在二叉树内
     *
     * @param root 根结点
     * @param node 要检测的结点
     * @return 返回{@code true}，在；返回{@code false}，不在
     */
    public boolean isNodeInTree(Node root, Node node) {
        if (root == null || node == null) {
            return false;
        }

        if (root.item == node.item) {
            return true;
        }

        if (isNodeInTree(root.left, node) || isNodeInTree(root.right, node)) {
            return true;
        }

        return false;
    }
}

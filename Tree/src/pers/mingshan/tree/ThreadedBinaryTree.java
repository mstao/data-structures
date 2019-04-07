package pers.mingshan.tree;

/**
 * 线索二叉树
 *
 * @author mingshan
 */
public class ThreadedBinaryTree<E extends Comparable<E>> {

    // 根结点
    private BinaryTree.Node<E> root;
    // 二叉树结点数量
    private int size;
    // 线索化的时候保存前驱
    private Node pre = null;

    public static class Node<E extends Comparable<E>> {
        E item;
        Node<E> left;
        Node<E> right;
        boolean leftThread;
        boolean rightThread;

        public Node (E item) {
            this.item = item;
        }


        @Override
        public String toString() {
            return "item=" + item + " left="
                    + ((left != null) ? left.item : "NULL") + " right=" + ((right != null) ? right.item : "NULL")
                    + " leftThread=" + leftThread + " rightThread=" + rightThread;
        }
    }

    public Node<E> init() {
        Node root = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        root.left = node1;
        root.right = node2;

        Node node3 = new Node(4);
        Node node4 = new Node(5);
        node1.left = node3;
        node1.right = node4;

        Node node5 = new Node(6);
        Node node6 = new Node(7);
        node2.left = node5;
        node2.right = node6;

        Node node10 = new Node(8);
        node4.left = node10;

        return root;
    }


    /**
     * 中序遍历线索化
     *
     * @param root 二叉树根结点
     */
    public void inThreading(Node root) {
        if (root == null) {
            return;
        }

        // 按照中序遍历方向，先处理左子树
        inThreading(root.left);

        // 判断当前节点的左孩子是否为空，为空的话则当前节点的左孩子指向其前驱
        if (root.left == null) {
            root.leftThread = true;
            root.left = pre;
        }

        // 判断当前节点的右孩子是否为空，
        if (pre != null && pre.right == null) {
            pre.rightThread = true;
            pre.right = root;
        }

        pre = root;

        inThreading(root.right);
    }

    /**
     * 中序遍历
     *
     * @param root 根结点
     */
    public void inOrderNonRec(Node root) {
        if (root == null) {
            return;
        }
        // 查找中序遍历的起始节点
        while (root != null && !root.leftThread) {
            root = root.left;
        }
        while (root != null) {
            System.out.println(root);
            // 如果右孩子是线索
            if (root.rightThread) {
                root = root.right;
            } else { // 有右孩子
                root = root.right;
                while (root != null && !root.leftThread) {
                    root = root.left;
                }
            }
        }
    }

}

package me.mingshan.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 二叉树
 *
 * @author mingshan
 */
public class BinaryTree<E extends Comparable<E>> {
    // 根结点
    private Node<E> root;
    // 二叉树结点数量
    private int size;

    public static class Node<E extends Comparable<E>> {
        private E item;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(Node<E> parent, E item) {
            this.parent = parent;
            this.item = item;
        }

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "item=" + item + " parent=" + ((parent != null) ? parent.item : "NULL") + " left="
                    + ((left != null) ? left.item : "NULL") + " right=" + ((right != null) ? right.item : "NULL");
        }
    }

    public Node<Integer> init() {
        Node<Integer> root = new Node<>(null, 1);
        Node<Integer> node1 = new Node<>(root, 2);
        Node<Integer> node2 = new Node<>(root, 3);
        root.left = node1;
        root.right = node2;

        Node<Integer> node3 = new Node<>(node1, 4);
        Node<Integer> node4 = new Node<>(node1, 5);
        node1.left = node3;
        node1.right = node4;

        Node<Integer> node5 = new Node<>(node2, 6);
        Node<Integer> node6 = new Node<>(node2, 7);
        node2.left = node5;
        node2.right = node6;

        node4.left = new Node<>(node4, 8);

        return root;
    }

    /*
     * 先序遍历：
     *
     * 对于当前结点，先输出该结点，然后输出它的左孩子，最后输出它的右孩子
     */

    /**
     * 先序遍历（递归）
     *
     * @param node 根节点
     */
    public void preOrderRec(Node<E> node) {
        if (node == null) {
            return;
        }

        System.out.println(node); // 先输出该结点
        preOrderRec(node.left);   // 输出它的左孩子
        preOrderRec(node.right);  // 输出它的右孩子
    }

    /**
     * 先序遍历（非递归）<br/>
     *
     * <ul>
     *     <li>1. 先访问树的根节点</li>
     *     <li>2. 将右子孩子入栈</li>
     *     <li>3. 将左子孩子入栈</li>
     *     <li>4. 重复1， 2， 3步，直至栈为空</li>
     * </ul>
     *
     * @param node
     */
    public void preOrderNonRec(Node<E> node) {
        if (node == null) {
            return;
        }

        Stack<Node<E>> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            Node<E> eNode = stack.pop();
            System.out.println(eNode);

            if (eNode.getRight() != null) {
                stack.push(eNode.getRight());
            }

            if (eNode.getLeft() != null) {
                stack.push(eNode.getLeft());
            }
        }
    }

    /**
     * 先序遍历（非递归）<br/>
     *
     * <ul>
     *  <li>1. 对于任何结点node，如果该结点不为空，打印当前节点将自己压入栈内，然后将当前结点的左子结点赋值给node，直至node为null</li>
     *  <li>2. 若左子树为空，则栈顶元素出栈，并将当前node的右子结点赋值给node</li>
     *  <li>3. 重复1，2步操作，直至node为空，并且栈为空</li>
     * <ul/>
     *
     * @param node 根节点
     */
    public void preOrderNonRec2(Node<E> node) {
        if (node == null) {
            return;
        }

        System.out.println(node); // 先输出当前结点

        Stack<Node<E>> stack = new Stack<>();
        stack.push(node);
        node = node.left;

        while (node != null || !stack.isEmpty()) {
            visitAlongLeftBranch(node, stack);

            node = stack.pop();
            node = node.right;
        }
    }

    /**
     * 从当前节点开始，沿左链下行，依次访问
     *
     * @param node 当前节点
     * @param stack 栈
     */
    private void visitAlongLeftBranch(Node<E> node, Stack<Node<E>> stack) {
        while (node != null) {
            System.out.println(node); // 先输出当前结点
            stack.push(node);         // 入栈
            node = node.left;         // 遍历左孩子
        }
    }

    /*
     * 中序遍历：
     *
     * 对于当前结点，先输出它的左孩子，然后输出该结点，最后输出它的右孩子。
     */

    /**
     * 中序遍历（递归）
     *
     * @param node 根节点
     */
    public void inOrderRec(Node<E> node) {
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
     * @param node 根节点
     */
    public void inOrderNonRec(Node<E> node) {
        if (node == null) {
            return;
        }

        Stack<Node<E>> stack = new Stack<>();
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

    /*
     * 后序遍历：
     *
     *
     * 对于当前结点，先输出它的左孩子，然后输出它的右孩子，最后输出该结点。
     */

    /**
     * 后序遍历（递归）
     *
     * @param node 根节点
     */
    public void postOrderRec(Node<E> node) {
        if (node == null) {
            return;
        }

        postOrderRec(node.left);
        postOrderRec(node.right);
        System.out.println(node);
    }


    /**
     * 后序遍历（非递归）
     * <p>
     * 对于结点node，可分三种情况考虑：
     * <p>
     * 1. node如果是叶子结点，直接输出
     * 2. node如果有孩子，且孩子没有被访问过，则按照右孩子，左孩子的顺序依次入栈
     * 3. node如果有孩子，而且孩子都已经访问过，则访问node节点
     * <p>
     * 注意结点的右孩子先入栈，左孩子再入栈，这样才会先访问左孩子
     *
     * @param node 根节点
     */
    public void postOrderNonRec(Node<E> node) {
        if (node == null) {
            return;
        }

        Stack<Node<E>> stack = new Stack<>();
        Node<E> pre = root;
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
    public void levelTraverse(Node<E> node) throws InterruptedException {
        if (node == null) {
            return;
        }

        BlockingQueue<Node<E>> queue = new LinkedBlockingQueue<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node<E> item = queue.take();
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
     * 层次遍历
     *
     * @param node 根结点
     */
    public void levelTraversePerLine(Node<E> node) throws InterruptedException {
        if (node == null) {
            return;
        }

        int curLevelCount = 1; // 当前层结点数量
        int nextLevelCount = 0; // 下一层结点数量

        BlockingQueue<Node<E>> queue = new LinkedBlockingQueue<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node<E> item = queue.take();
            System.out.println(item);
            System.out.println(curLevelCount);
            curLevelCount--;

            if (item.left != null) {
                queue.add(item.left);
                nextLevelCount++;
            }

            if (item.right != null) {
                queue.add(item.right);
                nextLevelCount++;
            }

            if (0 == curLevelCount) {
                System.out.println();
                curLevelCount = nextLevelCount;
                nextLevelCount = 0;
            }

        }
    }


    /**
     * 计算二叉树的深度
     *
     * @param node 当前结点点
     * @return 二叉树的深度
     */
    public int getDepth(Node<E> node) {
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
    public int countNode(Node<E> node) {
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
    public int countLeafNode(Node<E> node) {
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
     * @param k    第k层
     * @return 结点的数量
     */
    public int countKLevelNode(Node<E> node, int k) {
        if (node == null || k <= 0) {
            return 0;
        }

        if (k == 1) {
            return 1;
        }

        return countKLevelNode(node.left, k - 1) + countKLevelNode(node.right, k - 1);
    }

    /**
     * 获取二叉树第k层结点的数量 - 非递归
     *
     * @param node 根结点
     * @param k    第k层
     * @return 结点的数量
     */
    public int countKLevelNodeNonRec(Node<E> node, int k) throws InterruptedException {
        if (node == null) {
            return 0;
        }

        int level = 0;    //当前层计数器
        int cntNode;  //当前层节点数计数器
        int curLevelNodesTotal = 0; //当前层节点总数

        BlockingQueue<Node<E>> queue = new LinkedBlockingQueue<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            ++level;
            cntNode = 0; //当前层节点数计数器归0
            curLevelNodesTotal = queue.size();//当前层的节点总数

            if (level == k)//如果层数已大于指定层数，则退出
                break;

            while (cntNode < curLevelNodesTotal) {

                ++cntNode;//记录当前层的节点数
                node = queue.take();

                //将当前层节点的左右结点均入队，即将下一层节点入队
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }

        while (!queue.isEmpty())
            queue.clear();//清空队列
        if (level == k)
            return curLevelNodesTotal;

        return 0;
    }

    /**
     * 获取二叉树第k层叶子结点的个数
     *
     * @param node 根结点
     * @param k    第k层
     * @return 结点的数量
     */
    public int countKLevelLeafNode(Node<E> node, int k) throws InterruptedException {
        if (node == null) {
            return 0;
        }

        int level = 0;    //当前层计数器
        int cntNode;  //当前层节点数计数器
        int curLevelNodesTotal; //当前层节点总数

        BlockingQueue<Node<E>> queue = new LinkedBlockingQueue<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            ++level;
            cntNode = 0; //当前层节点数计数器归0
            curLevelNodesTotal = queue.size();//当前层的节点总数

            // 如果层数等于指定层数，遍历该层的结点，判断叶子结点
            if (level == k) {
                int leafCount = 0;
                while (!queue.isEmpty()) {
                    node = queue.take();
                    if (node.left == null && node.right == null) {
                        ++leafCount;
                    }
                }

                return leafCount;
            }

            while (cntNode < curLevelNodesTotal) {

                ++cntNode;//记录当前层的节点数
                node = queue.take();

                //将当前层节点的左右结点均入队，即将下一层节点入队
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }

        return 0;
    }

    /**
     * 判断一个结点是否在二叉树内
     *
     * @param root 根结点
     * @param node 要检测的结点
     * @return 返回{@code true}，在；返回{@code false}，不在
     */
    public boolean isNodeInTree(Node<E> root, Node<E> node) {
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

    /**
     * 获取给定结点的父结点
     *
     * @param root 根结点
     * @param curr 给定结点
     * @return 给定结点的父结点
     */
    public Node<E> getParent(Node<E> root, Node<E> curr) {
        if (root == null || curr == null) {
            return null;
        }

        if (root == curr) {
            return null;
        }

        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);
        root = root.left;

        while (root != null || !stack.isEmpty()) {

            while (root != null) {
                if (root.left.item == curr.item || root.right.item == curr.item) {
                    return root;
                }
                stack.push(root);         // 入栈
                root = root.left;         // 遍历左孩子
            }

            root = stack.pop();
            root = root.right;
        }

        return null;
    }

    /**
     * 二叉树的镜像 - 递归
     *
     * @param node 根结点
     */
    public void mirrorRec(Node<E> node) {
        if (node == null) {
            return;
        }

        // 交换左右子树
        Node<E> temp = node.left;
        node.left = node.right;
        node.right = temp;

        // 对交换后的左右子树继续进行镜像处理
        mirrorRec(node.left);
        mirrorRec(node.right);
    }

    /**
     * 二叉树的镜像 - 非递归，采用先序遍历
     *
     * @param node 根结点
     */
    public void mirrorNonRec(Node<E> node) {
        if (node == null) {
            return;
        }

        // 交换左右子树
        swap(node);

        Stack<Node<E>> stack = new Stack<>();
        stack.push(node);
        node = node.left;

        while (node != null || !stack.isEmpty()) {

            while (node != null) {
                // 交换左右子树
                swap(node);

                stack.push(node);         // 入栈
                node = node.left;         // 遍历左孩子
            }

            node = stack.pop();
            node = node.right;
        }
    }

    private void swap(Node<E> node) {
        // 交换左右子树
        Node<E> temp = node.left;
        node.left = node.right;
        node.right = temp;
    }


    /**
     * 最低公共祖先，即LCA(Lowest Common Ancestor）,此种情况假设节点没有父结点的指针
     *
     * @param root  根结点
     * @param node1 节点1
     * @param node2 节点2
     * @return 最低公共祖先
     */
    public Node<E> findLCA(Node<E> root, Node<E> node1, Node<E> node2) {
        if (root == null) {
            return null;
        }

        if (node1 == root || node2 == root) {
            return root;
        }

        Node<E> temp1 = findLCA(root.left, node1, node2);
        Node<E> temp2 = findLCA(root.right, node1, node2);
        if (temp1 != null && temp2 != null) {
            return root;
        }

        return temp1 != null ? temp1 : temp2;
    }


    /**
     * 最低公共祖先，即LCA(Lowest Common Ancestor）,此种情况假设节点拥有父结点的指针
     *
     * @param root  根结点
     * @param node1 节点1
     * @param node2 节点2
     * @return 最低公共祖先
     */
    public Node<E> findLCA2(Node<E> root, Node<E> node1, Node<E> node2) {
        if (root == null) {
            return null;
        }

        if (node1 == root || node2 == root) {
            return root;
        }

        List<Node<E>> queue1 = new LinkedList<>();
        while (node1.parent != null) {
            node1 = node1.parent;
            queue1.add(node1);
        }

        List<Node<E>> queue2 = new LinkedList<>();
        while (node2.parent != null) {
            node2 = node2.parent;
            queue2.add(node2);
        }

        return getFirstCommonNode(queue1, queue2);
    }

    /**
     * 两个链表的第一个公共结点
     *
     * @param queue1 链表1
     * @param queue2 链表2
     * @return 第一个公共结点
     */
    private Node<E> getFirstCommonNode(List<Node<E>> queue1, List<Node<E>> queue2) {
        for (int i = 0; i < queue1.size(); i++) {
            for (int j = 0; j < queue1.size(); j++) {
                if (queue1.get(i) == queue2.get(j)) {
                    return queue1.get(i);
                }
            }
        }

        return null;

//        int len1 = queue1.size();
//        int len2 = queue2.size();
//
//        int n = Math.abs(len1 - len2);
//
//        List<Node> curr1; // 代表长的链表
//        List<Node> curr2; // 代表短的链表
//
//        if (len1 > len2) {
//            curr1 = queue1;
//            curr2 = queue2;
//        } else {
//            curr1 = queue2;
//            curr2 = queue1;
//        }
//
//        Node node = curr1.get(n - 1);
//
//
//        for (int i = 0; i < queue1.size(); i++) {
//            for (int j = 0; j < queue1.size(); j++) {
//                if (queue1.get(i) == queue2.get(j)) {
//                    return queue1.get(i);
//                }
//            }
//        }
    }


    public void display(Node<E> node) {
        if (node == null) {
            return;
        }

        String outputString = "";
        outputString += node.left == null ? "." : node.left.item;
        outputString += "->" + node.item + "<-";
        outputString += node.right == null ? "." : node.right.item;

        System.out.println(outputString);

        this.display(node.left);
        this.display(node.right);
    }
}

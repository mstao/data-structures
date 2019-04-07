package pers.mingshan.tree.test;

import org.junit.Test;
import pers.mingshan.tree.BinaryTree;

/**
 * @author mingshan
 */
public class BinaryTreeTest {
    final BinaryTree<Integer> tree = new BinaryTree<>();
    final BinaryTree.Node<Integer> root = tree.init();

    @Test
    public void test1() {
        tree.preOrderRec(root);
    }

    @Test
    public void test2() {
        tree.preOrderNonRec(root);
    }

    @Test
    public void test3() {
        tree.inOrderRec(root);
    }

    @Test
    public void test4() {
        tree.inOrderNonRec(root);
    }

    @Test
    public void test5() {
        tree.postOrderRec(root);
    }

    @Test
    public void test6() {
        tree.postOrderNonRec(root);
    }

    @Test
    public void test7() throws InterruptedException {
        tree.levelTraverse(root);
    }

    @Test
    public void test71() throws InterruptedException {
        tree.levelTraversePerLine(root);
    }

    @Test
    public void test8() {
        System.out.println(tree.getDepth(root));
    }

    @Test
    public void test9() {
        System.out.println(tree.countLeafNode(root));
    }

    @Test
    public void test10() {
        System.out.println(tree.countKLevelNode(root, 4));
    }

    @Test
    public void test11() throws InterruptedException {
        System.out.println(tree.countKLevelLeafNode(root, 2));
    }

    @Test
    public void test111() throws InterruptedException {
        System.out.println(tree.countKLevelNodeNonRec(root, 4));
    }

    @Test
    public void test12() {
        BinaryTree.Node node = new BinaryTree.Node(null, 4);
        System.out.println(tree.isNodeInTree(root, node));
    }

    @Test
    public void test13() {
        BinaryTree.Node node = new BinaryTree.Node(null, 4);
        System.out.println(tree.getParent(root, node));
    }

    @Test
    public void test14() {
        tree.display(root);
    }

    @Test
    public void test15() throws InterruptedException {
        tree.mirrorRec(root);
        tree.levelTraverse(root);
    }

    @Test
    public void test16() throws InterruptedException {
        tree.mirrorNonRec(root);
        tree.levelTraverse(root);
    }

    @Test
    public void test17() throws InterruptedException {
        BinaryTree.Node root = new BinaryTree.Node(null, 1);
        BinaryTree.Node node1 = new BinaryTree.Node(root, 2);
        BinaryTree.Node node2 = new BinaryTree.Node(root, 3);
        root.setLeft(node1);
        root.setRight(node2);

        BinaryTree.Node node3 = new BinaryTree.Node(node1, 4);
        BinaryTree.Node node4 = new BinaryTree.Node(node1, 5);
        node1.setLeft(node3);
        node1.setRight(node4);

        BinaryTree.Node node5 = new BinaryTree.Node(node2, 6);
        BinaryTree.Node node6 = new BinaryTree.Node(node2, 7);
        node2.setLeft(node5);
        node2.setRight(node6);

        BinaryTree.Node node10 = new BinaryTree.Node(node4, 8);
        node4.setLeft(node10);
        System.out.println(tree.findLCA(root, node3, node10));
    }

    @Test
    public void test18() throws InterruptedException {
        BinaryTree.Node root = new BinaryTree.Node(null, 1);
        BinaryTree.Node node1 = new BinaryTree.Node(root, 2);
        BinaryTree.Node node2 = new BinaryTree.Node(root, 3);
        root.setLeft(node1);
        root.setRight(node2);

        BinaryTree.Node node3 = new BinaryTree.Node(node1, 4);
        BinaryTree.Node node4 = new BinaryTree.Node(node1, 5);
        node1.setLeft(node3);
        node1.setRight(node4);

        BinaryTree.Node node5 = new BinaryTree.Node(node2, 6);
        BinaryTree.Node node6 = new BinaryTree.Node(node2, 7);
        node2.setLeft(node5);
        node2.setRight(node6);

        BinaryTree.Node node10 = new BinaryTree.Node(node4, 8);
        node4.setLeft(node10);
        System.out.println(tree.findLCA2(root, node3, node10));
    }
}

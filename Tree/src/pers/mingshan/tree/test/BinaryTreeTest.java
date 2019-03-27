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
    public void test11() {
        System.out.println(tree.countKLevelLeafNode(root, 3));
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
}

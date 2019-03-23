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
    public void test7() {
        System.out.println(tree.getDepth(root));
    }

    @Test
    public void test8() {
        System.out.println(tree.countLeafNode(root));
    }
}

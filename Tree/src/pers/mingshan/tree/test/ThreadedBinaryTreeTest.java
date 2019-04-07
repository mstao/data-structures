package pers.mingshan.tree.test;

import org.junit.Before;
import org.junit.Test;
import pers.mingshan.tree.ThreadedBinaryTree;

/**
 * @author mingshan
 */
public class ThreadedBinaryTreeTest {

    final ThreadedBinaryTree<Integer> tree = new ThreadedBinaryTree<>();

    final ThreadedBinaryTree.Node<Integer> root = tree.init();

    @Before
    public void b() {
        tree.inThreading(root);
    }

    @Test
    public void test0() {
        tree.inOrderNonRec(root);
    }
}

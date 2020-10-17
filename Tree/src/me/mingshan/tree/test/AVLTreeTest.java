package me.mingshan.tree.test;

import me.mingshan.tree.AVLTree;
import org.junit.Test;

public class AVLTreeTest {
    final AVLTree<Integer> tree = new AVLTree<>();

    @Test
    public void testRR() {
        AVLTree.AVLNode<Integer> root = new AVLTree.AVLNode<>(1);
        AVLTree.AVLNode<Integer> node1 = new AVLTree.AVLNode<>(3);

        AVLTree.AVLNode<Integer> node2 = new AVLTree.AVLNode<>(2);
        AVLTree.AVLNode<Integer> node3 = new AVLTree.AVLNode<>(4);
        node1.setLeft(node2);
        node1.setRight(node3);
        root.setRight(node1);
        tree.initRoot(root);

        System.out.println(tree.toString());

        AVLTree.AVLNode<Integer> avlNode2 = tree.rotateLeft(root);
        tree.initRoot(avlNode2);

        System.out.println(tree.toString());

    }

    @Test
    public void testLL() {
        AVLTree.AVLNode<Integer> root = new AVLTree.AVLNode<>(4);
        AVLTree.AVLNode<Integer> node1 = new AVLTree.AVLNode<>(3);

        AVLTree.AVLNode<Integer> node2 = new AVLTree.AVLNode<>(2);
        AVLTree.AVLNode<Integer> node3 = new AVLTree.AVLNode<>(1);

        root.setLeft(node1);
        node1.setLeft(node3);
        node1.setRight(node2);

        tree.initRoot(root);

        System.out.println(tree.toString());

        AVLTree.AVLNode<Integer> avlNode2 = tree.rotateRight(root);
        tree.initRoot(avlNode2);

        System.out.println(tree.toString());
    }

    @Test
    public void testRL() {
        AVLTree.AVLNode<Integer> root = new AVLTree.AVLNode<>(1);
        AVLTree.AVLNode<Integer> node1 = new AVLTree.AVLNode<>(3);

        AVLTree.AVLNode<Integer> node2 = new AVLTree.AVLNode<>(2);
        AVLTree.AVLNode<Integer> node3 = new AVLTree.AVLNode<>(4);

        AVLTree.AVLNode<Integer> node4 = new AVLTree.AVLNode<>(5);
        AVLTree.AVLNode<Integer> node5 = new AVLTree.AVLNode<>(6);

        root.setRight(node4);
        node4.setLeft(node1);
        node4.setRight(node5);
        node1.setLeft(node2);
        node1.setRight(node3);

        tree.initRoot(root);

        System.out.println(tree.toString());

        AVLTree.AVLNode<Integer> avlNode2 = tree.rotateRight((AVLTree.AVLNode<Integer>) root.getRight());
        root.setRight(avlNode2);
        tree.initRoot(root);
        System.out.println(tree.toString());

        root.setRight(avlNode2);
        AVLTree.AVLNode<Integer> avlNode3 = tree.rotateLeft(root);
        tree.initRoot(avlNode3);
        System.out.println(tree.toString());
    }
}

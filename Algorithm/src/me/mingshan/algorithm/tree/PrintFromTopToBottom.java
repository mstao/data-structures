package me.mingshan.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 从上往下打印出二叉树的每个结点，同一层的结点按照从左到右的顺序打印。
 */
public class PrintFromTopToBottom {

  public static void main(String[] args) {
    BinaryTree.Node<String> root = init();
    solution(root);
  }

  public static BinaryTree.Node<String> init() {
    BinaryTree.Node<String> root = new BinaryTree.Node<String>(null, "1");
    BinaryTree.Node<String> node1 = new BinaryTree.Node<String>(root, "2");
    BinaryTree.Node<String> node2 = new BinaryTree.Node<String>(root, "3");
    root.left = node1;
    root.right = node2;

    BinaryTree.Node<String> node3 = new BinaryTree.Node<String>(node1, "4");
    BinaryTree.Node<String> node4 = new BinaryTree.Node<String>(node1, "5");
    node1.left = node3;
    node1.right = node4;

    BinaryTree.Node<String> node5 = new BinaryTree.Node<String>(node2, "6");
    BinaryTree. Node<String> node6 = new BinaryTree.Node<String>(node2, "7");
    node2.left = node5;
    node2.right = node6;

    node4.left = new BinaryTree.Node<String>(node4, "8");

    return root;
  }

  public static void solution(BinaryTree.Node<String> root) {
    if (root == null) {
      return;
    }


    Queue<BinaryTree.Node<String>> queue = new LinkedList<>();
    queue.add(root);

    while (queue.isEmpty() == false) {
      BinaryTree.Node<String> node = queue.poll();
      System.out.println(node.getItem());

      if (node.getLeft() != null) {
        queue.add(node.left);
      }

      if (node.getRight() != null) {
        queue.add(node.right);
      }
    }
  }

  private static class BinaryTree<E extends Comparable<E>> {
    // 根结点
    private Node<E> root;
    // 二叉树结点数量
    private int size;

    static class Node<E extends Comparable<E>> {
      private E item;
      private Node<E> parent;
      private Node<E> left;
      private Node<E> right;

      public Node (Node<E> parent, E item) {
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
  }

}

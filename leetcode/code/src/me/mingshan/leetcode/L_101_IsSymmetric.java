package me.mingshan.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 101. 对称二叉树
 * <p>
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 * <p>
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *  https://leetcode-cn.com/problems/symmetric-tree/
 */
public class L_101_IsSymmetric {

  public static void main(String[] args) throws InterruptedException {
    TreeNode root = new TreeNode(1);
    TreeNode node1 = new TreeNode(2);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(4);
    TreeNode node6 = new TreeNode(3);

    root.left = node1;
    root.right = node2;

    //node1.left = node3;
    node1.right = node3;

    //node2.left = node5;
    node2.right = node6;

    System.out.println(isSymmetric(root));
  }

  /**
   * 非递归
   *
   * 此题迭代思路比较清晰，层次遍历可以逐层遍历，我们拿到每一层的节点，可以利用该层节点是否为回文来判断即可，有两个注意的地方
   *
   * 1. 每一层的空节点也统计，否则不能判断该层是否为镜像对称
   * 2. 处理每一层的节点时，我们可以在每一层结束插入一个flag节点，来判断该层是否结束
   *
   * @param root
   * @return
   */
  public static boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }

    BlockingQueue<TreeNode> queue = new LinkedBlockingQueue<>();

    TreeNode flag = new TreeNode(Integer.MAX_VALUE);

    queue.add(root);
    queue.add(flag);

    // 每一层节点放的元素
    List<TreeNode> nextLevelNodeList = new ArrayList<>();

    while (!queue.isEmpty()) {
      TreeNode treeNode = queue.poll();

      if (flag == treeNode) {
        // 先判断当前层是否是回文
        if (nextLevelNodeList.size() % 2 != 0) {
          return false;
        }

        if (!isPalindrome(nextLevelNodeList)) {
          return false;
        }

        // 清楚数据，继续下一层
        nextLevelNodeList.clear();

        if (queue.isEmpty()) {
          break;
        }
        queue.add(flag);
        continue;
      }

      //System.out.println("层级：" + level + "节点：" + treeNode);

      nextLevelNodeList.add(treeNode.left);
      nextLevelNodeList.add(treeNode.right);

      if (treeNode.left != null) {
        queue.add(treeNode.left);
      }

      if (treeNode.right != null) {
        queue.add(treeNode.right);
      }
    }

    return true;
  }

  private static boolean isPalindrome(List<TreeNode> levelNodeList) {
    if (levelNodeList.isEmpty()) {
      return true;
    }

    for (int i = 0; i < levelNodeList.size(); i++) {
      TreeNode first = levelNodeList.get(i);
      TreeNode last = levelNodeList.get(levelNodeList.size() - i - 1);

      if (first == null && last != null) {
        return false;
      }

      if (first != null && last == null) {
        return false;
      }

      if (first != null && first.val != last.val) {
        return false;
      }
    }

    return true;
  }


  /**
   * TODO 递归版
   *
   * @param root
   * @return
   */
  public static boolean isSymmetric2(TreeNode root) {
    return false;
  }

  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }

    @Override
    public String toString() {
      return "TreeNode{" +
          "val=" + val +
          ", left=" + left +
          ", right=" + right +
          '}';
    }
  }
}

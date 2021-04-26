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

    System.out.println(isSymmetric2(root));
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
   * 递归版
   *
   * 在[迭代层次遍历](https://leetcode-cn.com/problems/symmetric-tree/solution/die-dai-ceng-ci-bian-li-by-mingshan-kiz7/)中介绍了迭代的解法，
   * 代码比较多，但逻辑是比较明确的，下面说递归思路：
   *
   * 1. 如果根节点是null，直接返回true；
   * 2. 接着就要判断根节点的左右子树是否为对称
   * 3. 那么如何判断左右子树是否对称呢？如果左右子树的根节点不相等，那么肯定不对称，无需考虑其子树。这时我们自己画下图就可以得出信息，就是左子树根节点的左子树 与 右子树根节点的 右子树，
   * 左子树根节点的右子树 与 右子树根节点的 左子树，两个都是对称的，原先的两个子树才对称；
   * 4. 那么如何判断左子树根节点的左子树 与 右子树根节点的 右子树 对称呢？ 其实此时又回到第三步了，就是如何判断两个树是否对称，满足递归的基本条件
   *
   * 上面的分析过程已经包含了递归返回的条件：
   *
   * 1. 两个树的根节点都为空，此时返回true
   * 2. 两个树的根节点值不同（一个为空，一个不为空；值不相等），返回false
   *
   * @param root
   * @return
   */
  public static boolean isSymmetric2(TreeNode root) {
    if (root == null) {
      return true;
    }

    return isSymmetric3(root.left, root.right);
  }

  /**
   * 判断两个树是否对称
   *
   * @param left
   * @param right
   * @return
   */
  private static boolean isSymmetric3(TreeNode left, TreeNode right) {
    if (left == null && right == null) {
      return true;
    }

    if (left == null || right == null) {
      return false;
    }

    if (left.val != right.val) {
      return false;
    }

    return isSymmetric3(left.left, right.right) && isSymmetric3(left.right, right.left);
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

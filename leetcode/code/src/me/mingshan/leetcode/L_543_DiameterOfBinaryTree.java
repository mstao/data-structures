package me.mingshan.leetcode;

/**
 * 543. 二叉树的直径
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 *  
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 *  
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class L_543_DiameterOfBinaryTree {
  private static int sum;

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    TreeNode node1 = new TreeNode(2);
    TreeNode node2 = new TreeNode(3);
    TreeNode node3 = new TreeNode(4);
    TreeNode node4 = new TreeNode(5);
//    TreeNode node5 = new TreeNode(4);
//    TreeNode node6 = new TreeNode(3);

    root.left = node1;
    root.right = node2;

    node1.left = node3;
    node1.right = node4;

//    node2.left = node5;
//    node2.right = node6;

    System.out.println(diameterOfBinaryTree(root));
  }

  /**
   * 解题思路：将题转化为，对于任何一个节点，求出左子树右子树深度之和-1的最大值，即为答案
   *
   * @param root
   * @return
   */
  public static int diameterOfBinaryTree(TreeNode root) {
    if (root == null) {
      return 0;
    }

    preOrder(root);

    return sum;
  }

  private static void preOrder(TreeNode root) {
    if (root == null) {
      return;
    }

    int currSum = calDepthSum(root);
    sum = Math.max(currSum, sum);

    preOrder(root.left);
    preOrder(root.right);
  }

  private static int calDepthSum(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int depth = 0;

    if (root.left != null) {
      depth += maxDepth(root.left);
    }
    if (root.right != null) {
      depth += maxDepth(root.right);
    }

    return depth;
  }

  public static int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
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

package me.mingshan.leetcode;

/**
 * 226. 翻转二叉树
 *
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class L_226_InvertTree {

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    TreeNode node1 = new TreeNode(2);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(4);
    TreeNode node6 = new TreeNode(3);

    root.left = node1;
    root.right = node2;

    node1.left = node3;
    node1.right = node4;

    node2.left = node5;
    node2.right = node6;

    TreeNode treeNode = invertTree(root);
    System.out.println(treeNode);
  }

  /**
   * 此题和 101. 对称二叉树 递归解法一致，实际上就是求树的镜像，思路很简单，
   * 参考题解：https://leetcode-cn.com/problems/symmetric-tree/solution/di-gui-jie-fa-by-mingshan-2ri5/
   *
   * 作者：mingshan
   * 链接：https://leetcode-cn.com/problems/invert-binary-tree/solution/di-gui-jie-fa-by-mingshan-6qfw/
   * 来源：力扣（LeetCode）
   * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
   *
   * @param root
   * @return
   */
  public static TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }

    invert(root);
    return root;
  }

  private static void invert(TreeNode root) {
    if (root == null) {
      return;
    }

    swap(root);

    if (root.left != null) {
      invert(root.left);
    }
    if (root.right != null) {
      invert(root.right);
    }
  }

  private static void swap(TreeNode root) {
    TreeNode left = root.left;
    root.left = root.right;
    root.right = left;
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

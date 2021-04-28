package me.mingshan.leetcode;

/**
 * 617. 合并二叉树
 * <p>
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 * <p>
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * Tree 1                     Tree 2
 * 1                         2
 * / \                       / \
 * 3   2                     1   3
 * /                           \   \
 * 5                             4   7
 * 输出:
 * 合并后的树:
 * 3
 * / \
 * 4   5
 * / \   \
 * 5   4   7
 * 注意: 合并必须从两个树的根节点开始。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_617_MergeTrees {

  public static void main(String[] args) {
//    TreeNode root = new TreeNode(1);
//    TreeNode node1 = new TreeNode(2);
//    TreeNode node2 = new TreeNode(2);
//    TreeNode node3 = new TreeNode(3);
//    TreeNode node4 = new TreeNode(4);
//    TreeNode node5 = new TreeNode(4);
//    TreeNode node6 = new TreeNode(3);
//
//    root.left = node1;
//    root.right = node2;
//
//    node1.left = node3;
//    node1.right = node4;
//
//    node2.left = node5;
//    node2.right = node6;
//
//    System.out.println(mergeTrees(root, root));

    TreeNode root1 = new TreeNode(1);
    TreeNode node1 = new TreeNode(3);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(5);

    root1.left = node1;
    root1.right = node2;
    node1.left = node3;

    TreeNode root11 = new TreeNode(2);
    TreeNode node11 = new TreeNode(1);
    TreeNode node12 = new TreeNode(3);
    TreeNode node13 = new TreeNode(4);
    TreeNode node14 = new TreeNode(7);
    root11.left = node11;
    root11.right = node12;
    node11.right = node13;
    node12.right = node14;

    System.out.println(mergeTrees(root1, root11));
  }

  public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
    if (root1 == null) {
      return root2;
    }

    if (root2 == null) {
      return root1;
    }

    root1.val = root1.val + root2.val;

    root1.left = mergeTrees(root1.left, root2.left);
    root1.right = mergeTrees(root1.right, root2.right);
    return root1;
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

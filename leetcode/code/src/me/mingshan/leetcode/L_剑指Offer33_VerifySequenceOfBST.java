package me.mingshan.leetcode;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 *
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 *
 */
public class L_剑指Offer33_VerifySequenceOfBST {

  public static void main(String[] args) {
    System.out.println(verifyPostorder(new int[]{1,6,3,2,5})); // false

    System.out.println(verifyPostorder(new int[]{1,3,2,6,5})); // true
    System.out.println(verifyPostorder(new int[]{1,2,3,4})); // true
    System.out.println(verifyPostorder(new int[]{6,8,9,5})); // true
  }

  /**
   *
   * 思路：
   *
   * 二叉树后序遍历，最后一个节点是根节点，根据二叉搜索树的特性，根节点左边的，必然小于根节点；右边的，必然大于根节点。
   *
   * 由于 二叉树后序遍历 先输出左子树，后输出右子树，最后根节点，所以在数组中，必然左子树输出是连续的，右子树输出是连续的，
   * 我们可以找到第一个大于根节点的节点，当前节点就是左子树与右子树的分隔点
   *
   * 对于左子树和右子树同样也要判断，可以使用递归
   *
   * @param data
   * @return
   */
  public static boolean verifyPostorder(int[] data) {
    if (data == null || data.length == 0) {
      return false;
    }

    return verify(data, 0, data.length - 1);
  }

  /**
   *
   * @param data 原始数组
   * @param begin 树的起始节点
   * @param end 树的结束节点
   * @return 是否正确
   */
  private static boolean verify(int[] data, int begin, int end) {
    if (begin >= end) {
      return true;
    }

    // 当前树的根节点
    int root = data[end];

    // 从 begin 到 end -1 必然是分为连续两部分的，左边一部分全比root小，右边一部分全比root大，如果不满足这个条件，就返回false

    // 找到第一个比root大的点，该点就是左右两区域的分界点

    Integer firstBig = null;
    int currIndex = begin;

    while (currIndex <= end) {
      if (data[currIndex] > root) {
        firstBig = data[currIndex];
        break;
      }

      currIndex++;
    }

    // 校验左边的都小于root，右边的都大于root

    for (int i = begin; i < currIndex; i++) {
      if (data[i] > root) {
        return false;
      }
    }

    for (int i = currIndex; i < end; i++) {
      if (data[i] < root) {
        return false;
      }
    }

    // 如果找不到第一个比root大的点，要么说明右子树为空
    if (firstBig == null) {
      return verify(data, begin, end - 1);
    } else {
      if (currIndex == begin) {
        return verify(data, currIndex, end - 1);
      } else {
        // 否则要判断左右子树都要满足
        return verify(data, begin, currIndex - 1) && verify(data, currIndex, end - 1);
      }
    }
  }

}

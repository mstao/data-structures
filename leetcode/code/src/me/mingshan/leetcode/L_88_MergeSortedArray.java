package me.mingshan.leetcode;

import java.util.Arrays;

/**
 *
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author hanjuntao
 */
public class L_88_MergeSortedArray {

  public static void main(String[] args) {
    int[] nums1 = new int[100];
    nums1[0] = 1;
    nums1[1] = 3;
    nums1[2] = 4;
    nums1[3] = 6;
    nums1[4] = 7;
    nums1[5] = 9;

    int[] nums2 = {2, 5, 7, 8, 10, 11, 23};

    merge(nums1, 6, nums2, nums2.length);
    System.out.println(Arrays.toString(nums1));
  }

  /**
   * 思路：新建一个数组，然后按照两个数组从前往后的顺序遍历，每次小的数组指针向前移动，当有一个数组遍历完后，
   * 剩下的另一个数组的元素直接拷贝到目标数组即可
   *
   * @param nums1
   * @param m
   * @param nums2
   * @param n
   */
  public static void merge(int[] nums1, int m, int[] nums2, int n) {
    int last = 0;

    int[] temp = nums1.clone();

    int i = 0;
    int j = 0;
    while (i <= m -1 && j <= n -1) {
      if (nums1[i] > nums2[j]) {
        temp[last++] = nums2[j++];
      } else {
        temp[last++] = nums1[i++];
      }
    }

    // nums1数组原数据有剩余
    while (i <= m - 1) {
      temp[last++] = nums1[i++];
    }

    // nums2数组原数据有剩余
    while (j <= n - 1) {
      temp[last++] = nums2[j++];
    }

    System.arraycopy(temp, 0, nums1, 0, m + n);
  }
}

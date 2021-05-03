package me.mingshan.leetcode;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组
 * <p>
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author hanjuntao
 */
public class L_88_MergeSortedArray {

  public static void main(String[] args) {
//    int[] nums1 = new int[13];
//    nums1[0] = 1;
//    nums1[1] = 3;
//    nums1[2] = 4;
//    nums1[3] = 6;
//    nums1[4] = 7;
//    nums1[5] = 9;
//
//    int[] nums2 = {2, 5, 7, 8, 10, 11, 23};
//
//    merge2(nums1, 6, nums2, nums2.length);
//    System.out.println(Arrays.toString(nums1));

    int[] nums3 = new int[2];
    nums3[0] = 2;

    int[] nums4 = {1};

    merge2(nums3, 1, nums4, nums4.length);
    System.out.println(Arrays.toString(nums3));
  }

  /**
   * 思路：新建一个数组，然后按照两个数组从前往后的顺序遍历，每次小的数组指针向前移动，当有一个数组遍历完后，
   * 剩下的另一个数组的元素直接拷贝到目标数组即可，注意
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
    while (i <= m - 1 && j <= n - 1) {
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

  /**
   * 不需要额外数组，从后往前遍历，如果遍历结束，nums2的指针没有置为0，那么直接将剩余0~j的元素拷贝到数组num1的0~j位置即可
   *
   * @param nums1
   * @param m
   * @param nums2
   * @param n
   */
  public static void merge2(int[] nums1, int m, int[] nums2, int n) {
    if (nums2 == null || nums2.length == 0) {
      return;
    }

    int i = m == 0 ? 0 : m - 1;
    int j = n - 1;

    int currIndex = m + n - 1;

    // 同步比较
    while (i >= 0 && j >= 0) {
      if (nums1[i] > nums2[j]) {
        nums1[currIndex] = nums1[i];
        i--;
      } else {
        nums1[currIndex] = nums2[j];
        j--;
      }

      currIndex--;
    }

    // 如果有nums2的j指针没有到0，那么将0~j的全部拷贝到nums1的前0~j位
    while (j >= 0) {
      nums1[j] = nums2[j];
      j--;
    }
  }
}

package me.mingshan.leetcode;

import java.util.Arrays;

/**
 *
 * https://leetcode-cn.com/problems/merge-sorted-array/
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

    int[] nums2 = {2, 5, 7, 8, 10, 11};

    merge(nums1, 6, nums2, nums2.length);
    System.out.println(Arrays.toString(nums1));
  }

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

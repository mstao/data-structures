package me.mingshan.algorithm.dynamic;

import java.util.Arrays;

/**
 * 给定一个整数数组（下标从 0 到 n-1， n 表示整个数组的规模），请找出该数组中的最长上升连续子序列。（最长上升连续子序列可以定义为从右到左或从左到右的序列。）
 *
 * https://www.lintcode.com/problem/397/
 *
 * https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/
 *
 * @author Walker Han
 * @date 2021/2/18 16:17
 */
public class LongestIncreasingContinuousSubsequence {


  public static void main(String[] args) {
    int[] nums = new int[]{1, 2, 3, 4};
    reverse(nums);
    System.out.println(Arrays.toString(nums));

    System.out.println(longestIncreasingContinuousSubsequence(nums));

    int[] nums2 = new int[]{2,2,2,2,2};
    System.out.println(longestIncreasingContinuousSubsequence(nums2));
  }

  /**
   *
   * @param nums
   * @return
   */
  public static int longestIncreasingContinuousSubsequence(int[] nums) {
    if (nums == null) {
      return 0;
    }

    if (nums.length <= 1) {
      return nums.length;
    }

    int result1 = calcu(nums);

    reverse(nums);

    int result2 = calcu(nums);

    return Math.max(result1, result2);
  }

  /**
   * 数组反转
   *
   * @param nums 数组
   */
  private static void reverse(int[] nums) {
    int i = 0;
    int j = nums.length - 1;

    while (i != j && i <= j) {
      int t = nums[i];
      nums[i] = nums[j];
      nums[j] = t;
      i++;
      j--;
    }
  }

  private static int calcu(int[] nums) {
    int result = 1;

    int[] f = new int[nums.length];

    for (int i = 0; i < nums.length; i++) {
      f[i] = 1;

      if (i == 0) {
        continue;
      }

      if (nums[i - 1] < nums[i]) {
        f[i] = f[i - 1] + 1;
      }

      if (f[i] > result) {
        result = f[i];
      }
    }

    return result;
  }
}

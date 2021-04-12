package me.mingshan.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class L_53_MaxSubArray {

  public static void main(String[] args) {
    int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
    System.out.println(maxSubArray2(nums)); // 6

//    int[] nums2 = {0};
//    System.out.println(maxSubArray2(nums2)); // 0
//
//    int[] nums3 = {0, 1};
//    System.out.println(maxSubArray2(nums3)); // 1
//
//    int[] nums4 = {0, -1};
//    System.out.println(maxSubArray2(nums4)); // 0
//
//    int[] nums5 = {-1, 2};
//    System.out.println(maxSubArray2(nums5)); // 2
//
//    int[] nums6 = {-1, -1};
//    System.out.println(maxSubArray2(nums6)); // -1
  }

  /**
   * 暴力求解，利用缓存,但时间复杂度比较高
   *
   * @param nums
   * @return
   */
  public static int maxSubArray(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int result = Integer.MIN_VALUE;

    Map<String, Integer> temp = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
      for (int j = i; j < nums.length; j++) {
        Integer beforeValue = temp.get((i + "") + "_" + ((j - 1) + ""));
        int value = 0;
        if (beforeValue == null) {
          for (int x = i; x <= j; x++) {
            value += nums[x];
          }
        } else {
          value = beforeValue + nums[j];
        }

        temp.put((i + "") + "_" + ((j) + ""), value);

        if (value > result) {
          result = value;
        }
      }
    }

    return result;
  }

  /**
   * 解法2：
   *
   *  利用动态规划思想，假设 f[i] 代表 以i为结尾的最长和
   *
   *  f[i] = max{f[i-1] + nums[i], nums[i]}
   *
   * @param nums
   * @return
   */
  public static int maxSubArray2(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int len = nums.length;

    // f[i] = max{f[i-1] + nums[i], nums[i]}
    int[] f = new int[len];

    f[0] = nums[0];

    for (int i = 1; i < len; i++) {
      f[i] = Math.max(f[i-1] + nums[i], nums[i]);
    }

    int result = Integer.MIN_VALUE;

    for (int i = 0; i < len; i++) {
      if (f[i] > result) {
        result = f[i];
      }
    }

    return result;
  }
}

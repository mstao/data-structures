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

    //System.out.println(longestIncreasingContinuousSubsequence(nums));
    System.out.println(longestIncreasingContinuousSubsequence2(nums));

    int[] nums2 = new int[]{2,2,2,2,2};
    //System.out.println(longestIncreasingContinuousSubsequence(nums2));
    System.out.println(longestIncreasingContinuousSubsequence2(nums2));
  }

  /**
   * 该题用动态规划思路去解比较简单，因为是连续递增子序列，那么对于任意一个以nums[j]的结尾的最长递增子序列，其最长递增子序列有两种情况：
   * 假设j的上一位为i (i + 1 = j)
   *
   * 如果出现 nums[i] < nums[j]， 那么就可以转化为以nums[i]结尾的最长子序列的长度 + 1
   * 否则以nums[j] 结尾的最长子序列长度就是当前本身，即为1
   * 根据上面的逻辑我们可以得到转移方程：
   *
   * 设f[j] 代表以nums[j] 结尾的最长递增子序列的长度
   *
   *
   * f[j] = {
   *         1,  nums[i] >= nums[j] && (i + 1 == j)
   *         f[i] + 1, nums[i] < nums[j] && (i + 1 == j)
   * }
   * 最终结果就是f数组里面的最大值。
   *
   * 作者：mingshan
   * 链接：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/solution/dong-tai-gui-hua-si-xiang-by-mingshan-ipox/
   * 来源：力扣（LeetCode）
   * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
   *
   * @param nums
   * @return
   */
  public static int longestIncreasingContinuousSubsequence2(int[] nums) {
    if (nums == null) {
      return 0;
    }

    if (nums.length <= 1) {
      return nums.length;
    }

    int len = nums.length;
    int result = 1;

    int[] f = new int[len + 1];

    f[0] = 0;
    f[1] = 1;

    for (int i = 1; i < len; i++) {
      if (nums[i] > nums[i - 1]) {
        f[i + 1] = f[i] + 1;
      } else {
        f[i + 1] = 1;
      }

      result = Math.max(result, f[i+1]);
    }

    return result;
  }

}

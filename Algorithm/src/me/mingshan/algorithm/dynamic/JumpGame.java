package me.mingshan.algorithm.dynamic;

/**
 * https://leetcode-cn.com/problems/jump-game/
 */
public class JumpGame {

  public static void main(String[] args) {
    int[] nums1 = {2,3,1,1,4};
    System.out.println(canJump(nums1));

    int[] nums2 = {3,2,1,0,4};
    System.out.println(canJump(nums2));
  }

  /**
   * nums 表示青蛙在某一个位置能够向前跳的距离
   *
   * f[j] 表示 青蛙能够跳到j的位置
   *
   * 解此题的关键是找到转移方程：
   *
   * f[j] = OR(0 <= i < j) [f[i] && (nums[i] + i >= j]
   *
   * 并且初始条件f[0] = true
   *
   * @param nums
   * @return
   */
  public static boolean canJump(int[] nums) {
    // f[j] 表示 青蛙能够跳到j的位置
    boolean[] f = new boolean[nums.length];

    f[0] = true;

    for (int i = 1; i < nums.length; i++) {
      f[i] = false;

      for (int j = 0; j < i; j++) {
        if (f[j] && (j + nums[j] >= i)) {
          f[i] = true;
          break;
        }
      }
    }

    return f[nums.length - 1];
  }

}

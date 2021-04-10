package me.mingshan.leetcode;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_70_ClimbStairs {

  public static void main(String[] args) {
    System.out.println(climbStairs(3));
    System.out.println(climbStairs(4));
    System.out.println(climbStairs(5));
    System.out.println(climbStairs(6));
    System.out.println(climbStairs(8));
  }

  /**
   * 直接动态规划即可
   *
   * 由题意可以只，最后一步可以是第n-1层跳上来的，也可以是第n-2层跳上来的。
   *
   * @param n
   * @return
   */
  public static int climbStairs(int n) {
    if (n == 0) {
      return 0;
    }

    if (n == 1) {
      return 1;
    }

    // f代表第n层的次数，由题意可以知，最后一步可以是第n-1层跳上来的，也可以是第n-2层跳上来的。
    // f[n] = f[n-1] + f[n-2];
    int[] f = new int[n+1];

    f[0] = 0;
    f[1] = 1;
    f[2] = 2;

    for (int i = 3; i <= n; i++) {
      f[i] = f[i-1] + f[i-2];
    }

    return f[n];
  }
}

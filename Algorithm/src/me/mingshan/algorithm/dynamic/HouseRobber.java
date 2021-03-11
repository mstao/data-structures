package me.mingshan.algorithm.dynamic;

/**
 * 假设你是一个专业的窃贼，准备沿着一条街打劫房屋。每个房子都存放着特定金额的钱。你面临的唯一约束条件是：相邻的房子装着相互联系的防盗系统，且 当相邻的两个房子同一天被打劫时，该系统会自动报警。
 *
 * 给定一个非负整数列表，表示每个房子中存放的钱， 算一算，如果今晚去打劫，在不触动报警装置的情况下, 你最多可以得到多少钱 。
 *
 * https://www.lintcode.com/problem/392/
 * https://leetcode-cn.com/problems/house-robber
 *
 */
public class HouseRobber {

  public static void main(String[] args) {
    int[] A1 = {3, 8, 4};
    int[] A2 = {2,1,1,2};
    System.out.println(houseRobber(A2));
  }

  /**
   * @param A: An array of non-negative integers
   * @return: The maximum amount of money you can rob tonight
   */
  public static long houseRobber(int[] A) {
    // write your code here

    if (A == null || A.length == 0) {
      return 0L;
    }

    int m = A.length;

    // f[i][j] 代表到i-1个房子金钱总数，j表示第i-1个房子有没有被打劫，0：没有；1代表被打劫
    int[][] f = new int[m+1][2];

    for (int i = 1; i <= m; i++) {
      // 如果第i-1 栋房子没偷，那么 第i-2栋房子可能偷了，也可没偷，找出最大值
      f[i][0] = Math.max(f[i-1][1], f[i-1][0]);
      // 如果第i-1 栋房子偷了，那么 第i-2栋房子必然是没有偷
      f[i][1] = f[i-1][0] + A[i-1];
    }

    return Math.max(f[m][0], f[m][1]);
  }
}

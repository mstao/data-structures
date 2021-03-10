package me.mingshan.algorithm.dynamic;

/**
 * 假设你是一个专业的窃贼，准备沿着一条街打劫房屋。每个房子都存放着特定金额的钱。你面临的唯一约束条件是：相邻的房子装着相互联系的防盗系统，且 当相邻的两个房子同一天被打劫时，该系统会自动报警。
 *
 * 给定一个非负整数列表，表示每个房子中存放的钱， 算一算，如果今晚去打劫，在不触动报警装置的情况下, 你最多可以得到多少钱 。
 *
 * https://www.lintcode.com/problem/392/
 *
 */
public class HouseRobber {

  /**
   * @param A: An array of non-negative integers
   * @return: The maximum amount of money you can rob tonight
   */
  public long houseRobber(int[] A) {
    // write your code here

    if (A == null || A.length == 0) {
      return 0L;
    }

    int m = A.length;

    // f[i][j] 代表到i-1个房子金钱总数，j表示第i-1个房子有没有被打劫，0：没有；1代表被打劫
    int[][] f = new int[m+1][2];

    for (int i = 0; i < m; i++) {

    }

    return 0L;
  }
}

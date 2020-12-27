package me.mingshan.algorithm.dynamic;

/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 * <p>
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color red;
 * costs[1][2] is the cost of painting house 1 with color green, and so on...
 * Find the minimum cost to paint all houses.
 * <p>
 * https://leetcode-cn.com/problemset/all/?search=256
 *
 * https://www.lintcode.com/problem/paint-house/
 */
public class PaintHouse {

  public static void main(String[] args) {
    int[][] cost = {
      {14, 2, 11},
      {11, 14, 5},
      {14, 3, 10}
    };

    System.out.println(minCost(cost)); // 10
  }

  /**
   * 转移方程，注意：cost[n-1][0]代表第n栋房子的红色的最小花费，这个不要搞错了
   *
   * f[n][0] = min{ f[n-1][1] + cost[n-1][0], f[n-1][2] + cost[n][0] }
   * f[n][1] = min{ f[n-1][0] + cost[n-1][1], f[n-1][2] + cost[n][1] }
   * f[n][2] = min{ f[n-1][0] + cost[n-1][2], f[n-1][1] + cost[n][2] }
   * <p>
   * 第n房子的最小花费为：min {f[n][0], f[n][1], f[n][2]}
   *
   * @param cost
   * @return
   */
  public static int minCost(int[][] cost) {
    if (cost == null) {
      return 0;
    }

    int m = cost.length;
    if (m == 0) {
      return 0;
    }

    // 多少种颜色
    int n = cost[0].length;

    // f[n] 代表第n
    int[][] f = new int[m + 1][n];

    // 第0栋房子最小花费为0

    for (int i = 1; i <= m; i++) {
      for (int j = 0; j < n; j++) {
        f[i][j] = Integer.MAX_VALUE;
        for (int k = 0; k < n; k++) {
          if (j == k) {
            continue;
          }

          int tempValue = f[i - 1][k] + cost[i - 1][j];

          f[i][j] = Math.min(f[i][j], tempValue);
        }
      }
    }

    int result = f[m][0];
    for (int i = 1; i < n; i++) {
      result = Math.min(result, f[m][i]);
    }

    return result;
  }
}

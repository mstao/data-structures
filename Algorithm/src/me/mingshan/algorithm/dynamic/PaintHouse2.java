package me.mingshan.algorithm.dynamic;

/**
 * 这里有n个房子在一列直线上，现在我们需要给房屋染色，共有k种颜色。每个房屋染不同的颜色费用也不同，你希望每两个相邻的房屋颜色不同
 *
 * 费用通过一个nxk 的矩阵给出，比如cost[0][0]表示房屋0染颜色0的费用，cost[1][2]表示房屋1染颜色2的费用。
 *
 * https://www.lintcode.com/problem/516/
 */
public class PaintHouse2 {

  public static void main(String[] args) {
    int[][] cost = {
        {14, 2, 11},
        {11, 14, 5},
        {14, 3, 10}
    };

    System.out.println(minCostII(cost)); // 10
  }

  /**
   * @param costs: n x k cost matrix
   * @return: an integer, the minimum cost to paint all houses
   */
  public static int minCostII(int[][] costs) {
    // write your code here

    if (costs == null || costs.length == 0) {
      return 0;
    }

    // 房子数
    int m = costs.length;
    // 颜色种类数
    int k = costs[0].length;

    // 转移方程, n 为前n房子的最小花费（n从1开始）
    // f[n][i] = [j != i]  const[n-1][i] + min{f[n-1][0], f[n-1][2] ... f[n-1][k]}

    int[][] f = new int[m+1][k];

    for (int i = 1; i <= m; i++) {
      for (int j = 0; j < k; j++) {
        f[i][j] = Integer.MAX_VALUE;

        for (int z = 0; z < k; z++) {
          if (z == j) {
            continue;
          }

          int v = f[i-1][z] + costs[i-1][j];
          if (f[i][j] > v) {
            f[i][j] = v;
          }
        }
      }
    }

    int result = f[m][0];

    for (int i = 1; i < k; i++) {
      if (f[m][i] < result) {
        result = f[m][i];
      }
    }

    return result;
  }
}

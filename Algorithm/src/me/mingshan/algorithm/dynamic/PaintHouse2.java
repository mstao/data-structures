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


    int[] m = {4,3,1,5,2};

    // 第一小， 第二小
    int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
    // 第一小颜色位置， 第二小颜色位置
    int k1 = 0, k2 = 0;

    // 算出{f[i-1][0], f[i-1][2] ... f[i-1][k]} 的第一小，记录哪个颜色，第二小，记录哪个颜色，下面可以直接用
    for (int q = 0; q < m.length; q++) {
      if (q == 0) {
        min1 = min2 = m[q];
        k1 = k2 = 0;
      }
      int curr = m[q];
      // 选判断是否比次小值小
      int oldMin1 = min1;
      int oldK1 = k1;
      if (curr < min1) {
        min1 = curr;
        k1 = q;
      }

      // 当前值比最小值大，且旧的min2大于当前值
      if (curr > min1 && min2 > curr) {
        min2 = curr;
        k2 = q;
      }

      // 当前值比最小值原来的值（当前第二小）大，且当前值大于min2
      if (curr > oldMin1) {
        min2 = oldMin1;
        k2 = oldK1;
      }
    }

    System.out.println("min1 = " + min1 + ", k1 = " + k1);
    System.out.println("min1 = " + min2 + ", k1 = " + k2);
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

  /**
   * @param costs: n x k cost matrix
   * @return: an integer, the minimum cost to paint all houses
   */
  public static int minCostII2(int[][] costs) {
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
      // 第一小， 第二小
      int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
      // 第一小颜色位置， 第二小颜色位置
      int k1 = 0, k2 = 0;

      // 算出{f[i-1][0], f[i-1][2] ... f[i-1][k]} 的第一小，记录哪个颜色，第二小，记录哪个颜色，下面可以直接用
      for (int q = 0; q < k; q++) {
        int curr = f[i-1][q];
        // 选判断是否比次小值小
        int oldMin1 = min1;
        int oldK1 = k1;
        if (curr < min1) {
          min1 = curr;
          k1 = q;
        }

        if (oldMin1 < min2) {
          min2 = oldMin1;
          k2 = oldK1;
        }

      }

      for (int j = 0; j < k; j++) {
        f[i][j] = Integer.MAX_VALUE;

        // TODO 直接得出结果，而不需要重复比较
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

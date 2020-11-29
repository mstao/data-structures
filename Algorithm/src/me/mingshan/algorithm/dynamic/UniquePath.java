package me.mingshan.algorithm.dynamic;

/**
 * 求有多少不同路径
 *
 * https://leetcode-cn.com/problems/unique-paths/
 */
public class UniquePath {
  public static void main(String[] args) {
    System.out.println(uniquePaths(3, 2));
  }

  /**
   * 求有多少不同路径
   *
   * @param m 行数
   * @param n 列素有
   * @return 路径数
   */
  public static int uniquePaths(int m, int n) {
    // f[i][j] 表示从[0][0] 到[i][j]的路径数
    int[][] f = new int[m][n];

    f[0][0] = 0;

    for (int i = 0; i < m; i++) {  // 从上到下
      for (int j = 0; j < n; j++) {
        if (i == 0 || j == 0) {
          f[i][j] = 1;
        } else {
          f[i][j] = f[i - 1][j] + f[i][j - 1];
        }
      }
    }

    return f[m-1][n-1];
  }
}

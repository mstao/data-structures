package me.mingshan.algorithm.dynamic;

/**
 * 表格有障碍物
 *
 * https://leetcode-cn.com/problems/unique-paths-ii/
 */
public class UniquePath2 {

  public static void main(String[] args) {
//    int[][] g1 = {{0,0,0},{0,1,0},{0,0,0}};
//    System.out.println(uniquePathsWithObstacles(g1));

    int[][] g2 = {{0,1},{0,0}};
    System.out.println(uniquePathsWithObstacles(g2));

//    int[][] g3 = {{0,1},{1,1},{0,0}};
//    System.out.println(uniquePathsWithObstacles(g3));
  }

  public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
    if (obstacleGrid == null || obstacleGrid[0].length == 0) {
      return 0;
    }
    // 行数
    int m = obstacleGrid.length;
    // 列数
    int n = obstacleGrid[0].length;

    // 如果第一个位置数组值是1，代表为障碍物，直接返回0
    if (obstacleGrid[0][0] == 1) {
      return 0;
    }

    // 如果最后一个位置数组值是1，代表为障碍物，直接返回0
    if (obstacleGrid[m - 1][n - 1] == 1) {
      return 0;
    }

    // 表示从[0][0] 到[i][j]的路径数
    int[][] f = new int[m][n];

    f[0][0] = 1;

    // 转移方程 f[i][j] = f[i-1][j] + f[i][j-1]
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (obstacleGrid[i][j] == 1) {
          f[i][j] = 0;
        } else {
          f[i][j] = 0;

          if (i - 1 >= 0) {
            f[i][j] += f[i - 1][j];
          }

          if (j - 1 >= 0) {
            f[i][j] += f[i][j - 1];
          }
        }
      }
    }

    return f[m - 1][n - 1];
  }
}

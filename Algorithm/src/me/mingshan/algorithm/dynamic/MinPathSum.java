package me.mingshan.algorithm.dynamic;

/**
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * https://leetcode-cn.com/problems/minimum-path-sum/
 */
public class MinPathSum {

  public static void main(String[] args) {
    int[][] a1 = {
        {1,3,1},
        {1,5,1},
        {4,2,1}
    };

    int[][] a2 = {
        {1,2,3},
        {4,5,6}
    };

    System.out.println(minPathSum(a1));
    System.out.println(minPathSum(a2));
  }

  /**
   * 解题思路：
   *
   * # 确定最终状态
   *
   *   最后一步必然是 m,n 那么前一步必然是（m-1， n）权值为A 或者（m， n-1） 权值为B
   *   我们只需要知道前面的整体最小即可，这样问题规模就减小了。
   *
   * # 转移方程
   *
   *   f[m][n] = V[m][n] + min{f[m-1][n], f[m][n-1]}
   *
   * # 初始值和边界条件
   *   坐标型动态规划最重要的绝对不能下标越界，对于此题而言，如果是在最上侧，还是最左测，计算结果都是可直接预测的（因为只有一条路径）；
   *   初始值：如果在（0，0）位置，结果就是该点对应的权值
   *
   *
   * @param grid
   * @return
   */
  public static int minPathSum(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int m = grid.length;
    int n = grid[0].length;

    int[][] f = new int[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 && j == 0) {
          f[i][j] = grid[i][j];
        } else if (i == 0) {
          f[i][j] = grid[i][j] + f[0][j-1];
        } else if (j == 0) {
          f[i][j] = grid[i][j] + f[i-1][0];
        } else {
          f[i][j] = grid[i][j] + Math.min(f[i-1][j], f[i][j-1]);
        }
      }
    }

    return f[m-1][n-1];
  }

}

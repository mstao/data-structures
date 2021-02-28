package me.mingshan.algorithm.dynamic;

/**
 * https://www.lintcode.com/problem/553/
 *
 * 给定一个二维矩阵, 每一个格子可能是一堵墙 W,或者 一个敌人 E 或者空 0 (数字 '0'), 返回你可以用一个炸弹杀死的最大敌人数.
 * 炸弹会杀死所有在同一行和同一列没有墙阻隔的敌人。 由于墙比较坚固，所以墙不会被摧毁.
 *
 * 你只能在空的地方放置炸弹.
 *
 * @author Walker Han
 * @date 2021/2/26 11:09
 */
public class BombEnemy {

  public static void main(String[] args) {
    char[][] grid1 = {
        {'0', 'E','0', '0'},
        {'E', '0','W', 'E'},
        {'0', 'E','0', '0'},
    };

    // ["WWWWWWWWWW","EEEEEEEEEE","WWWWWWWWWW","0000000000","WWWWWWWWWW","EEEEEEEEEE"]
    char[][] grid2 = {
        {'0', 'E'}
    };


    // ["W00000EEEEEEEE000000WWW0WWW00W0W0WEEEE0000EW00W"]
    char[][] grid3 = {{'W', '0', '0', '0', '0', '0', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E'}};

//    System.out.println(maxKilledEnemies(grid1));
//    System.out.println(maxKilledEnemies2(grid1));
//    System.out.println(maxKilledEnemies(grid2));
    System.out.println(maxKilledEnemies2(grid3));
  }

  /**
   * @param grid: Given a 2D grid, each cell is either 'W', 'E' or '0'
   * @return: an integer, the maximum enemies you can kill using one bomb
   */
  public static int maxKilledEnemies(char[][] grid) {
    // write your code here
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }

    // 行数
    int m = grid.length;
    // 列数
    int n = grid[0].length;

    // 杀死敌人的最大数
    int result = 0;

    // 计算
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        int killedEnemies = maxKilledEnemies(grid, i, j);
        if (killedEnemies > result) {
          result = killedEnemies;
        }
      }
    }

    return result;
  }

  /**
   * 计算当前行列的值
   *
   * @param grid 传入表格
   * @param row 当前行
   * @param col 当前列
   * @return 最大敌人数
   */
  public static int maxKilledEnemies(char[][] grid, int row, int col) {
    if (grid[row][col] != '0') {
      return 0;
    }

    // 行数
    int m = grid.length;
    // 列数
    int n = grid[0].length;

    int result = 0;

    // 往上, 从下往上计算
    for (int i = row - 1; i >= 0; i--) {
      char curr = grid[i][col];
      if (curr == 'W') {
        break;
      }

      if (curr == 'E') {
        result++;
      }
    }

    // 往下，从上往下计算
    for (int i = row + 1; i < m; i++) {
      char curr = grid[i][col];
      if (curr == 'W') {
        break;
      }

      if (curr == 'E') {
        result++;
      }
    }

    // 往左，从右往左计算
    for (int i = col - 1; i >= 0; i--) {
      char curr = grid[row][i];
      if (curr == 'W') {
        break;
      }

      if (curr == 'E') {
        result++;
      }
    }

    // 往右，从左往右计算
    for (int i = col + 1; i < n; i++) {
      char curr = grid[row][i];
      if (curr == 'W') {
        break;
      }

      if (curr == 'E') {
        result++;
      }
    }

    return result;
  }


  /**
   * 动态规划：
   *
   * 1. 确定状态
   *   假设任何一格都可以放炸弹，但对于特殊情况
   *      1. 当前格为墙，那么不能炸死任何人
   *      2. 当前格为E(有一个敌人)，那么炸死的人 为相邻的格炸死人数 + 1
   *      3. 如果为空地，那么炸死的人数为为相邻的格炸死人数
   *
   *
   *
   * @param grid: Given a 2D grid, each cell is either 'W', 'E' or '0'
   * @return: an integer, the maximum enemies you can kill using one bomb
   */
  public static int maxKilledEnemies2(char[][] grid) {
    // write your code here
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }

    // 行数
    int m = grid.length;
    // 列数
    int n = grid[0].length;

    // f[i][j]，代表i,j位置炸死的敌人数
    int[][] f = new int[m][n];
    int[][] results = new int[m][n];

    // 计算上（从上往下算）
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        char curr = grid[i][j];
        if (curr == 'W') {
          f[i][j] = 0;
        } else {
          f[i][j] = 0;
          if (curr == 'E') {
            f[i][j] = 1;
          }
          if (i > 0) {
            f[i][j] += f[i-1][j];
          }
        }

        results[i][j] += f[i][j];
      }
    }

    // 计算下（从下往上算）
    for (int i = m-1 ; i >= 0; i--) {
      for (int j = 0; j < n; j++) {
        char curr = grid[i][j];
        if (curr == 'W') {
          f[i][j] = 0;
        } else {
          f[i][j] = 0;
          if (curr == 'E') {
            f[i][j] = 1;
          }
          if ((i+1) < m) {
            f[i][j] += f[i+1][j];
          }
        }

        results[i][j] += f[i][j];
      }
    }

    // 计算左（从左往右算）
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        char curr = grid[i][j];
        if (curr == 'W') {
          f[i][j] = 0;
        } else {
          f[i][j] = 0;
          if (curr == 'E') {
            f[i][j] = 1;
          }
          if (j > 0) {
            f[i][j] += f[i][j-1];
          }
        }

        results[i][j] += f[i][j];
      }
    }

    // 计算右（从右往左算）
    for (int i = 0; i < m; i++) {
      for (int j = n - 1; j >= 0 ; j--) {
        char curr = grid[i][j];
        if (curr == 'W') {
          f[i][j] = 0;
        } else {
          f[i][j] = 0;
          if (curr == 'E') {
            f[i][j] = 1;
          }
          if ((j+1) < n) {
            f[i][j] += f[i][j+1];
          }
        }

        results[i][j] += f[i][j];
      }
    }

    int result = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        char curr = grid[i][j];
        if (curr == '0') {
          int currResult = results[i][j];
          if (currResult > result) {
            result = currResult;
          }
        }
      }
    }

    // 计算
    return result;
  }

}

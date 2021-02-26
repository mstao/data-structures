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


    //System.out.println(maxKilledEnemies(grid1));
    System.out.println(maxKilledEnemies(grid2));
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

    System.out.println("row: " + row + ", col: " + col);

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
      System.out.println("curr: " + curr);
      if (curr == 'W') {
        break;
      }

      if (curr == 'E') {
        result++;
      }
    }

    System.out.println("result: " + result);
    return result;
  }
}

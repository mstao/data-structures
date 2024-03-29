package me.mingshan.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 200. 岛屿数量
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 *
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 参考：https://mp.weixin.qq.com/s/it_asGIDqQBoOrzdL1j2CQ
 */
public class L_200_MumIslands {

  public static void main(String[] args) {
    char[][] grid = {
        {'1', '1', '1', '1', '0'},
        {'1', '1', '0', '1', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '0', '0', '0'}
    };

    //System.out.println(numIslands2(grid));

    char[][] grid2 = {
        {'1','1','1'},
        {'0','1','0'},
        {'1','1','1'},
    };
    //System.out.println(numIslands(grid2));
    System.out.println(numIslands2(grid2));
  }

  /**
   * 利用dfs
   *
   * @param grid
   * @return
   */
  public static int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }

    // 行数
    int rowNum = grid.length;
    // 列数
    int colNum = grid[0].length;
    int result = 0;

    // 采用dfs遍历，每一个遍历过的，全部置为0
    // 从上往下探测
    for (int i = 0; i < rowNum; i++) {
      for (int j = 0; j < colNum; j++) {
        if (grid[i][j] == '1') {
          dfs(grid, i, j);
          result++;
        }
      }
    }

    return result;
  }

  /**
   * 从i，j开始向左，向右，向下开始探测，遇到0该方向停止
   *
   * @param grid 表格
   * @param i 行
   * @param j 列
   */
  private static void dfs(char[][] grid, int i, int j) {
    // 行数
    int rowNum = grid.length;
    // 列数
    int colNum = grid[0].length;

    if (i < 0 || j < 0 || i >= rowNum || j >= colNum || grid[i][j] == '0') {
      return;
    }

    grid[i][j] = '0';

    dfs(grid, i, j + 1);
    dfs(grid, i, j - 1);
    dfs(grid, i + 1, j);
    dfs(grid, i - 1, j);
  }

  /**
   * 利用bfs，广度优先遍历, 上下左右依次遍历即可
   *
   *
   *
   * @param grid
   * @return
   */
  public static int numIslands2(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) {
      return 0;
    }

    // 行数
    int rowNum = grid.length;
    // 列数
    int colNum = grid[0].length;
    int result = 0;

    Queue<Position> queue = new LinkedList<>();

    for (int i = 0; i < rowNum; i++) {
      for (int j = 0; j < colNum; j++) {
        if (grid[i][j] == '1') {
          result++;
          grid[i][j] = '0';

          queue.add(new Position(i, j));
          while (!queue.isEmpty()) {
            Position position = queue.poll();

            int row = position.row;
            int col = position.col;

            // 上
            if (row - 1 >= 0 && grid[row - 1][col] == '1') {
              queue.add(new Position(row - 1, col));
              grid[row - 1][col] = '0';
            }

            // 下
            if (row + 1 < rowNum && grid[row + 1][col] == '1') {
              queue.add(new Position(row + 1, col));
              grid[row + 1][col] = '0';
            }

            // 左
            if (col - 1 >= 0 && grid[row][col - 1] == '1') {
              queue.add(new Position(row, col - 1));
              grid[row][col - 1] = '0';
            }

            // 右
            if (col + 1 < colNum && grid[row][col + 1] == '1') {
              queue.add(new Position(row, col + 1));
              grid[row][col + 1] = '0';
            }

          }
        }
      }
    }

    return result;
  }

  private static final class Position {
    int row;
    int col;

    public Position(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }

}
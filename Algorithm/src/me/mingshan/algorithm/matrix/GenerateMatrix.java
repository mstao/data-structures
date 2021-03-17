package me.mingshan.algorithm.matrix;

import java.util.Arrays;

/**
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * https://leetcode-cn.com/problems/spiral-matrix-ii/
 *
 */
public class GenerateMatrix {

  public static void main(String[] args) {
    System.out.println(Arrays.deepToString(generateMatrix(2)));
  }

  public static int[][] generateMatrix(int n) {
    if (n <= 0) {
      return null;
    }

    int[][] result = new int[n][n];

    int start = 0;
    int begin = 1;

    while (n > start * 2) {
      int colP = n - 1;
      int rowP = n - 1;

      System.out.println(111);

      // 先打印上行
      for (int i = start; i <= colP - start; i++) {
        result[start][i] = begin++;
      }

      // 再打印右边竖行
      for (int i = start + 1; i <= rowP - start; i++) {
        result[i][colP - start] = begin++;
      }

      // 再打印最下面的
      for (int i = colP - start - 1; i >= start; i--) {
        result[rowP - start][i] = begin++;
      }

      // 最后打印左侧的
      for (int i = rowP - start - 1; i > start; i--) {
        result[i][start] = begin++;
      }

      start++;
    }

    return result;
  }
}

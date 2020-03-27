package me.mingshan.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/spiral-matrix/
 */
public class L_54_SpiralMatrix {

  public static void main(String[] args) {
    int[][] source = new int[][]{
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 16},
    };

    int[][] source2 = new int[][]{
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12}
    };

    int[][] source3 = new int[][]{
        {7},
        {9},
        {6}
    };

    int[][] source4 = new int[][]{
        {1, 2, 3, 4}
    };

    System.out.println(spiralOrder(source));
    System.out.println(spiralOrder(source2));
    System.out.println(spiralOrder(source3));
    System.out.println(spiralOrder(source4));
  }

  public static List<Integer> spiralOrder(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return Collections.emptyList();
    }

    int rows = matrix.length;
    int columns = matrix[0].length;
    int start = 0;
    List<Integer> result = new ArrayList<>();

    while (columns > start * 2 && rows > start * 2) {
      printCircle(matrix, columns, rows, start, result);
      start++;
    }

    return result;
  }

  private static void printCircle(int[][] source, int columns, int rows, int start, List<Integer> result) {
    int colP = columns - 1;
    int rowP = rows - 1;

    int endY = colP - start;
    int endX = rowP - start;

    // 先打印上行
    for (int i = start; i <= endY; i++) {
      result.add(source[start][i]);
    }

    if (start == endX) {
      return;
    }

    // 再打印右边竖行
    for (int i = start + 1; i <= endX; i++) {
      result.add(source[i][colP - start]);
    }

    if (start == endY) {
      return;
    }

    // 再打印最下面的
    for (int i = endY - 1; i >= start; i--) {
      result.add(source[rowP - start][i]);
    }

    // 最后打印左侧的
    for (int i = endX - 1; i > start; i--) {
      result.add(source[i][start]);
    }
  }
}

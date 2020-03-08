package me.mingshan.algorithm.matrix;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
 *
 */
public class PrintMatrix {

  public static void main(String[] args) {
    int[][] source = new int[][]{
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 16},
    };

    //printRow(source, 0, 0, 3);
    //printColumn(source, 0, 0, 3);

    //printCircle(source, 4, 4, 0);

    solution(source, 4, 4);
  }

  public static void solution(int[][] source, int columns, int rows){
    if (source == null || source.length == 0) {
      return;
    }

    int start = 0;

    while (columns > start * 2 && rows > start * 2) {
      printCircle(source, columns, rows, start);
      start++;
    }
  }

  private static void printCircle(int[][] source, int columns, int rows, int start) {
    int colP = columns - 1;
    int rowP = rows - 1;

    // 先打印上行
    for (int i = start; i <= colP - start; i++) {
      System.out.print(source[start][i] + " ");
    }

    System.out.println();

    // 再打印右边竖行
    for (int i = start + 1; i <= rowP - start; i++) {
      System.out.print(source[i][colP - start] + " ");
    }

    System.out.println();

    // 再打印最下面的
    for (int i = colP - start - 1; i >= start; i--) {
      System.out.print(source[rowP - start][i] + " ");
    }

    System.out.println();

    // 最后打印左侧的
    for (int i = rowP - start - 1; i > start; i--) {
      System.out.print(source[i][start] + " ");
    }
  }

  private static void printRow(int[][] source, int row, int s, int t) {
    for (int i = s; i <= t; i++) {
      System.out.println(source[row][i]);
    }
  }


  private static void printColumn(int[][] source, int column, int s, int t) {
    for (int i = s; i <= t; i++) {
      System.out.println(source[i][column]);
    }
  }
}

package me.mingshan.leetcode;

/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_74_SearchMatrix {

  public static void main(String[] args) {
   int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};

    System.out.println(searchMatrix(matrix,31));
  }

  /**
   * S型排列，直接用二分查找就可以
   *
   * index  = 第m行 * 总共多少列n  + 当前位置p
   *
   * @param matrix
   * @param target
   * @return
   */
  public static boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) {
      return false;
    }

    // 行
    int m = matrix.length;
    // 列
    int n = matrix[0].length;

    int maxLength = m * n;

    int[] source = new int[maxLength];

    int x = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        source[x] = matrix[i][j];
        x++;
      }
    }

    return search(source, target, 0, maxLength - 1);
  }

  private static boolean search(int[] source, int target, int low, int high) {
    int mid = (high + low) / 2;

    if (low == high) {
      return target == source[low];
    }

    if (target > source[mid]) {
      return search(source, target, low + 1, high);
    } else if (target < source[mid]) {
      return search(source, target, low, high - 1);
    } else{
      return true;
    }
  }


}

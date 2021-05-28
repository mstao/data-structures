package me.mingshan.leetcode;

/**
 * 74.搜索二维矩阵
 *
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
//   int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
//
//    System.out.println(searchMatrix2(matrix,31));
//
//    int[][] matrix2 = {{1}};
//
//    System.out.println(searchMatrix2(matrix2,31));


    int[][] matrix3 = {{1,1},{2,2}};
    System.out.println(searchMatrix2(matrix3,2));
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
      return search(source, target, mid + 1, high);
    } else if (target < source[mid]) {
      return search(source, target, low, mid - 1);
    } else{
      return true;
    }
  }

  /**
   * 时间复杂度为O(log2n)，充分利用二分查找的特性
   *
   * @param matrix
   * @param target
   * @return
   */
  public static boolean searchMatrix2(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) {
      return false;
    }

    // 行
    int m = matrix.length;
    // 列
    int n = matrix[0].length;

    int maxLength = m * n;

    return search(matrix, target, 0, maxLength - 1);
  }

  private static boolean search(int[][] nums, int target, int low, int high) {
    if (nums.length == 1 && nums[0].length == 1) {
      return target == nums[0][0];
    }

    int mid = (high + low) / 2;

    // mid 对应的位置
    int midRow = mid / (nums[0].length);
    int midCol = mid % (nums[0].length);

    // 如何判断终止运行呢？

    int lowRow = low / (nums[0].length);
    int lowCol = low % (nums[0].length);

    int highRow = high / (nums[0].length);
    int highCol = high % (nums[0].length);

    if (target == nums[lowRow][lowCol]) {
      return true;
    }

    if (target == nums[highRow][highCol]) {
      return true;
    }

    if (low == high - 1 || low == high) {
      return target == nums[lowRow][lowCol];
    } else if (low == high - 2) {
      int low1Row = (low + 1) / (nums[0].length);
      int low1Col = (low + 1) % (nums[0].length);
      return target == nums[low1Row][low1Col];
    }

    if (target > nums[midRow][midCol]) {
      return search(nums, target, mid + 1, high);
    } else if (target < nums[midRow][midCol]) {
      return search(nums, target, low, mid - 1);
    } else{
      return true;
    }
  }
}

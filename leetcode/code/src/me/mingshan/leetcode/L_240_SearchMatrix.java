package me.mingshan.leetcode;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_240_SearchMatrix {
  public static void main(String[] args) {
//    int[][] matrix = {{1,4,7,11},{2,5,8,12},{3,6,9,16},{10,13,14,17},{18,21,23,26}};
//
//    System.out.println(searchMatrix(matrix, 20)); // false
//
//    int[][] matrix2 = {{20}};
//
//    System.out.println(searchMatrix(matrix2, 20)); //true
//
//    int[][] matrix3 = {{1,1}};
//
//    System.out.println(searchMatrix(matrix3, 0)); // false
//
//
//    int[][] matrix4 = {{1}, {2}};
//
//    System.out.println(searchMatrix(matrix4, 2)); // true
//
//    int[][] matrix5 = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
//    System.out.println(searchMatrix(matrix5, 20)); // true

//    int[][] matrix6 = {{1,4},{2,5}};
//    System.out.println(searchMatrix(matrix6, 5)); // true

//    int[][] matrix7 = {{5,6,10,14},{6,10,13,18},{10,13,18,19}};
//    System.out.println(searchMatrix3(matrix7, 14)); // true

//    int[][] matrix8 = {{-5}};
//    System.out.println(searchMatrix3(matrix8, -5));

    int[][] matrix9 = {{1,1}};
    System.out.println(searchMatrix3(matrix9, 2));
  }

  /**
   * 整体思路是尽可能多的排除掉无关 行/列 ，可以从 第一排最后一个/第一列最后一个 开始搜索，这里选择从 第一排最后一个 开始
   * 由于行列规则等价，搜索策略 先按行排除/按列排除 也是等价的，这里选择 按行排除
   * 搜索规则：小于 target 则向左搜索，大于 则向下搜索，可以保证 global search
   * 若超出 矩阵大小 则意味着没有匹配 target，输出 False
   *
   * 作者：eloise-1
   * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/solution/si-lu-qing-xi-zhe-xian-sou-suo-by-eloise-kisj/
   * 来源：力扣（LeetCode）
   * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
   *
   * @param matrix
   * @param target
   * @return
   */
  public static boolean searchMatrix3(int[][] matrix, int target) {
    if (matrix == null || matrix[0].length == 0) {
      return false;
    }

    int m = matrix.length;
    int n = matrix[0].length;

    int currRow = 0;
    int currCol = n - 1;

    while (currRow < m && currCol >= 0) {
      if (matrix[currRow][currCol] == target) {
        return true;
      }

      // 当前值大于target，向左进行搜索
      if (matrix[currRow][currCol] > target) {
        currCol--;
      } else {
        // 当前值小于target，向下进行搜索
        currRow++;
      }
    }

    return false;
  }












  public static boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) {
      return false;
    }

    int m = 0;
    int n = matrix[0].length - 1;
    while (m < matrix.length && n >= 0) {
      if (matrix[m][n] == target) {
        return true;
      } else if (matrix[m][n] > target) {
        n--;
      } else {
        m++;
      }
    }
    return false;
  }

  /**
   * 解法错误
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

    if (m == 1 && n == 1) {
      return target == matrix[0][0];
    }

    return search(matrix, target, 0, 0, m - 1, n -1);
  }

  private static boolean search(int[][] matrix, int target, int lowRow, int lowCol, int heightRow, int heightCol) {
    if (matrix == null || matrix.length == 0) {
      return false;
    }

    if (lowRow < 0 || lowCol < 0) {
      return false;
    }

    if (lowRow == 0 && lowCol == 0 && heightRow == 0 && heightCol == 0) {
      return target == matrix[0][0];
    }

    if (matrix[lowRow][lowCol] == target || matrix[heightRow][heightCol] == target) {
      return true;
    }

    if ((heightRow - lowRow == 1 && heightCol - lowCol == 1)) {
      // 从最小的往后

      for (int i = lowCol; i < matrix[0].length; i++) {
        if (matrix[lowRow][i] == target) {
          return true;
        }
      }

      // 从最大的往前，全部遍历
      for (int i = 0; i < heightCol; i++) {
        if (matrix[heightRow][i] == target) {
          return true;
        }
      }

      return false;
    }

    if ((lowRow == heightRow) && (heightCol - lowCol == 1)) {
      return matrix[lowRow][lowCol] == target || matrix[lowRow][heightCol] == target;
    }

    if ((lowCol == heightCol) && (heightRow - lowRow == 1)) {
      return matrix[lowRow][lowCol] == target || matrix[heightRow][lowCol] == target;
    }

    int midRow = (lowRow + heightRow) / 2;

    int midCol = (lowCol + heightCol) / 2;

    if (matrix[midRow][midCol] == target) {
      return true;
    } else if (matrix[midRow][midCol] > target) {
      // 说明midRow， midCol的处在方框最右下端，值最大
      for (int i = 0; i < heightCol; i++) {
        if (matrix[heightRow][i] == target) {
          return true;
        }
      }

      return search(matrix, target, lowRow, lowCol, midRow, midCol);
    } else {
      // 说明midRow， midCol的处在方框最左上端，值最小
      // 缩小范围之前，需要先判断原先最小的，有没有值比较大
      for (int i = lowCol; i < matrix[0].length; i++) {
        if (matrix[lowRow][i] == target) {
          return true;
        }
      }

      return search(matrix, target, midRow, midCol, heightRow, heightCol);
    }
  }
}

package me.mingshan.leetcode;

/**
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * <p>
 * https://leetcode-cn.com/problems/maximal-square/
 *
 * @author hanjuntao
 * @date 2022/2/19
 */
public class L_221_MaximalSquare {

    public static void main(String[] args) {
//        char[][] a1 = {{'0', '1'}, {'1', '0'}};
//        int i = maximalSquare(a1);
//        System.out.println(i);
//
//        char[][] a2 = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
//        int i2 = maximalSquare(a2);
//        System.out.println(i2);
//
//
//        char[][] a3 = {{ '1', '0'}};
//        int i3 = maximalSquare(a3);
//        System.out.println(i3);
//
//        char[][] a4 = {{ '1', '1'}, { '1', '1'}};
//        int i4 = maximalSquare(a4);
//        System.out.println(i4);

        // [['1','1','1','1','1'],['1','1','1','1','1'],['0','0','0','0','0'],['1','1','1','1','1'],['1','1','1','1','1']]

        char[][] a5 = {{'1','1','1','1','1'},{'1','1','1','1','1'},{'0','0','0','0','0'},{'1','1','1','1','1'},{'1','1','1','1','1'}};
        int i5 = maximalSquare(a5);
        System.out.println(i5);
    }

    /**
     * 思路：
     * <p>
     * 先从第一行从左往右遍历,
     * 要成为正方形，那么
     *
     * @param matrix
     * @return
     */
    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix[0].length == 0) {
            return 0;
        }

        // 行
        int row = matrix.length;

        // 列
        int col = matrix[0].length;

        int result = 0;

        int tempLen = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1') {
                    tempLen++;

                    // 计算是否可以形成正方形
                    Integer endJ = isSquare(i, j, tempLen, matrix);

                    if (endJ == null) {
                        result = Math.max(tempLen * tempLen, result);
                        tempLen = j == col - 1 ? 0 : tempLen;
                    } else {
                        // 缩短 tempLen
                        tempLen = j == endJ ? 0 :  tempLen - (j - endJ - 1);
                    }

                    result = Math.max(result, 1);
                } else {
                    tempLen = 0;
                }
            }
        }

        return result;
    }

    /**
     * 如果想是正方形，那么必须
     *
     * @param i
     * @param j
     * @param tempLen
     * @param matrix
     * @return
     */
    private static Integer isSquare(int i, int j, int tempLen, char[][] matrix) {
        // 行
        int row = matrix.length;
        // 列
        int col = matrix[0].length;

        // 只有一个
        if (j == (col - 1) && tempLen == 1) {
            return null;
        }

        // 开始的列
        int beginJ = j - tempLen + 1;

        Integer zeroJ = null;

        int nextRowIndex = Math.min(i + tempLen - 1, row - 1);

        // 如果是最后一行
        if (i == row - 1) {
            return j;
        }

        for (int x = i + 1; x <= nextRowIndex; x++) {
            for (int y = beginJ; y <= j; y++) {
                if (matrix[x][y] == '0') {
                    zeroJ = y;
                }
            }
        }

        return zeroJ;
    }
}

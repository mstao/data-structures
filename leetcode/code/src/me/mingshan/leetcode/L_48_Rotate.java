package me.mingshan.leetcode;

/**
 *
 * 给定一个 n x n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-image
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * https://leetcode-cn.com/problems/rotate-image/
 *
 * @author hanjuntao
 * @date 2022/1/3
 */
public class L_48_Rotate {
    /**
     * 解题思路：
     *
     * 仔细观察几个图可知，当我们每次要计算一个位置上元素旋转后的另一个位置，都有上下左右四个方向的元素相互影响，这四个点形成一个新的正方形，
     * 所有我们每次都以四个点为整体来移动，那么当第一行的元素全部移动完成，整个矩阵也旋转完成。
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        // 行数 / 列数
        int m = matrix.length;

        for (int i = 0; i < m; i++) {
            // 第一行当前元素
            int currItem = matrix[i][0];

            // 左边的元素
            int leftItem = matrix[0][m - 1 - i];

            // 下边的元素
            int downItem = matrix[m-1][m - 1 - i];

            // 右边的元素
            int rightItem = matrix[i][m-1];

            // 先交换四个位置元素

        }
    }
}

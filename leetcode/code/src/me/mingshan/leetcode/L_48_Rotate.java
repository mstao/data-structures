package me.mingshan.leetcode;

import java.util.Arrays;

/**
 * 给定一个 n x n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * <p>
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-image
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * https://leetcode-cn.com/problems/rotate-image/
 *
 * @author hanjuntao
 * @date 2022/1/3
 */
public class L_48_Rotate {
    public static void main(String[] args) {
        int[][] ma1 = {{1,2}, {3, 4}};

        System.out.println(Arrays.deepToString(ma1));
        rotate(ma1);

        // [[3,1],[4,2]]
        System.out.println(Arrays.deepToString(ma1));

        System.out.println("-------------------------------");

        int[][] ma2 = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        System.out.println(Arrays.deepToString(ma2));
        rotate(ma2);

        // [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
        System.out.println(Arrays.deepToString(ma2));

        System.out.println("-------------------------------");

        int[][] ma3 = {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println(Arrays.deepToString(ma3));
        rotate(ma3);

        // [[7, 4, 1], [8, 5, 2], [9, 6, 3]]
        System.out.println(Arrays.deepToString(ma3));

        System.out.println("-------------------------------");

        //[[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
    }

    /**
     * 解题思路：
     * <p>
     * 仔细观察几个图可知，当我们每次要计算一个位置上元素旋转后的另一个位置，都有上下左右四个方向的元素相互影响，这四个点形成一个新的正方形，
     * 所有我们每次都以四个点为整体来移动，那么当第一行的元素全部移动完成，整个矩阵也旋转完成。
     *
     * @param matrix 正方形
     */
    public static void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }

        // 行数 / 列数
        int m = matrix.length;
        // 循环多少圈
        int c = m / 2;

        for (int i = 0; i < c; i++) {
            rotateCirce(matrix, i);
        }
    }

    /**
     * @param matrix
     * @param c      第多少圈， 从 0开始
     */
    public static void rotateCirce(int[][] matrix, int c) {
        // 上下左右索引，从0开始
        int right = matrix.length - c - 1;
        int left = c;
        int top =  c;
        int bottom = matrix.length - c - 1;

        for (int i = c; i < right; i++) {
            // 上边的元素，x轴不动，y轴从左往右
            int currItem = matrix[top][i];

            // 左边的元素, y轴不动，x轴从下往上
            int leftItem = matrix[bottom - i + c][left];

            // 下边的元素，x轴不动，y轴从右往左
            int downItem = matrix[bottom][right - i + c];

            // 右边的元素，y轴不动，x轴从上往下
            int rightItem = matrix[i][right];

            // 先交换四个位置元素
            // 更新右
            matrix[i][right] = currItem;
            int temp = rightItem;

            // 更新下
            matrix[bottom][right - i] = temp;
            temp = downItem;

            // 更新左
            matrix[bottom - i][left] = temp;
            temp = leftItem;

            // 更新上
            matrix[top][i] = temp;
        }
    }
}

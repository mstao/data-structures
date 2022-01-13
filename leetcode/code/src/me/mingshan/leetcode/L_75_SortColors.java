package me.mingshan.leetcode;

import java.util.Arrays;

import static me.mingshan.leetcode.L_912_SortArray.swap;

/**
 * 给定一个包含红色、白色和蓝色，一共n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author hanjuntao
 * @date 2022/1/12
 */
public class L_75_SortColors {
    public static void main(String[] args) {
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        sortColors(nums1);
        System.out.println(Arrays.toString(nums1)); // [0,0,1,1,2,2]
    }

    /**
     * 插入排序
     *
     * @param nums
     */
    public static void sortColors(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }

        int len = nums.length;

        // 插入排序
        for (int i = 0; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                }
            }
        }
    }

    /**
     * 仅使用常数空间的一趟扫描算法
     *
     * @param nums
     */
    public static void sortColors2(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }

        int len = nums.length;

        int minIndex = 0;
        int minLen = 0;

        int secondIndex = 0;
        int secondLen = 0;

        int thirdIndex = 0;
        int thirdLen = 0;

        for (int i = 0; i < len; i++) {
            if (i + 1 < len) {
                // 后一位比前一位小
                if (nums[i+1] < nums[i]) {
                    // 检查 上一位是否有多个连续的
                    if (nums[i] == 1) {

                        swap(nums, i, i+1);


                    }

                    // 将两者交换位置

                }
            }
        }
    }

}

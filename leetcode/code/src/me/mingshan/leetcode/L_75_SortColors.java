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
        // 022110
        sortColors2(nums1);
        System.out.println(Arrays.toString(nums1)); // [0,0,1,1,2,2]

        System.out.println("----------------");

        int[] nums2 = {0, 0};
        // 022110
        sortColors2(nums2);
        System.out.println(Arrays.toString(nums2)); // [0,0,1,1,2,2]

        System.out.println("----------------");

        int[] nums3 = {1, 2, 0};
        // 022110
        sortColors2(nums3);
        System.out.println(Arrays.toString(nums3)); // [0,0,1,1,2,2]

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
     * <p>
     * 逻辑：
     * <p>
     * 碰到0，直接放到数组前面，遇到2，直接放到数组尾部
     *
     * @param nums
     */
    public static void sortColors2(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }

        int len = nums.length;

        int zeroIndex = -1;
        int twoIndex = -1;

        for (int i = 0; i < len; i++) {
            int currValue = nums[i];

            if (currValue == 1) {
                int j = i;
                while (j < len && nums[j] != 0) {
                    j++;
                }

                if (j )

                if (j != i) {
                    swap(nums, i, j);
                }
            }

            if (currValue == 2) {
                if (twoIndex == -1) {
                    twoIndex = len - 1;
                }
                while (twoIndex >= 0 && nums[twoIndex] == 2) {
                    twoIndex--;
                }

                // 无需处理
                if (twoIndex <= i) {
                    continue;
                }

                swap(nums, i, twoIndex);
            }

            if (currValue == 0) {
                if (zeroIndex == -1) {
                    zeroIndex = 0;
                }
                while (zeroIndex < len && nums[zeroIndex] == 0) {
                    zeroIndex++;
                }
                // 无需处理
                if (zeroIndex >= i) {
                    continue;
                }

                swap(nums, i, zeroIndex);
            }
        }
    }
}

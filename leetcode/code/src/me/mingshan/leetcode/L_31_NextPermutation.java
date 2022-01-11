package me.mingshan.leetcode;

import java.util.Arrays;

/**
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author hanjuntao
 * @date 2022/1/11
 */
public class L_31_NextPermutation {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};

        nextPermutation(nums1);
        System.out.println(Arrays.toString(nums1));
        System.out.println("------------------------");

        int[] nums2 = {3, 2, 1};

        nextPermutation(nums2);
        System.out.println(Arrays.toString(nums2));
        System.out.println("------------------------");

        int[] nums3 = {1, 1, 5};

        nextPermutation(nums3);
        System.out.println(Arrays.toString(nums3));
        System.out.println("------------------------");


        int[] nums4 = {4, 3, 2, 1};

        nextPermutation(nums4);
        System.out.println(Arrays.toString(nums4));
        System.out.println("------------------------");


        int[] nums5 = {1, 3, 2};

        nextPermutation(nums5);
        System.out.println(Arrays.toString(nums5));
        System.out.println("------------------------");  // [2,1,3]
    }

    /**
     * 思路：
     *
     * @param nums
     */
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }

        // 需要从后往前遍历，需要以当前位置为基准，找到上一个元素，下一个元素，这三者以当前排列为基准的下一个排列；
        // 如果没有出现，那么让整个数组反转

        int len = nums.length;

        boolean exist = false;

        // 从倒数第二个开始
        for (int i = len - 2; i >= 0; i--) {
            // 如果上一个
            if (nums[i] < nums[i + 1]) {
                int lastSecondIndex = i + 2;
                if (lastSecondIndex <= len - 1) {
                    if (nums[i + 1] > nums[lastSecondIndex]) {
                        swap(nums, i, lastSecondIndex);
                    } else {
                        swap(nums, i, i + 1);
                    }
                } else {
                    swap(nums, i, i + 1);
                }

                exist = true;
                break;
            }
        }

        if (!exist) {
            // 反转数组
            reverse(nums);
        }
    }

    private static void reverse(int[] nums) {
        int length = nums.length;
        int mid = nums.length / 2;

        for (int i = 0; i < mid; i++) {
            swap(nums, i, length - 1 - i);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

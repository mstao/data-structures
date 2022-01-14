package me.mingshan.leetcode;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回[-1, -1]。
 *
 * 进阶：
 *
 * 你可以设计并实现时间复杂度为O(log n)的算法解决此问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author hanjuntao
 * @date 2022/1/14
 */
public class L_34_SearchRange {
    public static void main(String[] args) {
//        int[] nums1 = {5,7,7,8,8,10};
//
//        int[] ints = searchRange2(nums1, 8);
//        System.out.println(Arrays.toString(ints));
//
//        int[] nums2 = {5,7,7,8,8,10};
//
//        int[] ints2 = searchRange2(nums2, 6);
//        System.out.println(Arrays.toString(ints2));

        int[] nums3 = {-99999,-99998,-9999,-999,-99,-9,-1};

        int[] ints3 = searchRange2(nums3, 0);
        System.out.println(Arrays.toString(ints3));
    }

    /**
     * 一次for循环
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;

        if (nums == null || nums.length == 0) {
            return result;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (result[0] == -1) {
                    result[0] = i;
                }

                result[1] = i;
            }
        }

        return result;
    }

    /**
     * 二分搜索
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange2(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;

        if (nums == null || nums.length == 0) {
            return result;
        }

        searchRange2(nums, result, target, 0, nums.length - 1);

        return result;
    }

    private static void searchRange2(int[] nums, int[] result, int target, int begin, int end) {
        int len = end - begin + 1;

        int mid = begin + (len / 2);

        if (end - begin == 1 || end - begin == 0) {
            // 刚好等于，那么直接查找即可
            handle(nums, result, target, begin, end);
            return;
        }

        //
        if (nums[mid] < target) {
            searchRange2(nums, result, target, mid + 1, end);
        } else if (nums[mid] > target) {
            searchRange2(nums, result, target, begin, mid);
        } else {
            // 刚好等于，那么直接查找即可
            handle(nums, result, target, begin, end);
        }

    }

    private static void handle(int[] nums, int[] result, int target, int begin, int end) {
        for (int i = begin; i <= end; i++) {
            if (nums[i] == target) {
                if (result[0] == -1) {
                    result[0] = i;
                }

                result[1] = i;
            }
        }
    }


}

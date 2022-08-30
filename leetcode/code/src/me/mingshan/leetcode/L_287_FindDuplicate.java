package me.mingshan.leetcode;

import java.util.Arrays;

/**
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 *
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author hanjuntao
 * @date 2022/8/30
 */
public class L_287_FindDuplicate {
    public static void main(String[] args) {
        int[] nums1 = {1,3,4,2,2};
        System.out.println(findDuplicate2(nums1));

        int[] nums2 = {2,2,2};
        System.out.println(findDuplicate2(nums2));
    }

    /**
     * 先排序，再找出有两个相邻的
     *
     * 不满足 【不修改数据】
     *
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;

        Arrays.sort(nums);

        for (int i = 0; i < len; i++) {
            int value = nums[i];
            int nextIndex = i + 1;

            if (nextIndex <= len - 1) {
                if (value == nums[nextIndex]) {
                    return value;
                }
            }
        }

        return 0;
    }

    /**
     * 取反法，可以将每一个位置上的数字，对应其相应的位置索引，将将其数据取反，如果当发现其数字已经是负数，证明已经出现重复了。
     *
     * @param nums
     * @return
     */
    public static int findDuplicate2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int result = 0;

        int len = nums.length;

        for (int i = 0; i < len; i++) {
            int value = nums[i];
            int anooo = Math.abs(value);

            int num = nums[anooo];
            if (num < 0) {
                result = Math.abs(value);
                break;
            } else {
                nums[anooo] = -num;
            }
        }

        for (int i = 0; i < len; i++) {
            nums[i] = Math.abs(nums[i]);
        }

        return result;
    }
}

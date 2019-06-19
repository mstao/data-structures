package me.mingshan.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 *
 *  给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 */
public class L_1_TwoSum {

    public static void main( String[] args ) {
        L_1_TwoSum t = new L_1_TwoSum();
        int[] nums = {5,4, 2};
        int target = 6;
        int[] result = t.twoSum2(nums, target);
        for (int i = 0; i< result.length; i++ ) {
            System.out.println(result[i]);
        }
    }

    /**
     *
     * 暴力方式，直接遍历数组，找出与nums[i]相加为target的元素，然后返回下标。
     * 因为要循环两次，时间复杂度为O(n * n)
     * 空间复杂度为O(1)。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if ((nums[i] + nums[j]) == target) {
                    return new int[] {i, j};
                }
            }
        }

        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 利用一个map将遍历过的数组的元素存起来，然后通过map来判断target - nums[i]元素是否存在，
     * 只需遍历一次即可，所以时间复杂度为O(n)；
     * 在最坏的情况下，需要map需要存储数组的所有元素，所以空间复杂度为O(n)。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> a = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int x = target - nums[i];
            if (a.containsKey(x)) {
                return new int[] {a.get(x), i};
            }

            a.put(nums[i], i);
        }

        throw new IllegalArgumentException("No two sum solution");
     }
}

package me.mingshan.leetcode;

import java.util.Arrays;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_26_RemoveDuplicates {

  public static void main(String[] args) {
    int[] nums = {1,1,2,2,3,4};

    System.out.println(removeDuplicates(nums));

    int[] nums2 = {1,1};

    System.out.println(removeDuplicates(nums2));

    int[] nums3 = {1};

    System.out.println(removeDuplicates(nums3));
  }

  public static int removeDuplicates(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    // 放在非重复元素的最后一个元素
    int i = 0;
    // 向前探测指针
    int j = 1;

    while (j < nums.length) {
      // 如果两者相等，j继续向前探测
      if (nums[j] == nums[i]) {
        j++;
      } else {
        // 两者不等，直接将j当前位置的数据移到i位置的后面，同时i指向该位置即可
        nums[i+1] = nums[j];
        i++;
      }
    }

    System.out.println(Arrays.toString(nums));

    return i + 1;
  }
}

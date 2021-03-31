package me.mingshan.leetcode;

import java.util.Arrays;

/**
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 未实现
 */
public class L_80_RemoveDuplicates {

  public static void main(String[] args) {
    int[] nums = {1, 1, 1, 2, 2, 3};
    System.out.println(removeDuplicates(nums));

//    int[] nums2 = {0,0,1,1,1,1,2,3,3};
//    System.out.println(removeDuplicates(nums2));
  }

  public static int removeDuplicates(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    // 指向处理过元素的最后一个
    int x = 0;

    // 代表重复区间的起始点
    int i = 0;
    // 向探测指针
    int j = 0;

    // 重复元素的长度，超过1个就需要处理
    int distance = 0;

    while (j < nums.length) {
      if (nums[i] == nums[j]) {
        boolean p = false;
        if (distance > 1) {
          x++;
          distance = 0;
          p = true;
        } else {
          distance++;
        }

        j++;
        if (p) {
          i = j;
        }
      } else {
        if (distance == 2) {
          nums[x + 1] = nums[i];
          nums[x + 2] = nums[i + 1];
        }

        x++;
      }
    }

    System.out.println(Arrays.toString(nums));

    return x + 1;
  }
}

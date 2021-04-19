package me.mingshan.leetcode;

import java.util.Arrays;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_283_MoveZeroes {

  public static void main(String[] args) {
    int[] nums1 = {0,1,4,3,12};
    moveZeroes2(nums1);
    System.out.println(Arrays.toString(nums1));

    int[] nums2 = {0,1};
    moveZeroes2(nums2);
    System.out.println(Arrays.toString(nums2));

  }

  /**
   * 直接交换
   *
   * @param nums
   */
  public static void moveZeroes(int[] nums) {
    if (nums == null || nums.length == 0) {
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) {
        // 如果i的下一位有值且不是0，直接交换
        if (i + 1 < nums.length && nums[i+1] != 0) {
          swap(nums, i, i+1);
        } else {
          // 如果下一位也是0，那么就需要向前继续探测，找到不为0的第一位，与当前位置交换即可
          for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] != 0) {
             swap(nums, i, j);
             break;
            }
          }
        }
      }
    }
  }

  /**
   * 思路：
   *
   * 先统计有多少个0，然后让非0的，全部挪到前面，剩下的全部改为0
   *
   * @param nums
   */
  public static void moveZeroes2(int[] nums) {
    if (nums == null || nums.length == 0) {
      return;
    }

    int zeroNum = 0;

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) {
        zeroNum++;
      }
    }

    // 最后不为0 的位置
    int lastIndex = nums[0] == 0 ? Integer.MIN_VALUE : 0;

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] != 0) {
        if (lastIndex == Integer.MIN_VALUE) {
          lastIndex = 0;
        } else {
          lastIndex++;
        }

        nums[lastIndex] = nums[i];
      }
    }

    // 从第x位开始，往后全是0
    int x = nums.length - zeroNum;

    for (int i = 0; i < nums.length; i++) {
      if (i >= x) {
        nums[i] = 0;
      }
    }
  }


  private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}

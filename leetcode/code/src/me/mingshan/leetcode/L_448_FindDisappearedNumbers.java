package me.mingshan.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 448. 找到所有数组中消失的数字
 *
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 *
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 *
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 *
 * 示例:
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [5,6]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_448_FindDisappearedNumbers {

  /**
   * 思路：
   *
   * 由于我们不知道那么数字到底有没有出现，且数组中的数字最大不会超过数字的长度，
   * 那么我们就将数组中的每一个值对应数组的下标，出现的全部都标记（取反等操作），剩下的没有标记过的下标，就是没有出现过的
   *
   * @param nums
   * @return
   */
  public List<Integer> findDisappearedNumbers(int[] nums) {
    if (nums == null || nums.length == 0) {
      return null;
    }

    int n = nums.length;

    List<Integer> result = new ArrayList<>();

    // 把nums中出现的每一个数-1作为下标对原数组值取反
    for (int i = 0; i < n; i++) {
      int num = Math.abs(nums[i]);
      // num 对应的索引
      int index = num - 1;
      if (nums[index] > 0) {
        nums[index] *= -1;
      }
    }

    // 找到那些值仍未正数的下标，代表没有被更新，即在数组中没有出现过
    for (int i = 0; i < n; i++) {
      if (nums[i] > 0) {
        result.add(i + 1);
      }
    }

    return result;
  }
}

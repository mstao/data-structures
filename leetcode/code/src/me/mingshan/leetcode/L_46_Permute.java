package me.mingshan.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class L_46_Permute {

  public static void main(String[] args) {
    System.out.println(permute(new int[]{1,2,3}));
  }

  public static List<List<Integer>> permute(int[] nums) {
    if (nums == null || nums.length == 0) {
      return null;
    }

    LinkedList<Integer> queue = new LinkedList<>();
    List<List<Integer>> result = new ArrayList<>();

    backtrace(nums, queue, result);
    return result;
  }

  /**
   * 回溯算法
   *
   * @param nums
   * @param queue
   * @param result
   */
  private static void backtrace(int[] nums, LinkedList<Integer> queue, List<List<Integer>> result) {
    if (queue.size() == nums.length) {
      result.add(new ArrayList<>(queue));
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (queue.contains(nums[i])) {
        continue;
      }

      queue.add(nums[i]);
      backtrace(nums, queue, result);
      queue.removeLast();
    }
  }
}

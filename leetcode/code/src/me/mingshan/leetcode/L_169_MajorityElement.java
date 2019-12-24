package me.mingshan.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/majority-element/
 *
 * @author hanjuntao
 */
public class L_169_MajorityElement {

  public int majorityElement(int[] nums) {
    Map<Integer, Integer> countMap = new HashMap<>();

    int majority = nums.length / 2;
    for (int item : nums) {
      int count = countMap.getOrDefault(item, 0) + 1;
      countMap.put(item, count);

      if (count > majority) {
        return item;
      }
    }

    return 0;
  }

  public int majorityElement2(int[] nums) {
    int candidate = nums[0];
    int count = 1;

    for (int item : nums) {
      if (count == 0) {
        candidate = item;
        count = 1;
      } else if (item == candidate) {
        count++;
      } else {
        count--;
      }
    }

    return candidate;
  }
}

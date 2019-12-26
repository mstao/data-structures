package me.mingshan.leetcode;

import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 *
 * @author hanjuntao
 */
public class L_215_FindKthLargest {

  public static void main(String[] args) {
    int[] nums = {1, 3, 2,5,7,6,9,10};

    System.out.println(findKthLargest(nums, 2));
  }

  public static int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
      if (o1.compareTo(o2) < 0) {
        return 1;
      } else if(o1.compareTo(o2) > 0) {
        return -1;
      }
      return 0;
    });

    for (int item : nums) {
      queue.add(item);
    }

    int answer = 0;
    for (int i = 0; i < k; i++) {
      answer = queue.poll();
    }

    return answer;
  }
}

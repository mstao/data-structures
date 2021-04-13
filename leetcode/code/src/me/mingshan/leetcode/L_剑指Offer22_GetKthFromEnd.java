package me.mingshan.leetcode;

import java.util.List;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 *
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 *
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 *
 *  
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 *
 * 返回链表 4->5.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_剑指Offer22_GetKthFromEnd {

  /**
   * 思路：先统计链表的长度len ，然后再走len -k 步即可，不过该方法需要遍历两次，时间复杂度为O(n)
   *
   * @param head
   * @param k
   * @return
   */
  public ListNode getKthFromEnd(ListNode head, int k) {
    if (head == null || k <= 0) {
      return null;
    }

    int len = 0;

    ListNode curr = head;

    while (curr != null) {
      len++;
      curr = curr.next;
    }

    curr = head;

    int p = len - k;

    while (curr != null) {
      if (p == 0) {
        return curr;
      }

      p--;
      curr = curr.next;
    }

    return null;
  }

  /**
   * 另一种解法：用两个指针，使两个指针始终相差k步，如果快的走到头了，那么慢的就是结果
   *
   * @param head
   * @param k
   * @return
   */
  public ListNode getKthFromEnd2(ListNode head, int k) {
    if (head == null || k <= 0) {
      return null;
    }

    ListNode slow = head;
    ListNode fast = head;

    int distance = 0;

    while (fast != null) {
      if (distance == k) {
        fast = fast.next;
        slow = slow.next;
      } else {
        fast = fast.next;
        distance++;
      }
    }

    return slow;
  }


  public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
}

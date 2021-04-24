package me.mingshan.leetcode;

import java.util.Stack;

/**
 * 234. 回文链表
 * <p>
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_234_IsPalindrome {

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(2);
    ListNode node4 = new ListNode(1);

    head.next = node2;
    node2.next = node3;

    System.out.println(isPalindrome(head));
  }

  /**
   * 用栈进行存储，时间复杂度O(N),空间复杂度为O(N)
   *
   * @param head
   * @return
   */
  public static boolean isPalindrome(ListNode head) {
    if (head == null) {
      return true;
    }

    Stack<Integer> data = new Stack<>();

    ListNode curr = head;
    while (curr != null) {
      data.push(curr.val);

      curr = curr.next;
    }

    curr = head;
    while (curr != null) {
      Integer pop = data.pop();
      if (pop != curr.val) {
        return false;
      }

      curr = curr.next;
    }

    return true;
  }

  public static boolean isPalindrome2(ListNode head) {
    if (head == null) {
      return true;
    }

    return true;
  }

  static class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}

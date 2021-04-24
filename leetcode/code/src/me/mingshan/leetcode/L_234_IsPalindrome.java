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
    ListNode node2 = new ListNode(1);
    ListNode node3 = new ListNode(1);
    ListNode node4 = new ListNode(1);

    head.next = node2;
//    node2.next = node3;
//    node3.next = node4;

    System.out.println(isPalindrome2(head));
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

  /**
   * 不使用额外空间
   *
   * @param head
   * @return
   */
  public static boolean isPalindrome2(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }

    // 先统计总节点数
    int nodeCount = 0;

    ListNode curr = head;
    while (curr != null) {
      nodeCount++;
      curr = curr.next;
    }

    // 将从中间节点链表反转

    int mid = nodeCount % 2 == 0 ? nodeCount / 2 : nodeCount / 2 + 1;

    curr = head;
    ListNode midNode = null;
    int currCount = 0;

    while (curr != null) {
      currCount++;

      if (currCount == mid) {
        midNode = curr;
        break;
      }

      curr = curr.next;
    }

    // 从两头向中间遍历，依次比较
    ListNode reverseHead = reverse(midNode);

    ListNode reverseNode = reverseHead;
    curr = head;

    int compareCount = 0;
    while (true) {
      compareCount++;
      if (reverseNode.val != curr.val) {
        return false;
      }

      if (compareCount == mid) {
        break;
      }

      reverseNode = reverseNode.next;
      curr = curr.next;
    }

    return true;
  }

  /**
   * 反转链表
   *
   * @param midNode
   * @return
   */
  private static ListNode reverse(ListNode midNode) {
    if (midNode == null || midNode.next == null) {
      return midNode;
    }

    ListNode node = reverse(midNode.next);
    midNode.next.next = midNode;
    midNode.next = null;
    return node;
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

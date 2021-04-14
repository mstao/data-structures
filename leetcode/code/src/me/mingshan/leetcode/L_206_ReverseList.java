package me.mingshan.leetcode;

import java.util.Stack;

/**
 * 206. 反转链表
 * <p>
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_206_ReverseList {

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    ListNode node1 = new ListNode(2);
    ListNode node2 = new ListNode(3);
    ListNode node3 = new ListNode(4);
    ListNode node4 = new ListNode(5);

    head.next = node1;
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;

    System.out.println(reverseList3(head));
  }

  /**
   * 递归版
   *
   * @param head
   * @return
   */
  public static ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode listNode = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return listNode;
  }

  /**
   * 尾递归
   *
   * @param head
   * @return
   */
  public static ListNode reverseList3(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    return reverse(null, head);
  }

  private static ListNode reverse(ListNode pre, ListNode cur) {
    if (cur == null) {
      return pre;
    }

    ListNode next = cur.next;
    cur.next = pre;
    return reverse(cur, next);
  }

  private static void reverse(ListNode node) {
    if (node.next.next == null) {
      ListNode last = node.next;
      node.next = null;
      last.next = node;
    } else {
      reverse(node);
    }
  }

  /**
   * 迭代版
   *
   * @param head
   */
  public static ListNode reverseList2(ListNode head) {
    if (head == null) {
      return head;
    }

    Stack<ListNode> stack = new Stack<>();

    ListNode curr = head;

    ListNode last = null;

    while (curr != null) {
      if (curr.next == null) {
        last = curr;
        break;
      } else {
        stack.push(curr);
        curr = curr.next;
      }
    }

    ListNode prev = last;

    while (!stack.isEmpty()) {
      ListNode node = stack.pop();
      node.next = null;
      prev.next = node;
      prev = prev.next;
    }

    return last;
  }


  public static class ListNode {
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

    @Override
    public String toString() {
      return "ListNode{" +
          "val=" + val +
          ", next=" + next +
          '}';
    }
  }
}

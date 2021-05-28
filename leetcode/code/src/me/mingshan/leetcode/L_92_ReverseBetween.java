package me.mingshan.leetcode;

import java.util.List;
import java.util.Stack;

/**
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class L_92_ReverseBetween {

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

    System.out.println(reverseBetween(head, 2, 5));
  }

  public static ListNode reverseBetween(ListNode head, int left, int right) {
    if (head == null) {
      return null;
    }

    if (right < left) {
      return head;
    }

    // 选中链表头结点的上一个节点
    ListNode lastDealHead = null;
    // 选中链表头结点
    ListNode dealHead = null;
    // 选中链表尾结点
    ListNode dealTail = null;
    // 选中链表尾结点下一个节点
    ListNode nextDealTail = null;

    ListNode curr = head;
    int begin = 1;

    while (curr != null) {
      if (begin == left - 1) {
        lastDealHead = curr;
      }

      if (begin == left) {
        dealHead = curr;
      }

      if (begin == right) {
        dealTail = curr;

        // 可能是空
        nextDealTail = curr.next;
      }

      begin++;
      curr = curr.next;
    }

    if (dealTail != null) {
      dealTail.next = null;
    }

    ListNode newHead = revereList(null, dealHead);

    if (lastDealHead == null) {
      dealHead.next = nextDealTail;
      return newHead;
    } else {
      lastDealHead.next = newHead;
      dealHead.next = nextDealTail;
      return head;
    }
  }

  private static ListNode revereList(ListNode prev, ListNode dealHead) {
    if (dealHead == null) {
      return prev;
    }

    ListNode next = dealHead.next;
    dealHead.next = prev;
    return revereList(dealHead, next);
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

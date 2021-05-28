package me.mingshan.leetcode;

/**
 * 21. 合并两个有序链表
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class L_21_MergeTwoLists {

  public static void main(String[] args) {
    ListNode next3 = new ListNode(4, null);
    ListNode next2 = new ListNode(3, next3);
    ListNode next1 = new ListNode(2, next2);
    ListNode head = new ListNode(1, next1);

    ListNode next13 = new ListNode(6, null);
    ListNode next12 = new ListNode(5, next13);
    ListNode next11 = new ListNode(3, next12);
    ListNode head1 = new ListNode(1, next11);
    System.out.println(mergeTwoLists(head, head1));
  }

  /**
   * 依次比较两个链表的值，谁的值小，谁的指针向前，最后将某一个链表剩余的元素全部拷贝过去即可
   *
   * @param l1
   * @param l2
   * @return
   */
  public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }

    if (l2 == null) {
      return l1;
    }

    ListNode next = null;
    ListNode head = null;

    ListNode l1Next = l1;
    ListNode l2Next = l2;

    while (l1Next != null && l2Next != null) {
      if (l1Next.val > l2Next.val) {
        if (next == null) {
          next = new ListNode(l2Next.val);
        } else {
          next.next = new ListNode(l2Next.val);
        }

        // 谁的值小，谁继续前进
        l2Next = l2Next.next;
      } else {
        if (next == null) {
          next = new ListNode(l1Next.val);
        } else {
          next.next = new ListNode(l1Next.val);
        }

        // 谁的值小，谁继续前进
        l1Next = l1Next.next;
      }

      if (head == null) {
        head = next;
      }

      if (next.next != null) {
        next = next.next;
      }
    }

    while (l1Next != null) {
      next.next = new ListNode(l1Next.val);
      l1Next = l1Next.next;

      next = next.next;
    }

    while (l2Next != null) {
      next.next = new ListNode(l2Next.val);
      l2Next = l2Next.next;

      next = next.next;
    }

    return head;
  }

  /**
   * 递归版:
   *
   * 思路：比较两个链表的当前节点，如果l1的节点小于l2的节点，那么我们需要合并l1.next 和l2,
   * 两个合并之后再作为一个链表，且l1.next指向该链表
   *
   *
   * @param l1
   * @param l2
   * @return
   */
  public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }

    if (l2 == null) {
      return l1;
    }

    // 谁小谁前进
    if (l1.val < l2.val) {
      l1.next = mergeTwoLists2(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoLists2(l1, l2.next);
      return l2;
    }
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

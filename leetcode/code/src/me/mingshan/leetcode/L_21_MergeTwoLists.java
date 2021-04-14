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
   * TODO 递归版
   *
   * @param l1
   * @param l2
   * @return
   */
  public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
    return null;
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

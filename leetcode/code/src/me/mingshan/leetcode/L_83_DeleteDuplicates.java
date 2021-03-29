package me.mingshan.leetcode;

/**
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 *
 * 返回同样按升序排列的结果链表。
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class L_83_DeleteDuplicates {

  public static void main(String[] args) {
    ListNode next5 = new ListNode(3, null);
    ListNode next4 = new ListNode(3, next5);
    ListNode next3 = new ListNode(2, next4);
    ListNode next2 = new ListNode(1, next3);
    ListNode next1 = new ListNode(1, next2);
    ListNode head = new ListNode(1, next1);

    System.out.println(deleteDuplicates(head));
  }

  public static ListNode deleteDuplicates(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    // 执行第一个非重复节点
    ListNode iNode = head;
    // 探测节点，主要作用：

    // 一个指针j用来向前探测重复的元素，当j遇到一个新的元素时，
    // 就将其位置的元素拷贝到i的下一位，同时i自增，指向元素，
    // 然后j跳过该元素的重复区间，继续探测下一个不同的元素，直至列表末尾

    ListNode jNode = head.next;

    while (jNode != null) {
      if (iNode.val != jNode.val) {
        iNode.next = jNode;

        // 仅jNode的下一位与当前jNode值一样值，才将iNode移到jNode的位置
        if (jNode.next != null && jNode.next.val != jNode.val) {
          iNode = jNode;
        }

        jNode = jNode.next;
      } else {
        // 如果两个值一样，iNode直接指向jNode的下一个节点
        jNode = jNode.next;
        iNode.next = jNode;
      }
    }

    return head;
  }

  public static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
      return "ListNode{" +
          "val=" + val +
          ", next=" + next +
          '}';
    }
  }
}

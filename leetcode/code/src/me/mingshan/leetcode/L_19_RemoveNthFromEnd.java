package me.mingshan.leetcode;

public class L_19_RemoveNthFromEnd {

  public static void main(String[] args) {
    ListNode l1 = new ListNode(2);
    ListNode l12 = new ListNode(4);
    ListNode l13 = new ListNode(5);
    l1.next = l12;
    l12.next = l13;

    ListNode listNode = removeNthFromEnd(l1, 4);
    ListNode.print(listNode);
  }

  public static ListNode removeNthFromEnd(ListNode head, int n) {
    if (head == null) {
      return null;
    }

    if (n <= 0) {
      return head;
    }

    int r = 0;
    ListNode fast = head;
    ListNode slow = null;
    ListNode prev = null;

    while (fast != null) {
      if (r >= n - 1) {
        if (slow == null) {
          slow = head;
        }

        if (fast.next == null) {
          break;
        }

        prev = slow;
        slow = slow.next;
      }

      r++;
      fast = fast.next;
    }

    if (slow != null) {
        if (slow == head) {
          ListNode next = head.next;
          if (next == null) {
            return null;
          }

          head.next = null;
          head = next;
        } else {
          prev.next = slow.next;
          slow.next = null;
        }
    }

    return head;
  }

  private static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
    }

    static void print(ListNode head) {
      StringBuilder sb = new StringBuilder("[");
      if (head == null) {
        System.out.println(sb.append("]").toString());
      } else {
        for (ListNode current = head; current != null; current = current.next) {
          sb.append(current.val + ", ");
        }

        int len = sb.length();
        System.out.println(sb.delete(len - 2, len).append("]").toString());
      }
    }
  }
}

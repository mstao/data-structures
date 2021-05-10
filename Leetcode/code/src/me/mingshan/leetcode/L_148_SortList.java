package me.mingshan.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 148. 排序链表
 * <p>
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * <p>
 * 进阶：
 * <p>
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_148_SortList {

  public static void main(String[] args) {
    ListNode next3 = new ListNode(4, null);
    ListNode next2 = new ListNode(5, next3);
    ListNode next1 = new ListNode(2, next2);
    ListNode head = new ListNode(1, next1);

    System.out.println(sortList(head));
  }

  /**
   * TODO
   *
   * @param head
   * @return
   */
  public static ListNode sortList(ListNode head) {
    if (head == null) {
      return null;
    }

    List<Integer> data = new ArrayList<>();

    ListNode curr = head;
    while (curr != null) {
      data.add(curr.val);

      curr = curr.next;
    }

    Collections.sort(data);

    ListNode resultHead = null;
    ListNode resultCurr = null;

    for (Integer item : data) {
      if (resultHead == null) {
        resultHead = new ListNode(item);
        resultCurr = resultHead;
      } else {
        resultCurr.next = new ListNode(item);
        resultCurr = resultCurr.next;
      }
    }

    return resultHead;
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

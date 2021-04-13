package me.mingshan.leetcode;

/**
 * 160. 相交链表
 * <p>
 * 编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * 注意：
 * <p>
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_160_GetIntersectionNode {

  public static void main(String[] args) {
    ListNode head1 = new ListNode(1);
    ListNode head2 = new ListNode(2);

    ListNode node2 = new ListNode(4);
    ListNode node3 = new ListNode(6);
    ListNode node4 = new ListNode(8);
    ListNode node5 = new ListNode(4);
    ListNode node6 = new ListNode(2);


    head1.next = node2;

    head2.next = node3;
    node3.next = node4;

    node5.next = node6;

    node2.next = node5;
    node4.next = node5;

    System.out.println(getIntersectionNode2(head1, head2));
  }

  /**
   * 思路：暴力求解
   *
   * @param headA
   * @param headB
   * @return
   */
  public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }

    ListNode currA = headA;
    while (currA != null) {
      ListNode currB = headB;
      while (currB != null) {
        if (currA.equals(currB)) {
          return currA;
        }

        currB = currB.next;
      }

      currA = currA.next;
    }

    return null;
  }

  /**
   * 思路2： 和 https://github.com/mstao/data-structures/blob/master/leetcode/code/src/me/mingshan/leetcode/L_剑指Offer22_GetKthFromEnd.java
   * 这个题一样，需要先计算出两个链表的长度，然后让长的先走几步，等到长度一样时，再一齐走，然后判断是否节点有一样，
   * 有一样的返回即可。
   *
   * @param headA
   * @param headB
   * @return
   */
  public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }

    int aLen = 0;
    int bLen = 0;

    ListNode currA = headA;
    ListNode currB = headB;

    while (currA != null) {
      aLen++;
      currA = currA.next;
    }

    while (currB != null) {
      bLen++;
      currB = currB.next;
    }

    int distance = 0;
    int k = Math.abs(aLen - bLen);
    currA = headA;
    currB = headB;


    // 需要判断谁的长度，长度长的先走

    while (currA != null && currB != null) {
      if (distance == k || aLen == bLen) {
        if (currA.equals(currB)) {
          return currA;
        }
        currA = currA.next;
        currB = currB.next;
      } else {
        if (aLen > bLen) {
          currA = currA.next;
        } else {
          currB = currB.next;
        }

        distance++;
      }
    }

    return null;
  }

  public static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
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

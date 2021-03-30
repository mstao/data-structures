package me.mingshan.leetcode;

/**
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 返回同样按升序排列的结果链表。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_82_DeleteDuplicates {

  public static void main(String[] args) {
//    ListNode next5 = new ListNode(4, null);
//    ListNode next4 = new ListNode(3, next5);
    ListNode next3 = new ListNode(1, null);
    ListNode next2 = new ListNode(1, next3);
    ListNode next1 = new ListNode(1, next2);
    ListNode head = new ListNode(1, next1);

    System.out.println(deleteDuplicates(head));
  }


  /**
   * 思路：
   *
   * 我们可以利用已有的集合空间，结合两个指针，一个指针i一直指向非重复元素的最后一位，一个指针j用来向前探测重复的元素，
   * 当j遇到一个新的元素时，就将其位置的元素拷贝到i的下一位，同时i自增，指向元素，然后j跳过该元素的重复区间，
   * 继续探测下一个不同的元素，直至列表末尾，最后输出0~i这个区间的元素即可。
   *
   *
   * @param head
   * @return
   */
  public static ListNode deleteDuplicates(ListNode head) {
    if (head == null) {
      return null;
    }

    // iNode的上一个节点
    ListNode aNode = null;
    // iNode表示重复的第一个节点
    ListNode iNode = head;
    // jNode表示重复的最后一个节点
    ListNode jNode = head.next;
    
    int distance = 0;

    while (jNode != null) {
      if (iNode.val != jNode.val) {
        // 进行
        if (distance >= 1) {
          if (aNode == null) {
            head = jNode;
          } else {
            aNode.next = jNode;
          }

          iNode = jNode;
          jNode = jNode.next;
          distance = 0;
        } else {
          aNode = iNode;
          iNode = jNode;
          jNode = jNode.next;
        }
      } else {
        jNode = jNode.next;
        distance++;

        // 最后一个还是在重复区间
        if (jNode == null) {
          if (aNode != null) {
            aNode.next = null;
          } else {
            head = null;
          }
        }
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

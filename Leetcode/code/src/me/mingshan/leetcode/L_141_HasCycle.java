package me.mingshan.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个链表，判断链表中是否有环。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，
 * 我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * 注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 *
 * TODO 进阶：
 *
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_141_HasCycle {

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    ListNode next1 = new ListNode(2);
    ListNode next2 = new ListNode(1);

//    head.setNext(next1);
//    next1.setNext(next2);
//    next2.setNext(head);

    System.out.println(hasCycle(head));
    System.out.println(hasCycle2(head));
  }

  /**
   * 记录访问过的节点，如果当前节点的下一个节点是以前访问过的节点，那么就是环
   *
   * @param head
   * @return
   */
  public static boolean hasCycle(ListNode head) {
    if (head == null) {
      return false;
    }

    // 已经访问过的索引
    List<ListNode> visited = new ArrayList<>();

    ListNode curr = head;

    while (curr != null) {
      for (ListNode visit : visited) {
        if (visit.equals(curr.next)) {
          return true;
        }
      }
      visited.add(curr);

      curr = curr.next;
    }

    return false;
  }

  /**
   * 快慢指针，如果有环，那么快的必然能碰见慢的
   *
   * @param head
   * @return
   */
  public static boolean hasCycle2(ListNode head) {
    if (head == null) {
      return false;
    }

    // 快的每次走两步
    ListNode fast = head.next;
    // 慢的每次走一步
    ListNode slow = head;

    // 相差步数
    int step = 1;

    while (true) {
      // 出现节点为空，证明非环
      if (fast == null) {
        return false;
      }

      if (fast.equals(slow)) {
        return true;
      }

      fast = fast.next;
      step++;

      if (step == 2) {
        slow = slow.next;
        step = 0;
      }
    }
  }

  public static class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }

    public void setNext(ListNode next) {
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

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
   * 将头节点head反转，返回反转后的头节点
   */
  public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    // 将head的下一个节点作为头节点进行反转
    ListNode node = reverseList(head.next);
    // 将当前节点的下一个节点指向当前节点head
    head.next.next = head;
    // 当前节点head 指向null即可
    head.next = null;
    return node;
  }

  /**
   * 尾递归
   *
   * 每次递归都传入当前节点的上一个节点prev，与当前节点curr，
   * 然后让当前节点的下一个节点指向pre： pre= curr.next
   *
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

  /**
   * 每次递归都传入当前节点的上一个节点prev，与当前节点curr，
   * 然后让当前节点的下一个节点指向pre： pre= curr.next
   *
   * 函数返回最后一个节点
   *
   * @param pre
   * @param cur
   * @return
   */
  private static ListNode reverse(ListNode pre, ListNode cur) {
    if (cur == null) {
      return pre;
    }

    ListNode next = cur.next;
    cur.next = pre;
    return reverse(cur, next);
  }

  /**
   * 迭代版:
   *
   * 用一个栈记录访问过的节点，然后从后往前遍历即可
   *
   * @param head
   */
  public static ListNode reverseList2(ListNode head) {
    if (head == null) {
      return head;
    }

    // 申请一个空间
    Stack<ListNode> stack = new Stack<>();

    ListNode curr = head;

    // 新的链表头结点
    ListNode last = null;

    // 原链表入栈
    while (curr != null) {
      if (curr.next == null) {
        last = curr;
        break;
      } else {
        stack.push(curr);
        curr = curr.next;
      }
    }

    // 指向当前操作的节点下一个
    ListNode prev = last;

    // 从后往前遍历原链表
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

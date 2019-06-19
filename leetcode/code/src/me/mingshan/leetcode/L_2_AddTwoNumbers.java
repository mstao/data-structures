package me.mingshan.leetcode;

/**
 * 两数相加
 *   给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 *   你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * @Author: mingshan
 * @Date: Created in 17:38 2018/9/15
 */
public class L_2_AddTwoNumbers {

    public static void main(String[] args) {
        L_2_AddTwoNumbers twoNumbers = new L_2_AddTwoNumbers();
        // 第一种
        // 2-> 4 -> 5
        ListNode l1 = new ListNode(2);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(5);
        l1.next = l12;
        l12.next = l13;

        // 5 -> 2 -> 5
        ListNode l2 = new ListNode(5);
        ListNode l22 = new ListNode(2);
        ListNode l23 = new ListNode(5);
        l2.next = l22;
        l22.next = l23;

        // [7, 6, 0, 1]
        ListNode.print(twoNumbers.addTwoNumbers(l1, l2));

        // 第二种
        // 2 -> 5 -> 3
        ListNode l3 = new ListNode(2);
        ListNode l32 = new ListNode(6);
        ListNode l33 = new ListNode(3);
        l3.next = l32;
        l32.next = l33;

        // 5 -> 6
        ListNode l4 = new ListNode(5);
        ListNode l42 = new ListNode(6);
        l4.next = l42;
        ListNode.print(twoNumbers.addTwoNumbers(l3, l4));

        // 第三种
        // []的情况
        ListNode l5 = new ListNode(5);
        ListNode l52 = new ListNode(6);
        l5.next = l42;
        ListNode.print(twoNumbers.addTwoNumbers(l5, null));


        // 9, 9 -> 9
        ListNode l7 = new ListNode(9);
        ListNode l8 = new ListNode(9);
        ListNode l9 = new ListNode(9);
        l8.next = l9;
        ListNode.print(twoNumbers.addTwoNumbers(l7, l8));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        ListNode temp1 = l1;
        ListNode temp2 = l2;

        ListNode curr = null;

        int remainder = 0;

        while (temp1 != null || temp2 != null) {
            int res = (temp1 != null ? temp1.val : 0) + (temp2 != null ? temp2.val : 0) + remainder;
            remainder = 0;
            if (res >= 10) {
                remainder = res / 10;
            }

            res = res % 10;

            if (result == null) {
                // 创建头结点
                curr = result = new ListNode(res);
            } else {
                curr.next = new ListNode(res);
                curr = curr.next;
            }

            if (temp1 != null) {
                temp1 = temp1.next;
            }
            if (temp2 != null) {
                temp2 = temp2.next;
            }
        }

        // 处理最后一位相加大于10的情况，由于节点只存储单个数字
        // 像 l1: 2 -> 5, l2: 3-> 5, 需要再向后添加一个结点
        if (remainder > 0) {
            curr.next = new ListNode(remainder);
        }

        return result;
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    static void print(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        for (ListNode current = head; current != null; current = current.next) {
            sb.append(current.val + ", ");
        }

        int len = sb.length();
        System.out.println(sb.delete(len - 2, len).append("]").toString());
    }
}

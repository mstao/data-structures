package me.mingshan.algorithm.linkedlist;

/**
 * 给定一个单向链表，获取倒数第k个节点
 * 节点定义如下：
 * <pre>{@code
 * private class Node<E> {
 *     E item;
 *     Node next;
 *
 *     public Node(E e) {
 *       this.item = e;
 *     }
 * }
 * }
 * </pre>
 *
 */
public class FindKNode {

  public static <E> Node<E> solution(Node<E> head, int k) {
    if (k <= 0) {
      return null;
    }

    if (head == null) {
      return null;
    }

    int r = 0;
    Node<E> fast = head;
    Node<E> slow = head;

    while (fast != null) {
      if (r >= k - 1) {

        if (fast.next == null) {
          return slow;
        }

        slow = slow.next;
      }

      r++;
      fast = fast.next;
    }

    return null;
  }

  private static class Node<E> {
    E item;
    Node<E> next;

    public Node(E e) {
      this.item = e;
    }

    @Override
    public String toString() {
      return "Node{" +
          "item=" + item +
          ", next=" + next +
          '}';
    }
  }

  private static class LinkedList<E> {
    private Node<E> head;

    public boolean add(E data) {
      if (data == null)
        throw new NullPointerException();
      if (head == null) {
        head = new Node<>(data);
        return true;
      }

      Node<E> temp = head;
      // 从头结点向后遍历，获取链表最后一个节点
      while (temp.next != null) {
        // temp 始终指向下一个节点
        temp = temp.next;
      }

      // 根据当前元素构造新节点
      // 将最后一节点的next指向新节点
      temp.next = new Node<>(data);
      // 计数加一
      return true;
    }
  }

  public static void main(String[] args) {
    LinkedList<String> list1 = new LinkedList<String>();
    list1.add("aa");
    list1.add("bb");
    list1.add("zz");
    list1.add("cc");
    list1.add("dd");

    System.out.println(list1.toString());

    System.out.println(solution(list1.head, 0));
    System.out.println(solution(list1.head, 1));
    System.out.println(solution(list1.head, 2));
    System.out.println(solution(list1.head, 3));
    System.out.println(solution(list1.head, 4));
    System.out.println(solution(list1.head, 5));
    System.out.println(solution(list1.head, 6));
  }

}

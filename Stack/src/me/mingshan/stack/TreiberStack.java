package me.mingshan.stack;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 无锁并发栈
 *
 * @author mingshan
 */
public class TreiberStack<E> {

  AtomicReference<Node<E>> top = new AtomicReference<>();

  public void push(E item) {
    Node<E> newHead = new Node<E>(item);
    Node<E> oldHead;
    do {
      oldHead = top.get();
      newHead.next = oldHead;
    } while (!top.compareAndSet(oldHead, newHead));
  }

  public E pop() {
    Node<E> oldHead;
    Node<E> newHead;
    do {
      oldHead = top.get();
      if (oldHead == null) {
        return null;
      }
      newHead = oldHead.next;
    } while (!top.compareAndSet(oldHead, newHead));
    return oldHead.item;
  }

  public boolean isEmpty() {
    return top.get() == null;
  }

  public int size() {
    int currentSize = 0;
    Node<E> current = top.get();
    while (current != null) {
      currentSize++;
      current = current.next;
    }
    return currentSize;
  }

  public E peek() {
    Node<E> eNode = top.get();
    if (eNode == null) {
      return null;
    } else {
      return eNode.item;
    }
  }

  private static class Node<E> {
    E item;
    Node<E> next;

    Node(E item) {
      this.item = item;
    }
  }
}

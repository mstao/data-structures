package me.mingshan.stack;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 无锁并发栈，基于{@link AtomicReference}
 * 不可以存{@code null}
 *
 * @author mingshan
 */
public class TreiberStack<E> implements Stack<E> {

  AtomicReference<Node<E>> top = new AtomicReference<>();

  @Override
  public void push(E item) {
    Objects.requireNonNull(item);
    Node<E> newHead = new Node<E>(item);
    Node<E> oldHead;
    do {
      oldHead = top.get();
      newHead.next = oldHead;
    } while (!top.compareAndSet(oldHead, newHead));
  }

  @Override
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

  @Override
  public int size() {
    int currentSize = 0;
    Node<E> current = top.get();
    while (current != null) {
      currentSize++;
      current = current.next;
    }
    return currentSize;
  }

  @Override
  public E peek() {
    Node<E> eNode = top.get();
    if (eNode == null) {
      return null;
    } else {
      return eNode.item;
    }
  }

  @Override
  public String toString() {
    if (top == null) {
      return "[]";
    } else {
      StringBuilder sb = new StringBuilder();
      Node<E> current = top.get();
      while (current != null) {
        sb.append(current.item).append(",");
        current = current.next;
      }

      return sb.substring(0, sb.length() -1);
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

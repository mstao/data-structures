package me.mingshan.stack;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.Objects;

/**
 * 基于VarHandle实现TreiberStack
 *
 * @author mingshan
 */
public class TreiberStack2<E> {
  private volatile Node<E> top;

  public void push(E item) {
    Objects.requireNonNull(item);

    Node<E> newHead = new Node<E>(item);
    Node<E> oldHead;
    do {
      oldHead = top;
      newHead.next = oldHead;
    } while (!TOP.compareAndSet(this, oldHead, newHead));
  }

  public E pop() {
    Node<E> oldHead;
    Node<E> newHead;
    do {
      oldHead = top;
      if (oldHead == null) {
        return null;
      }
      newHead = oldHead.next;
    } while (!TOP.compareAndSet(this, oldHead, newHead));
    return oldHead.item;
  }

  public boolean isEmpty() {
    return top == null;
  }

  public int size() {
    int currentSize = 0;
    Node<E> current = top;
    while (current != null) {
      currentSize++;
      current = current.next;
    }
    return currentSize;
  }

  public E peek() {
    Node<E> eNode = top;
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

  private static final VarHandle TOP;
  static {
    try {
      MethodHandles.Lookup l = MethodHandles.lookup();
      TOP = l.findVarHandle(TreiberStack2.class, "top", TreiberStack2.Node.class);
    } catch (ReflectiveOperationException e) {
      throw new ExceptionInInitializerError(e);
    }
  }
}

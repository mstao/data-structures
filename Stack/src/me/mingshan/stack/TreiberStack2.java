package me.mingshan.stack;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.Objects;

/**
 * 基于VarHandle实现TreiberStack
 * 不可以存{@code null}
 *
 * @author mingshan
 */
public class TreiberStack2<E> implements Stack<E> {
  private volatile Node<E> top;

  @Override
  public void push(E item) {
    Objects.requireNonNull(item);

    Node<E> newTop = new Node<E>(item);
    do {} while (!tryPush(newTop));
  }

  private boolean tryPush(Node<E> node) {
    Node<E> oldTop = top;
    NEXT.set(node, oldTop);
    return TOP.compareAndSet(this, oldTop, node);
  }

  @Override
  public E pop() {
    Node<E> oldTop = top;

    if (oldTop == null)
      return null;

    while (TOP.compareAndSet(this, oldTop, oldTop.next)) {
      NEXT.set(oldTop, null);
    }

    return oldTop.item;
  }

  @Override
  public boolean isEmpty() {
    return top == null;
  }

  @Override
  public int size() {
    Node<E> current = top;
    int size = 0;
    while (current != null) {
      size++;
      current = current.next;
    }
    return size;
  }

  @Override
  public E peek() {
    Node<E> eNode = top;
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
      Node<E> current = top;
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

  private static final VarHandle TOP;
  private static final VarHandle NEXT;
  static {
    try {
      MethodHandles.Lookup l = MethodHandles.lookup();
      TOP = l.findVarHandle(TreiberStack2.class, "top", TreiberStack2.Node.class);
      NEXT = l.findVarHandle(TreiberStack2.Node.class, "next", TreiberStack2.Node.class);
    } catch (ReflectiveOperationException e) {
      throw new ExceptionInInitializerError(e);
    }
  }
}

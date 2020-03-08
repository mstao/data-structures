package me.mingshan.algorithm.stack;

import java.util.Objects;
import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的min函数。在该栈中，调用min、push及pop的时间复杂度都是O（1）。
 *
 * 思路：需要一个辅助栈，用来记录每次入栈时当前栈内的最小元素
 */
public class StackWithMin<E extends Comparable<E>> {
  private E min;
  private final Stack<E> dataStack = new Stack<>();
  private final Stack<E> minStack = new Stack<>();

  public void push(E data) {
    Objects.requireNonNull(data, "data");

    if (min == null || data.compareTo(min) < 0) {
      min = data;
    }

    dataStack.push(data);
    minStack.push(min);
  }

  public E pop() {
    E node = dataStack.pop();
    if (node.equals(min)) {
      minStack.pop();
      min = minStack.peek();
    }
    return node;
  }

  public E min() {
    return min;
  }

  public static void main(String[] args) {
    StackWithMin<Integer> test = new StackWithMin<>();
    test.push(3);
    System.out.println(test.min);
    test.push(1);
    System.out.println(test.min);
    test.push(4);
    System.out.println(test.min);
    test.push(2);
    System.out.println(test.min);

    test.pop();
    System.out.println(test.min);

    test.pop();
    System.out.println(test.min);

    test.pop();
    System.out.println(test.min);

    test.pop();
    System.out.println(test.min);
  }
}

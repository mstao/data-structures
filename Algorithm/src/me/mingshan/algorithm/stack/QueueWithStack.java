package me.mingshan.algorithm.stack;

import java.util.Stack;

/**
 * 用栈实现队列
 *
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 *
 * @author Walker Han
 * @date 2021/3/5 11:20
 */
public class QueueWithStack {
  // 数据存储栈
  private Stack<Integer> dataStack;
  // 数据中转栈
  private Stack<Integer> tempStack;
  private Integer headItem;

  /** Initialize your data structure here. */
  public QueueWithStack() {
    headItem = null;
    dataStack = new Stack<>();
    tempStack = new Stack<>();
  }

  /** Push element x to the back of queue. */
  public void push(int x) {
    if (dataStack.empty())  {
      headItem = x;
    }

    dataStack.push(x);
  }

  /** Removes the element from in front of queue and returns that element. */
  public int pop() {
    if (dataStack.empty()) {
      throw new RuntimeException("Queue is empty");
    }

    tempStack.clear();
    while (!dataStack.empty()) {
      Integer integer = dataStack.pop();
      tempStack.push(integer);
    }

    int result = tempStack.pop();

    if (!tempStack.empty()) {
      headItem = tempStack.peek();

      while (!tempStack.empty()) {
        Integer integer = tempStack.pop();
        dataStack.push(integer);
      }
    } else {
      headItem = null;
    }

    return result;
  }

  /** Get the front element. */
  public int peek() {
    return headItem;
  }

  /** Returns whether the queue is empty. */
  public boolean empty() {
    return headItem == null;
  }
}


/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

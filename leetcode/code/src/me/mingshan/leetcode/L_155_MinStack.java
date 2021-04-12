package me.mingshan.leetcode;

import java.util.Stack;

/**
 * 155. 最小栈
 *
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class L_155_MinStack {

  public static void main(String[] args) {
    L_155_MinStack obj = new L_155_MinStack();
    obj.push(1);
    obj.pop();

    int param_3 = obj.top();
    int param_4 = obj.getMin();
  }

  private Integer minValue = null;
  private Integer secondMinValue = null;

  private Stack<Integer> stack;

  /** initialize your data structure here. */
  public L_155_MinStack() {
    stack = new Stack<>();
  }

  public void push(int val) {
    if (val < minValue) {
      minValue = val;
    }

    stack.push(val);
  }

  public void pop() {
    Integer popValue = stack.pop();
    if (popValue.equals(minValue)) {
      minValue = secondMinValue;
    }
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return minValue;
  }
}

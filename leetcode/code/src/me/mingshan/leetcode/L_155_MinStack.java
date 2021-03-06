package me.mingshan.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 155. 最小栈
 * <p>
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_155_MinStack {

  public static void main(String[] args) {
    L_155_MinStack obj = new L_155_MinStack();
    obj.push(5);
    obj.push(3);
    obj.push(1);
    System.out.println(obj.top());
    System.out.println(obj.getMin());

    obj.pop();
    System.out.println(obj.getMin());
    obj.push(7);
    obj.push(2);
    System.out.println(obj.top());
    System.out.println(obj.getMin());
  }

  /**
   *  如果所有操作都在O(1）中执行的话，需要两个栈，一个数据栈，存放数据，
   *  另一个栈存放当前的的最小元素，同时有一个变量记录最小值
   *
   *  入栈时，用当前值与最小值比较，如果比最小值还小，那么将最小值改为当前值，同时将该值入最小值栈
   *
   *  出栈时，如果当前出的值等于最小值，那么将最小值栈顶元素弹出，获取最小值栈新的栈顶元素，然后将
   *  该值赋值给最小值
   */


  private Integer minValue = null;

  private List<Integer> dataStack;

  /**
   * initialize your data structure here.
   */
  public L_155_MinStack() {
    dataStack = new ArrayList<>();
  }

  public void push(int val) {
    if (minValue == null || val < minValue) {
      minValue = val;
    }

    dataStack.add(val);
  }

  public void pop() {
    Integer popValue = dataStack.remove(dataStack.size() - 1);

    if (popValue.equals(minValue)) {
      if (dataStack.isEmpty()) {
        minValue = null;
      } else {
        minValue = findMinValue();
      }
    }
  }

  private int findMinValue() {
    int minValue = Integer.MAX_VALUE;
    for (Integer item : dataStack) {
      if (item < minValue) {
        minValue = item;
      }
    }

    return minValue;
  }

  public int top() {
    if (dataStack.isEmpty()) {
      throw new RuntimeException();
    }

    return dataStack.get(dataStack.size() - 1);
  }

  public int getMin() {
    if (dataStack.isEmpty()) {
      throw new RuntimeException();
    }

    return minValue;
  }
}

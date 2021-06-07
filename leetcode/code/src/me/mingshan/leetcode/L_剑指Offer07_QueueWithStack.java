package me.mingshan.leetcode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 剑指 Offer 09. 用两个栈实现队列
 *
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 *
 * @author Walker Han
 * @date 2021/6/7 10:17
 */
public class L_剑指Offer07_QueueWithStack {
  private Stack<Integer> stack1;
  private Stack<Integer> stack2;

  /**
   * 一个栈stack1，只用来存数据
   *
   * 当要获取队列第一个元素时，先判断stack2有没有元素，如果有，直接弹出栈顶元素；
   * 再判断stack1有没有元素，没有元素，返回-1；
   * 否则的话，将stack1的元素依次弹出，并且依次入stack2，这样stack2的元素顺序刚好与stack1相反，
   * 直接弹出stack2栈顶元素即可
   *
   */

  public L_剑指Offer07_QueueWithStack() {
    stack1 = new Stack<>();
    stack2 = new Stack<>();
  }

  public void appendTail(int value) {
    stack1.push(value);
  }

  public int deleteHead() {
    if (!stack2.isEmpty()) {
      return stack2.pop();
    }

    if (stack1.isEmpty()) {
      return -1;
    }

    while (!stack1.isEmpty()) {
      Integer pop = stack1.pop();
      stack2.push(pop);
    }

    return stack2.pop();
  }
}

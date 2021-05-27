package me.mingshan.algorithm.string;

import java.util.Stack;

/**
 * 实现并返回，2个长整数的字符串相加的和，比如"1234567890" + "99" = "1234567989"，
 * 不能直接转成bigdeciaml 或 long相加
 *
 * @author Walker Han
 * @date 2021/5/27 11:18
 */
public class SumStr {

  public static void main(String[] args) {
    System.out.println(add("99", "999"));
    System.out.println(add("121", "2119"));
  }

  public static String add(String str1, String str2) {
    if (str1 == null || str1.length() == 0) {
      return str2;
    }

    if (str2 == null || str2.length() == 0) {
      return str1;
    }

    char[] aChar = str1.toCharArray();
    char[] bChar = str2.toCharArray();

    char[] maxChar = aChar.length > bChar.length ? aChar : bChar;
    char[] minChar = aChar.length < bChar.length ? aChar : bChar;
    int maxLen = maxChar.length;
    int minLen = minChar.length;

    // 计算结果栈
    Stack<Integer> resultStack = new Stack<>();

    // 当前计算产生的进位
    int carry = 0;

    for (int i = maxLen - 1; i >= 0; i--) {
      // 偏移量, 移动一个加1
      int offset = (maxLen - i);

      // 如果短的计算完毕，那么处理产生的进位
      if (offset > minLen) {
        int curr = toInteger(maxChar[i]);
        int value = curr + carry;
        carry = handleCarry(resultStack, value);
      } else {
        int maxValue = toInteger(maxChar[i]);
        int minValue = toInteger(minChar[minLen - offset]);

        int value = maxValue + minValue + carry;
        carry = handleCarry(resultStack, value);
      }
    }

    if (carry != 0) {
      resultStack.push(carry);
    }

    String result = "";

    while (!resultStack.isEmpty()) {
      result += resultStack.pop();
    }

    return result;
  }

  private static int handleCarry(Stack<Integer> resultStack, int value) {
    int carry;
    if (value >= 10) {
      int step = 0;

      while (value >= 10) {
        value = value - 10;
        step++;
      }

      carry = step;
      resultStack.push(value);
    } else {
      resultStack.push(value);
      carry = 0;
    }
    return carry;
  }

  private static int toInteger(char source) {
    return Integer.parseInt(String.valueOf(source));
  }
}

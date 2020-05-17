package me.mingshan.algorithm.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 进制转换，将十进制数字转化为给定进制
 */
public class Convert {
  private static final char[] digit = {
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
  };

  public static void main(String[] args) {
    System.out.println(convert(14, 2));
  }

  public static String convert(int n, int base) {
    Stack<Character> data = new Stack<>();
    while (n > 0) {
      data.push(digit[n % base]); // 余数入栈

      n /= base; // n更新为对base的除商
    }

    char[] result = new char[data.size()];
    int i = 0;
    while (!data.isEmpty()) {
      result[i] = data.pop();
      i++;
    }

    return Arrays.toString(result);
  }
}

package me.mingshan.leetcode;

import java.util.Arrays;

/**
 * 整数反转
 *
 *  题目： 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * @author mingshan
 */
public class L_7_ReverseInteger {
  public static void main(String[] args) {
    int x = 111221;

    System.out.println(reverse(x));
  }


  public static int reverse(int x) {
    if (x == 0)
      return 0;
    
    String source = String.valueOf(x);
    boolean f = false;
    if (source.contains("-")) {
      f = true;
      source = source.replace("-", "");
    }
    char[] chars = source.toCharArray();
    for (int i = 0; i < chars.length / 2; i++) {
      char temp = chars[i];
      chars[i] = chars[chars.length - i -1];
      chars[chars.length - i -1] = temp;
    }

    int result;
    try {
      result = Integer.parseInt(String.valueOf(chars));
    } catch (NumberFormatException e) {
      return 0;
    }

    if (f) {
      return -result;
    }

    return result;
  }
}

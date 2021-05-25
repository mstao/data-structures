package me.mingshan.algorithm.binary;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Stack;

/**
 * 进制转换，将十进制数字转化为给定进制
 *
 * @author mingshan
 */
public class Convert {
  private static final char[] digit = {
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
  };

  public static void main(String[] args) {
    System.out.println(convert(14, 2));

    System.out.println(convert10("0x76E"));
  }

  /**
   * 十进制转任意进制，
   *
   * 处理方式：
   *
   * 十进制整数转换为二进制整数十进制整数转换为二进制整数采用"除2取余，逆序排列"法。
   * 具体做法是：用2整除十进制整数，可以得到一个商和余数；再用2去除商，又会得到一个商和余数，
   * 如此进行，直到商为小于1时为止，然后把先得到的余数作为二进制数的低位有效位，后得到的余数作为二进制数的高位有效位，依次排列起来。
   *
   *
   * @param n
   * @param base
   * @return
   */
  public static String convert(int n, int base) {
    Stack<Character> data = new Stack<>();
    while (n > 0) {
      // 余数入栈
      data.push(digit[n % base]);
      // n更新为对base的除商
      n /= base;
    }

    char[] result = new char[data.size()];
    int i = 0;
    while (!data.isEmpty()) {
      result[i] = data.pop();
      i++;
    }

    return Arrays.toString(result);
  }

  /**
   * 将十六进制转为10进制
   *
   * @param source 十六进制数如：
   * @return
   */
  public static Long convert10(String source) {
    if (source == null || source.length() == 0) {
      return null;
    }

    if (source.startsWith("0x")) {
      source = source.substring(2);
    }

    if (source.length() == 0) {
      return null;
    }

    long result = 0;

    char[] chars = source.toCharArray();
    int length = chars.length;
    int i = length - 1;

    while (i >= 0) {
      char aChar = chars[i];

      // 将char转化为数字
      int bit = toInteger(aChar);

      result += bit * Math.pow(16, length - 1 - i);
      i--;
    }

    return result;
  }

  private static int toInteger(char aChar) {
    String str = String.valueOf(aChar);
    if ("A".equals(str)) {
      return 10;
    }

    if ("B".equals(str)) {
      return 11;
    }

    if ("C".equals(str)) {
      return 12;
    }

    if ("D".equals(str)) {
      return 13;
    }

    if ("E".equals(str)) {
      return 14;
    }

    if ("F".equals(str)) {
      return 15;
    }

    return Integer.parseInt(str);
  }
}

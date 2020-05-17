package me.mingshan.algorithm.stack;

import java.util.Stack;

/**
 * 括号匹配，给定一个字符数据，判断在给定区间是否括号匹配
 */
public class ParenthesisMatch {

  public static void main(String[] args) {
    char[] a1 = { '(', '1', '2', ')' };
    char[] a2 = { '(', '1', '2', ')', ')' };

    System.out.println(parenthesisMatch(a1, 0, a1.length - 1));
    System.out.println(parenthesisMatch(a2, 0, a2.length - 1));
  }

  public static boolean parenthesisMatch(char[] source, int begin, int end) {
    if (source == null || source.length == 0) {
      return true;
    }

    if (begin < 0 || begin > source.length - 1 || begin > end) {
      throw new IllegalArgumentException("The begin is invalid");
    }

    if (end > source.length - 1) {
      throw new IllegalArgumentException("The end is invalid");
    }

    Stack<Character> data = new Stack<>();

    for (int i = begin; i <= end; i++) {
      if (source[i] != '(' && source[i] != ')') {
        continue;
      }

      if (source[i] == '(') {
        data.push(source[i]);
      } else if (!data.isEmpty()) {
        data.pop();
      } else {
        return false;
      }
    }

    return data.isEmpty();
  }
}

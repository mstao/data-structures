package me.mingshan.leetcode;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_20_IsValid {

  public static void main(String[] args) {
    String s = "()[]{}";
    System.out.println(isValid(s));

    String s2 = "([{}])";
    System.out.println(isValid(s2));

    String s3 = "([]){}";
    System.out.println(isValid(s3));

    String s4 = "(]";

    System.out.println(isValid(s4));
  }

  public static boolean isValid(String s) {
    if (s == null || s.length() == 0) {
      return false;
    }

    Stack<Character> stack = new Stack<>();

    int n = s.length();
    char[] chars = s.toCharArray();

    for (int i = 0; i < n; i++) {
      String value = String.valueOf(chars[i]);
      if ("(".equals(value)) {
        stack.push(')');
      } else if ("{".equals(value)) {
        stack.push('}');
      } else if ("[".equals(value)) {
        stack.push(']');
      } else {
        if (stack.isEmpty() || !value.equals(String.valueOf(stack.pop()))) {
          return false;
        }
      }
    }

    return stack.isEmpty();
  }
}

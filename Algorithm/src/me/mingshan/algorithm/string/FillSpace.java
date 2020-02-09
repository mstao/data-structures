package me.mingshan.algorithm.string;

/**
 * 给定一个字符串，将字符串中的数字用`\0`代替，要求时间复杂度为O(n)
 */
public class FillSpace {

  public static void main(String[] args) {
    String s = "Hello world";
    System.out.println(solution(s));
  }

  public static String solution(String source) {
    if (source == null || source.length() == 0) {
      return source;
    }

    char[] chars = source.toCharArray();
    int countSpace = 0;
    for (int i = 0; i < chars.length; i++) {
      if (chars[i] == ' ') {
        countSpace++;
      }
    }

    int p, q;

    return "";
  }
}

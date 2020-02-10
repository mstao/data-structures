package me.mingshan.algorithm.string;

/**
 * 给定一个字符串，将字符串中的数字用`\0`代替，要求时间复杂度为O(n)
 */
public class FillSpace {

  public static void main(String[] args) {
    String s = "He  llo wor  ld  ";
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

    char[] dest = new char[source.length() + countSpace * 2];

    int p = dest.length - 1;

    for (int i = chars.length - 1; i >= 0; i--) {
      if (chars[i] == ' ') {
        dest[p] = '0';
        dest[p -1] = '\\';
        p = p - 2;
      } else {
        dest[p] = chars[i];
        p--;
      }
    }

    return String.valueOf(dest);
  }
}

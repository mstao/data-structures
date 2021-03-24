package me.mingshan.algorithm.interview;

/**
 * 在30分钟内,用java语言写一段程序,从一段很长的字符串中（例如sdfs324b4f9ABAbeC79p1222222221alsdkf）,
 *   把所有长度大于8的正向或反向读都一样的字符串打印出来(例如1222222221)
 */
public class PrintPalindrome {

  public static void main(String[] args) {
    printPalindrome("sdfs324b4f9ABAbeC79p1222222221alsdkf");
  }

  public static void printPalindrome(String s) {
    if (s == null || "".equals(s)) {
      return;
    }

    for (int i = 0; i < s.length(); i++) {
      for (int j = i + 8; j < s.length(); j++) {
        String substring = s.substring(i, j + 1);
        boolean palindrome = isPalindrome(substring);

        if (palindrome) {
          System.out.println(substring);
        }
      }
    }
  }

  private static boolean isPalindrome(String str) {
    if (str == null || str.length() == 0) {
      return false;
    }

    char[] chars = str.toCharArray();

    for (int i = 0; i < str.length() / 2; i++) {
      char first = chars[i];
      char last = chars[str.length() - 1 - i];

      if (first != last) {
        return false;
      }
    }

    if (str.length() % 2 == 0) {
      char before = chars[str.length() / 2 - 1];
      char next = chars[str.length() / 2];
      if (before == next) {
        return true;
      } else {
        return false;
      }
    }

    return true;
  }
}

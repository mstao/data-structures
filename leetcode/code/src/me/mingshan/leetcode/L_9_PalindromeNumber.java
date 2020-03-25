package me.mingshan.leetcode;

/**
 * https://leetcode-cn.com/problems/palindrome-number/
 */
public class L_9_PalindromeNumber {

  public static void main(String[] args) {
    System.out.println(isPalindrome(121));
    System.out.println(isPalindrome(11));
    System.out.println(isPalindrome(1231));
    System.out.println(isPalindrome(-121));
    System.out.println(isPalindrome(2121));
    System.out.println(isPalindrome(5115));
  }

  public static boolean isPalindrome(int x) {
    if (x < 0) {
      return false;
    }

    char[] chars = String.valueOf(x).toCharArray();

    if (chars.length == 1) {
      return true;
    }

    boolean result = true;

    for (int i = 0; i < chars.length; i++) {
      char item = chars[i];
      if (item != chars[chars.length - i - 1]) {
        result = false;
        break;
      }
    }

    return result;
  }
}

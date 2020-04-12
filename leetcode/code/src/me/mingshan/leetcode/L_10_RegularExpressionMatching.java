package me.mingshan.leetcode;

/**
 * https://leetcode-cn.com/problems/regular-expression-matching/
 *
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 *
 * @author mingshan
 */
public class L_10_RegularExpressionMatching {

  public static void main(String[] args) {
    System.out.println(isMatch("ABCASAD", "SA"));
  }

  public static boolean isMatch(String s, String p) {
    if (s == null || p == null) {
      return false;
    }

    return false;
  }
}

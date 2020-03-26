package me.mingshan.leetcode;

/**
 * https://leetcode-cn.com/problems/string-to-integer-atoi/
 */
public class L_8_Atoi {

  public static void main(String[] args) {
    System.out.println(solution("     "));
    System.out.println(solution("42"));
    System.out.println(solution("      111"));
    System.out.println(solution("      -1211"));
    System.out.println(solution("      -1-211"));
    System.out.println(solution("-91283472332"));
    System.out.println(solution("      ac"));
    System.out.println(solution("4193 with words"));
    System.out.println(solution("3.134353"));
    System.out.println(solution("   +0 123"));
  }

  public static int solution(String str) {
    if (str == null) {
      return 0;
    }

    boolean isMinus = false;
    char whiteSpace = ' ';
    char minus = '-';
    char plus = '+';
    boolean firstLegal = false;

    StringBuilder sb = new StringBuilder();

    char[] chars = str.toCharArray();
    for (char aChar : chars) {
      if (!firstLegal) {
        if (aChar == whiteSpace) {
          continue;
        }

        if ((aChar == minus || aChar == plus)) {
          if (aChar == minus) {
            isMinus = true;
          }

          firstLegal= true;
          continue;
        }

        if (!Character.isDigit(aChar)) {
          return 0;
        }

        firstLegal= true;
      }

      if (Character.isDigit(aChar)) {
        sb.append(aChar);
      } else {
        break;
      }
    }

    String replace = sb.toString();
    if (replace.length() == 0) {
      return 0;
    }

    int result;

    try {
      result = Integer.parseInt(replace);
    } catch (NumberFormatException e) {
      if (isMinus) {
        return Integer.MIN_VALUE;
      } else {
        return Integer.MAX_VALUE;
      }
    }

    return isMinus ? -result : result;
  }
}

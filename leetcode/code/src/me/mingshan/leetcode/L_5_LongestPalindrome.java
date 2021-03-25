package me.mingshan.leetcode;

/**
 *  最长回文子串 (https://leetcode-cn.com/problems/longest-palindromic-substring/)
 *
 *  给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * @author mingshan
 */
public class L_5_LongestPalindrome {

  public static void main(String[] args) {
    System.out.println(longestPalindrome("weeww"));
    System.out.println(longestPalindrome("babad"));
    System.out.println(longestPalindrome("cbbd"));
    System.out.println(longestPalindrome("a"));
    System.out.println(longestPalindrome("acd"));
    System.out.println(longestPalindrome("vaomxdtiuwqlwhgutkhxxhccsgvyoaccuicgybnqnslogtqhblegfudagpxfvjdacsxgevvepuwthdtybgflsxjdmmfumyqgpxatvdypjmlapccaxwkuxkilqqgpihyepkilhlfkdrbsefinitdcaghqmhylnixidrygdnzmgubeybczjceiybowglkywrpkfcwpsjbkcpnvfbxnpuqzhotzspgebptnhwevbkcueyzecdrjpbpxemagnwmtwikmkpqluwmvyswvxghajknjxfazshsvjkstkezdlbnkwxawlwkqnxghjzyigkvqpapvsntojnxlmtywdrommoltpbvxwqyijpkirvndwpgufgjelqvwffpuycqfwenhzrbzbdtupyutgccdjyvhptnuhxdwbmdcbpfvxvtfryszhaakwshrjseonfvjrrdefyxefqfvadlwmedpvnozobftnnsutegrtxhwitrwdpfienhdbvvykoynrsbpmzjtotjxbvemgoxreiveakmmbbvbmfbbnyfxwrueswdlxvuelbkrdxlutyukppkzjnmfmclqpkwzyylwlzsvriwomchzzqwqglpflaepoxcnnewzstvegyaowwhgvcwjhbbstvzhhvghigoazbjiikglbqlxlccrwqvyqxpbtpoqjliziwmdkzfsrqtqdkeniulsavsfqsjwnvpprvczcujihoqeanobhlsvbzmgflhykndfydbxatskf"));
  }

  public static String longestPalindrome(String s) {
    if (s == null || "".equals(s)) {
      return "";
    }

    String result = "";

    for (int i = 0; i < s.length(); i++) {
      for (int j = i; j < s.length(); j++) {
        String substring = s.substring(i, j + 1);
        boolean palindrome = isPalindrome(substring);

        if (palindrome) {
          if (substring.length() > result.length()) {
            result = substring;
          }
        }
      }
    }

    return result;
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

  /**
   * 中心扩散法
   *
   * @param s
   * @return
   */
  public static String longestPalindrome2(String s) {
    if (s == null || "".equals(s)) {
      return "";
    }

    String result = "";

    for (int i = 0; i < s.length() - 1; i++) {

    }

    return result;
  }

  /**
   * 动态规划算法
   *
   * @param s
   * @return
   */
  public static String longestPalindrome3(String s) {
    if (s == null || "".equals(s)) {
      return "";
    }

    String result = "";

    for (int i = 0; i < s.length() - 1; i++) {
      //
    }

    return result;
  }
}

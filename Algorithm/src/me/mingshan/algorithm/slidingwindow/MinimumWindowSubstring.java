package me.mingshan.algorithm.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。(minimum-window-substring)
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 */
public class MinimumWindowSubstring {

  public static void main(String[] args) {
    String s = "ADOBECODEBANC";
    String t = "ABC";
    System.out.println(solution(s, t));
  }

  public static String solution(String s, String t) {
    if (s == null || t == null || s.length() < t.length()) {
      return null;
    }

    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      char c = t.charAt(i);
      map.merge(c, 1, Integer::sum);
    }

    int left = 0;
    int right = 0;
    int count = t.length();
    int max = Integer.MAX_VALUE;
    String res = s;

    while (right < s.length()) {
      char r = s.charAt(right);
      if (map.get(r) != null && map.get(r) > 0) {
        count--;
      }

      if (map.containsKey(r)) {
        map.put(r, map.get(r) - 1);
      }
      right++;

      while (count == 0) {
        if (right - left < max) {
          max = right - left;
          res = s.substring(left, right);
        }
        char le = s.charAt(left);
        map.merge(le, 1, Integer::sum);
        if (map.get(le) != null && map.get(le) > 0) {
          count++;
        }
        left++;
      }
    }

    return max == Integer.MAX_VALUE ? "" : res;
  }
}

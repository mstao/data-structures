package me.mingshan.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * "abcavc"
 *
 * @Author: mingshan
 * @Date: Created in 17:10 2018/9/16
 */
public class L_3_LengthOfLongestSubstring {

    public static void main(String[] args) {
        String a = "abcavc";

        L_3_LengthOfLongestSubstring x = new L_3_LengthOfLongestSubstring();
        System.out.print(x.lengthOfLongestSubstring(a));
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), answer = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int j = 0, i = 0; j < n; j++) {
            char x = s.charAt(j);

            if (map.containsKey(x)) {
                i = Math.max(map.get(x), i);
            }
            answer = Math.max(answer, j - i + 1);
            map.put(x, j + 1);
        }
        return answer;
    }
}

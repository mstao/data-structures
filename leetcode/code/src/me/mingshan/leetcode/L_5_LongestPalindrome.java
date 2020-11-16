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
        L_5_LongestPalindrome obj = new L_5_LongestPalindrome();
        obj.longestPalindrome("weeww");
    }

    public String longestPalindrome(String s) {
        if (s == null || "".equals(s)) {
            return "";
        }

        char[] arr = s.toCharArray();
        

        return "";
    }
}

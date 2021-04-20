package me.mingshan.leetcode;

/**
 * 28. 实现 strStr()
 *
 * 实现 strStr() 函数。
 *
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
 *
 *  
 *
 * 说明：
 *
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 * 示例 2：
 *
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 * 示例 3：
 *
 * 输入：haystack = "", needle = ""
 * 输出：0
 *  
 *
 * 提示：
 *
 * 0 <= haystack.length, needle.length <= 5 * 104
 * haystack 和 needle 仅由小写英文字符组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_28_StrStr {

  public static void main(String[] args) {
    System.out.println(strStr("12345", "23"));

    System.out.println(strStr("12345", "12"));

    System.out.println(strStr("12345", "45"));

    System.out.println(strStr("123", "2345"));

    System.out.println(strStr("mississippi", "issipi"));
  }

  /**
   * 相当于 java 的 indexOf 函数
   *
   * 思路：
   *
   * 1. 直接暴力
   * 2. TODO kmp解法
   *
   * @param haystack
   * @param needle
   * @return
   */
  public static int strStr(String haystack, String needle) {
    if (needle == null || needle.length() == 0) {
      return 0;
    }

    if (haystack == null || haystack.length() == 0) {
      return -1;
    }

    char[] haystackChars = haystack.toCharArray();
    char[] needleChars = needle.toCharArray();

    if (haystackChars.length < needleChars.length) {
      return -1;
    }

    for (int i = 0; i < haystackChars.length; i++) {
      // 如果第一位相等，那么继续向前探测needle的长度，如果全部符合，直接返回即可
      if (haystackChars[i] == needleChars[0]) {
        boolean match = true;
        // 从下一位开始，到needleChars长度结束
        for (int j = i + 1; j < i + needleChars.length; j++) {
          // 比较完之后，还匹配不上，直接返回
          if (j == haystackChars.length) {
            match = false;
            break;
          } else {
            if (haystackChars[j] != needleChars[j - i]) {
              match = false;
              break;
            }
          }
        }

        if (match) {
          return i;
        }
      }
    }

    return -1;
  }

}

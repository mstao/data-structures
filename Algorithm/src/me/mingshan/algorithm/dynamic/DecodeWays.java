package me.mingshan.algorithm.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 * 解密字符窜
 *
 * 现有字符加密方式
 *
 * A - 1, B - 2 ,,,, Z - 26
 *
 * 现给定一个字符串，求有多少解密方式
 *
 * 例如 12，可以解密为 AB(1 和 2) 或者 L，共有两种
 *
 * https://leetcode-cn.com/problems/decode-ways/
 */
public class DecodeWays {

  public static void main(String[] args) {
    //System.out.println(numDecodings("0"));
    //System.out.println(numDecodings("12"));
    //System.out.println(numDecodings("123"));

    System.out.println(numDecodings("226"));
  }

  /**
   *
   * 解题思路：
   * 以 "123412"字符串为例，如果我们要知道该字符串有多少解密方式，只需要考虑 （12341 + 2） 和 （1234 + 12）共有多少种解密方式即可，
   * 为什么呢？ 因为最后一个英文字母Z的数字对应是26，只有两位数，所以只需要考虑两位数字即可
   *
   * 我们用f[n] 表示字符串前n位数字的解密方式数，
   *
   * 转移方程为：
   *
   * f[n] = (f[n-1] + 最后一位数字加密方式数) + (f[n -2] + 最后两位加密方式数）
   *
   * @param s
   * @return
   */
  public static int numDecodings(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }

    int[] f = new int[s.length() + 1];

    char[] chars = s.toCharArray();

    for (int i = 0; i < chars.length; i++) {
      int curr = Integer.parseInt(String.valueOf(chars[i]));
      if (curr <= 0) {
        return 0;
      }

      if (i == 0) {
        f[i + 1] = 1;
      } else {
        f[i + 1] = (f[i] + 1) + f[i - 1];

        if (i > 1) {
          int i2 = Integer.parseInt(String.valueOf(chars[i - 1]));

          if (i2 > 26) {
            f[i + 1] = f[i + 1] + 1;
          }
        }
      }
    }

    return f[s.length()];
  }
}

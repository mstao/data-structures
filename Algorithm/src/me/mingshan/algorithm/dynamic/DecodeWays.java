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
    System.out.println(numDecodings2("226"));

    System.out.println(numDecodings("1202"));
    System.out.println(numDecodings2("1202"));

    System.out.println(numDecodings("1223"));
    System.out.println(numDecodings2("1223"));

    System.out.println(numDecodings("1234"));
    System.out.println(numDecodings2("1234"));
  }

  /**
   * 226
   *
   * f[0] = 1
   * f[1] = 1
   *
   * 解题思路：由于是给定数字字符串来解析字母的，最大的字母为Z，对应数字为26，这个是限制条件1，
   * 如果字符串包含0，那么含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20"，其他都不包含
   *
   * 用f[i] 表示第i为
   * 对于字符的任意一位 i,我们可以推断其转移方程为：  f[i] = f[i - 2] + f[i -1]
   * 上面的等式成立的话，需要将上面的条件也添加进去，即：
   *
   * 1. 如果要加上f[i - 2]，那么i位置的前两位必须是可解码的，即在10 ~ 26 之间，
   * 像01,03这种是无效的。
   *
   * 2. 如果要加上f[i-1]，那么i位置的数字必须不能是0，否则无法继续解码
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

    f[0] = 1;
    f[1] = chars[0] == '0' ? 0 : 1;

    for (int i = 2; i <= s.length(); i++) {
      // 如果要加上f[i - 2]，那么i位置的前两位必须是可解码的，即在10 ~ 26 之间 像01,03这种是无效的。
      String is2 = String.valueOf(chars[i - 2]) + String.valueOf(chars[i - 1]);

      int i2 = Integer.parseInt(is2);
      if (i2 >= 10 && i2 <= 26) {
        f[i] += f[i-2];
      }

      // 如果要加上f[i-1]，那么i位置的数字必须不能是0，否则无法继续解码
      int i1 = Integer.parseInt(String.valueOf(chars[i - 1]));

      if (i1 != 0) {
        f[i] += f[i-1];
      }
    }

    return f[s.length()];
  }

  public static int numDecodings2(String s) {
    if(s.length() == 0) {
      return 0;
    }

    int[] dp = new int[s.length() + 1];
    // 初始化第一种解码方式
    dp[0] = 1;
    // 如果第一位是0，则无法解码
    dp[1] = s.charAt(0) == '0' ? 0 : 1;
    for (int i = 2; i <= s.length(); i++) {
      // 如果字符串的第i-1位和第i位能组成一个10到26的数字，说明我们可以在第i-2位的解码方法上继续解码
      String i2 = s.substring(i - 2, i);
      if(Integer.parseInt(i2) <= 26 && Integer.parseInt(i2) >= 10){
        dp[i] += dp[i - 2];
      }
      // 如果字符串的第i-1位和第i位不能组成有效二位数字，在第i-1位的解码方法上继续解码
      String i1 = s.substring(i - 1, i);
      if(Integer.parseInt(i1) != 0){
        dp[i] += dp[i - 1];
      }
    }
    return dp[s.length()];
  }
}

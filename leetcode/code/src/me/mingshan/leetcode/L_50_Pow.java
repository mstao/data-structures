package me.mingshan.leetcode;

/**
 * 50. Pow(x, n)
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 *
 * https://leetcode-cn.com/problems/powx-n/
 *
 * @author Walker Han
 * @date 2021/6/8 14:30
 */
public class L_50_Pow {

  public static void main(String[] args) {
    System.out.println(myPow2(2, 10));
    System.out.println(myPow2(2.1, 3));
    System.out.println(myPow2(2, -2));
  }

  /**
   * 直接for循环解法，耗时较大，leetcode 不通过
   *
   * @param x
   * @param n
   * @return
   */
  public static double myPow(double x, int n) {
    if (x == 0 && n < 0) {
      throw new IllegalArgumentException("无效的参数");
    }

    if (n == 0) {
      return 1;
    }

    int c = n;
    if (n < 0) {
      c = -n;
    }

    double result = x;

    for (int i = 2; i <= c; i++) {
      result = result * x;
    }

    if (n < 0) {
      return 1 / result;
    }

    return result;
  }

  public static double myPow2(double x, int n) {
    if (x == 0 && n < 0) {
      throw new IllegalArgumentException("无效的参数");
    }

    if (n == 0) {
      return 1;
    }

    int c = n;
    if (n < 0) {
      c = -n;
    }

    double result = cPow(x, c);
    if (n < 0) {
      return 1 / result;
    }

    return result;
  }

  private static double cPow(double x, int n) {
    if (n == 0) {
      return 1;
    }
    if (n == 1) {
      return x;
    }

    boolean even = n % 2 == 0;

    if (even) {
      return cPow(x, n / 2) * 2;
    } else {
      return x * (cPow(x, (n - 1) / 2) * 2);
    }
  }
}

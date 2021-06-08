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
    System.out.println(myPow(2, 11));
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

  /**
   * 二分：
   *
   * 该题 O(N)时间复杂度不会通过，可以使用二分：
   * 即 5的10次方可以由两个5的5次方相乘计算得到。
   *
   * @param x
   * @param n
   * @return
   */
  public static double myPow2(double x, int n) {
    if (x == 0 && n < 0) {
      throw new IllegalArgumentException("无效的参数");
    }

    if (n == 0) {
      return 1;
    }

    if (x == 1) {
      return x;
    }

    if (x == -1) {
      if (n % 2 == 0) {
        return 1;
      } else {
        return -1;
      }
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

    double result = cPow(x, n / 2);
    if (n % 2 == 0) {
      return result * result;
    } else {
      return x * result * result;
    }
  }
}

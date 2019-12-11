package me.mingshan.algorithm.dynamic;

/**
 * fabnacci 数列动态规划
 *
 * @author mingshan
 */
public class Fabnacci {

  public static void main(String[] args) {
    System.out.println(fabnacci1(4));
  }

  /**
   * 普通递归
   *
   * 由上而下进行求解
   *
   * @param n
   * @return
   */
  private static long fabnacci1(int n) {
    if (n < 0) {
      return -1;
    }
    if (n == 0) {
      return 0;
    }
    if (n <= 1) {
      return 1;
    }

    return fabnacci1(n - 1) + fabnacci1(n - 2);
  }

  /**
   * 动态规划
   * 由下而上进行求解
   *
   * @param n
   * @return
   */
  private static long fabnacci2(int n) {
    if (n < 0) {
      return -1;
    }
    if (n == 0) {
      return 0;//初始值为0
    }
    if (n == 1) {
      return 1;//定义f（1）为1，否者结果都是0；
    }

    int result = 0;
    int r1 = 0;
    int r2 = 1;
    for (int i = 2; i <= n; i++) {
      result = r1 + r2;
      r1 = r2;
      r2 = result;
    }

    return result;//返回结果
  }
}

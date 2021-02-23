package me.mingshan.algorithm.dynamic;

/**
 *
 * F(0)=0，F(1)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 2，n ∈ N*）
 *
 * fabnacci 数列动态规划
 *
 * @author mingshan
 */
public class Fabnacci {

  public static void main(String[] args) {
    System.out.println(fabnacci1(3));

    System.out.println(fabnacci3(3));
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
    if (n == 1) {
      return 1;
    }
    if (n == 2) {
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

    if (n == 2) {
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

  private static long fabnacci3(int n) {
    if (n < 0) {
      return -1;
    }
    if (n == 0) {
      return 0;//初始值为0
    }
    if (n == 1) {
      return 1;//定义f（1）为1，否者结果都是0；
    }

    int[] f = new int[3];

    f[0] = 0;
    f[1] = 1;
    f[2] = 1;

    for (int i = 3; i <= n; i++) {
      f[i%3] = f[(i - 1)%3] + f[(i-2) %3];
    }

    return f[n%3];
  }
}

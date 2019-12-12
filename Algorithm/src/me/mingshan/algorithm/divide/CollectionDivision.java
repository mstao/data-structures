package me.mingshan.algorithm.divide;

/**
 * 集合划分【第二类Stirling数】
 *
 * F（n,m）表示把n个元素的集合分为m个子集，有多少种分法？
 *
 * 通项公式：
 * F(n,m) = F(n-1,m-1) + m * F(n-1,m)
 *
 * @author mingshan
 */
public class CollectionDivision {

  public static void main(String[] args) {
    System.out.println(divide(30, 2));
  }

  public static long divide(int n, int m) {
    if (m > n) {
      return 0;
    }

    if (m == 1 || m == n) {
      return 1;
    }

    return divide(n-1,m-1)+m*divide(n-1, m);
  }

}

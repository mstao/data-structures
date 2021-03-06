package me.mingshan.algorithm.divide;

/**
 * 集合划分【第二类Stirling数】
 *
 * F（n,m）表示把n个元素的集合分为m个子集，有多少种分法？
 *
 * 思路：
 *
 * 把n个元素编号,对於最后那个n号元素,有两种情况.一种是独立组成一个集合,另一种是和别的元素混在一起：
 *
 * 对於第一种情况,等价于把前n-1个元素分成m-1份,然后n号元素单独放.
 * 对於第二种情况,等价于把前n-1个元素分成m份,然后把n号元素放入这m个集合中的一个（也就是说有m种放法）
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

    return divide(n-1,m-1) + m*divide(n-1, m);
  }

}

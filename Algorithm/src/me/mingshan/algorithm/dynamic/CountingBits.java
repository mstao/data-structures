package me.mingshan.algorithm.dynamic;

/**
 * 给出一个 非负 整数 num，对所有满足 0 ≤ i ≤ num 条件的数字 i 均需要计算其二进制表示中数字 1 的个数并以数组的形式返回。
 *
 * https://www.lintcode.com/problem/664/
 *
 * @author Walker Han
 * @date 2021/3/2 9:19
 */
public class CountingBits {

  public int[] countBits(int num) {
    // write your code here
    int[] f = new int[num+1];

    for (int i = 0; i <= num; i++) {
      f[i] = f[i >> 1] + (i & 1);
    }

    return f;
  }
}

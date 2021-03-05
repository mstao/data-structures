package me.mingshan.algorithm.binary;

/**
 * 计算输入的数字的二进制表示有多少个1
 *
 * 例如6的二进制为：00000000 000000000 00000000 00000110
 * 所以结果为2
 *
 * @author mingshan
 */
public class Count1 {

  public static void main(String[] args) {
    System.out.println(solution(15));
    System.out.println(solution(-15));
  }

  public static int solution(int source) {
    int count = 32;
    int result = 0;

    while (count > 0) {
      if ((source & 1) == 1) {
        result++;
      }

      source = source >> 1;
      count--;
    }

    return result;
  }

  /**
   * 动态规划
   *
   * @param num
   * @return
   */
  public int[] solution2(int num) {
    // write your code here
    int[] f = new int[num+1];

    for (int i = 0; i <= num; i++) {
      f[i] = f[i >> 1] + (i & 1);
    }

    return f;
  }
}

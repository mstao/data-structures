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
}

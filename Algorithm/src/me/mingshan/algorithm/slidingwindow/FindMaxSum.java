package me.mingshan.algorithm.slidingwindow;

/**
 * 给定一个整数数组，计算长度为 'k' 的连续子数组的最大总和。
 *
 * 例如：
 * 输入：arr [] = {100,200,300,400}
 *      k = 2
 *
 * 输出：700
 *
 * 解释：300 + 400 = 700
 *
 */
public class FindMaxSum {

  public static void main(String[] args) {
    int[] arr = {100,200,300,400, 500, 300, 200, 600, 1000};
    System.out.println(solution(arr, 2));
  }

  public static int solution(int[] source, int k) {
    int result = 0;
    if (source == null || source.length == 0) {
      return result;
    }

    int lastResult = 0;
    for (int i = 0; i < k; i++) {
      result = lastResult = result + source[i];
    }

    for (int i = k - 1; i < source.length - k + 1; i++) {
      lastResult = lastResult - source[i - 1] + source[i + k - 1];
      result = Math.max(lastResult, result);
    }

    return result;
  }

  public static int solution2(int[] source, int k) {
    int result = 0;
    if (source == null || source.length == 0) {
      return result;
    }

    for (int i = 0; i < source.length - k + 1; i++) {
      int currSum = 0;
      for (int j = 0; j < k; j++) {
        currSum = currSum + source[i + j];
      }

      result = Math.max(result, currSum);
    }

    return result;
  }
}

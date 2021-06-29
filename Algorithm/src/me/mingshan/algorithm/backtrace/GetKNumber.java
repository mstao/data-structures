package me.mingshan.algorithm.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 例题： 从1，... ，n中取出k个数，要求不重复。
 *
 * n=4, k=2,   1,2 1,3 1,4 2,3 2,4 3,4
 *
 * https://blog.csdn.net/u012156116/article/details/79673523
 */
public class GetKNumber {
  public static List<List<Integer>> result = new ArrayList<>();

  public static void main(String[] args) {
    System.out.println(combine(3, 2));
  }

  public static List<List<Integer>> combine(int n, int k) {
    List<Integer> list = new ArrayList<>();
    backtracking(n, k, 1, list);

    return result;
  }

  /**
   *
   *
   * @param n
   * @param k 多少个数
   * @param start 起始位置
   * @param list 起始位置存放数据的集合
   */
  public static void backtracking(int n, int k, int start,List<Integer> list) {
    if (k < 0) {
      return;
    } else if (k == 0) {
      System.out.println("-----");
      result.add(new ArrayList<>(list));
    } else {
      for (int i = start; i <= n; i++) {
        System.out.println("k = " + k + ", start = " + start + ", i = " + i);
        list.add(i);                      // 添加元素
        backtracking(n, k - 1, i + 1, list);
        System.out.println("=====");
        list.remove(list.size() - 1);       // 回退
      }
    }
  }

}

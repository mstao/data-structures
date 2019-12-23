package me.mingshan.algorithm.distinct;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 将已排好序的数列去重
 *
 * @author hanjuntao
 */
public class IntegerDistinct {

  public static void main(String[] args) {
    List<Integer> source = Arrays.asList(1, 1, 1, 3, 3, 5, 6, 7, 8, 8, 8, 9);
    List<Integer> distinct = distinct(source);

    distinct.forEach(System.out::println);
  }

  /**
   * 将已排好序的数列去重
   *
   * @param source 源
   * @return 结果
   */
  public static List<Integer> distinct(List<Integer> source) {
    if (source == null || source.isEmpty()) {
      return Collections.emptyList();
    }

    int i = 0, j;

    for (j = 1; j < source.size(); j++) {
      int prev = i + 1;
      if (!source.get(j).equals(source.get(i))) {
        if (!source.get(j).equals(source.get(prev))) {
          source.set(prev, source.get(j));
        }
        i = prev;
      }
    }

    return source.subList(0, i + 1);
  }
}

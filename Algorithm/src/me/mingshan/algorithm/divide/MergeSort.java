package me.mingshan.algorithm.divide;

import java.util.Arrays;

/**
 * 归并排序
 *
 * 利用分治思想
 *
 * @author mingshan
 */
public class MergeSort {
  public static void main(String[] args) {
    int[] source = {1, 4, 3, 5, 9, 7};
    sort(source, 0, source.length - 1);

    System.out.println(Arrays.toString(source));
  }

  public static void sort(int[] source, int low, int high) {

  }

  /**
   * 合并两个有序数组
   *
   * 两个数组的元素分别为：
   * source[low] ~ source[mid]
   * source[low + 1] ~ source[high]
   *
   * @param source 原数组
   * @param low 低位
   * @param mid 低位和高位的中间位置
   * @param high 高位
   */
  private static void merge(int[] source, int low, int mid, int high) {
    
  }

  /**
   * 交换a,b两个位置的元素
   *
   * @param a 位置a
   * @param b 位置b
   */
  public static void swap(int[] source, int a, int b) {
    int temp = source[a];
    source[a] = source[b];
    source[b] = temp;
  }

}

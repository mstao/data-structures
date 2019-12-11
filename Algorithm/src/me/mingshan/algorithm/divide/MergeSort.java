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
    int[] temp = new int[source.length];
    sort(source, 0, source.length - 1, temp);

    System.out.println(Arrays.toString(source));
  }

  public static void sort(int[] source, int low, int high, int[] temp) {
    if (low < high) {
      int mid = (low + high) / 2;
      sort(source, low, mid, temp);
      sort(source, mid + 1, high, temp);
      merge(source, low, mid, high, temp);
    }
  }

  /**
   * 合并两个有序数组
   *
   * 两个数组的元素分别为：
   * source[low] ~ source[mid] 升序
   * source[low + 1] ~ source[high] 升序
   *
   * @param source 原数组
   * @param low 低位
   * @param mid 低位和高位的中间位置
   * @param high 高位
   * @param temp 辅助数组
   */
  private static void merge(int[] source, int low, int mid, int high, int[] temp) {
    int i = low;
    int j = mid + 1;
    int k = 0;

    // 将两个数组的元素一次比较，记录在temp 数组里面
    while (i <= mid && j <= high) {
      if (source[i] > source[j]) {
        temp[k++] = source[j++];
      } else {
        temp[k++] = source[i++];
      }
    }

    // 如果左半边数组还有剩余，直接追加到temp 数组里面
    while (i <= mid) {
      temp[k++] = source[i++];
    }

    // 如果右半边数组还有剩余，直接追加到temp 数组里面
    while (j <= high) {
      temp[k++] = source[j++];
    }

    // 将temp数组的元素移动原数组
    for (i = 0; i < k; i++) {
      source[low + i] = temp[i];
    }
  }
}

package me.mingshan.algorithm.divide;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 归并排序
 *
 * 利用分治思想
 *
 * @author mingshan
 */
public class MergeSort {
  public static void main(String[] args) {
    int[] source = new int[6];
    source[0] = 1;
    source[1] = 4;
    source[1] = 3;
    source[1] = 5;
    source[1] = 9;
    source[1] = 7;

    sort(source, 0, source.length - 1);

    System.out.println(Arrays.toString(source));
  }

  public static void sort(int[] source, int low, int high) {
    if (low < high) {
      int mid = (low + high) / 2;
      sort(source, low, mid);
      sort(source, mid + 1, high);
      merge(source, low, mid, high);
    }
  }

  /**
   * 合并两个有序数组，需要辅助数组
   *
   * 两个数组的元素分别为：
   * source[low] ~ source[mid] 升序
   * source[low + 1] ~ source[high] 升序
   *
   * @param source 原数组
   * @param low 低位
   * @param mid 低位和高位的中间位置
   * @param high 高位
   */
  private static void merge(int[] source, int low, int mid, int high) {
    int i = low;
    int j = mid + 1;
    int k = 0;

    int[] temp = new int[high-low+1];

    // 将两个数组的元素依次比较，记录在temp 数组里面
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

    // 使用内部数组copy，提高性能
    System.arraycopy(temp, 0, source, low, high - low + 1);
  }

  /**
   * 不使用辅助数组
   *
   * 两个数组的元素分别为：
   * source[low] ~ source[mid] 升序
   * source[low + 1] ~ source[high] 升序
   *
   * @param source 当前数组
   * @param low 最低位
   * @param mid 中间位置
   * @param high 最高位
   */
  private static void merge2(int[] source, int low, int mid, int high, int[] temp) {
    int i = mid;
    int j = high;

    int currIndex = high;
    while (i >=0 && j >=0) {
      if (source[i] > source[j]) {
        temp[currIndex] = source[i];
        i--;
      } else {
        temp[currIndex] = source[j];
        j--;
      }

      currIndex--;
    }

    // 如果后面的数组没比较完
    while (j >= 0) {
      temp[j] = source[j];
      j--;
    }

    // 如果后面的数组没比较完
    while (i >= 0) {
      temp[i] = source[i];
      i--;
    }
  }
}

package me.mingshan.leetcode;

import java.util.Arrays;

/**
 * 912. 排序数组
 *
 * 给你一个整数数组 nums，请你将该数组升序排列。
 *
 * https://leetcode-cn.com/problems/sort-an-array/
 */
public class L_912_SortArray {

  public static void main(String[] args) {
    int[] a1 = {5,2,3,1};

    System.out.println(Arrays.toString(sortArray(a1)));
  }


  public static int[] sortArray(int[] nums) {
    mergeSort(nums, 0, nums.length-1);
    return nums;
  }


  public static void mergeSort(int[] source, int low, int high) {
    int[] temp = new int[source.length];
    realMergeSort(source, low, high, temp);
  }

  private static void realMergeSort(int[] source, int low, int high, int[] temp) {
    if (low < high) {
      int mid = (low + high) / 2;
      realMergeSort(source, low, mid, temp);
      realMergeSort(source, mid + 1, high, temp);

      merge(source, low, mid, high, temp);
    }
  }

  /**
   * 将low ~ mid 和 mid ~ high 已排序的数组合并
   *
   * @param source
   * @param low
   * @param mid
   * @param high
   * @param temp
   */
  private static void merge(int[] source, int low, int mid, int high, int[] temp) {
    int i = low;
    int j = mid + 1;

    int k = 0;

    while (i <= mid && j <= high) {
      if (source[i] > source[j]) {
        temp[k++] = source[j++];
      } else {
        temp[k++] = source[i++];
      }
    }

    while (i <= mid) {
      temp[k++] = source[i++];
    }

    while (j <= high) {
      temp[k++] = source[j++];
    }

    for (int z = 0; z < k; z++) {
      source[low + z] = temp[z];
    }
  }


  /** 快排 */
  public static void quickSort(int[] source, int low, int high) {
    if (low < high) {
      int i = low;
      int j = high;
      // 找一个基准值
      int base = source[low];

      while (i != j) {
        // j 从右往左走，找到第一个比基准值小的
        while (i < j && source[j] >= base) {
          j--;
        }

        // i 从左往右走，找到第一个比基准值大的
        while (i < j && source[i] <= base) {
          i++;
        }

        if (i < j) {
          // 交换两者的数组
          swap(source, i, j);
        }
      }

      // i,j相遇后，交换基准位置与i的位置，保证i左边的，都小于该值，右边的，都大于该值
      swap(source, low, i);

      quickSort(source, low, i - 1);
      quickSort(source, i + 1, high);
    }
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
package me.mingshan.algorithm.divide;

/**
 * 二分搜索
 *
 * @author mingshan
 */
public class BinarySearch {

  public static void main(String[] args) {
    int[] source = {1, 3, 4, 5, 7, 9};
    System.out.println(search(source, 90, 0, source.length - 1));

  }

  public static int search(int[] source, int target, int low, int high) {
    int mid = (low + high) / 2;

    System.out.println(mid);

    if (low == high) {
      return target != source[low] ? -1 : low;
    }

    if (target == source[mid]) {
      return mid;
    } else if (target > source[mid]) {
      return search(source, target, mid + 1, high);
    } else {
      return search(source, target, low, mid - 1);
    }

  }
}

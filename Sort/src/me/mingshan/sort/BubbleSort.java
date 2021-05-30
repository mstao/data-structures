package me.mingshan.sort;

/**
 * 冒泡排序是一种交换排序，它的基本思想是：两两比较相邻记录的关键字，如果反序则交换，直到没有反序的记录为止。
 *
 * @author Walker Han
 * @date 2021/5/28 12:30
 */
public class BubbleSort {
  public static void main(String[] args) {
    int[] arr = {2,5,1,3,8,5,7,4,3};
    bubbleSort(arr);
    for (int x : arr) {
      System.out.println(x);
    }
  }

  /**
   * 冒泡排序
   * @param arr
   */
  private static void bubbleSort(int[] arr) {
    if(arr==null || arr.length < 2 ){
      return;
    }
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = 0; j < arr.length - i -1; j++) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
  }
}

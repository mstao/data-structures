package me.mingshan.sort;

public class SelectSort {

  public static void main(String[] args) {
    int[] arr = {2,5,1,3,8,5,7,4,3};
    selectSort(arr);
    for (int x : arr) {
      System.out.print(x + ",");
    }
  }

  private static void selectSort(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }

    int len = arr.length;

    for (int i = 0; i < len; i ++) {
      int min = Integer.MAX_VALUE;
      int x = 0;
      for (int j = i + 1; j < len; j++) {
        // 找到i+1 到 len的最小值
        if (arr[j] < min) {
          min = arr[j];
          x = j;
        }
      }

      if (min < arr[i]) {
        swap(arr, x, i);
      }
    }
  }

  private static void swap(int[] arr, int x, int i) {
    int temp = arr[x];
    arr[x] = arr[i];
    arr[i] = temp;
  }
}

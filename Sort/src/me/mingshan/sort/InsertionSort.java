package me.mingshan.sort;

/**
 * 直接插入排序
 * 
 * @author mingshan
 *
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] a = new int[] {3,1,4,9};
        insertionSort(a);
        for (int x : a) {
            System.out.println(x);
        }
    }

    public static void insertionSort(int[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j-1]) {
                swap(arr, j, j-1);
                j--;
            }
        }
    }

    private static void swap(int[] arr, int j, int i) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}

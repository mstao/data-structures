package me.mingshan.sort;

/**
 * 直接插入排序
 * 
 * @author mingshan
 *
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] a = new int[] {3, 8, 0, 1, 4, 9};
        insertionSort(a);
        //binaryInsertionSort(a);
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

    public static void binaryInsertionSort(int[] arr) {
        int len = arr.length;
        int low, high, mid;
        for (int i = 1; i < len; i++) {
            low = 0;
            high = i - 1;
            while (low <= high) {
                mid = (low + high) / 2;
                // 判断当前元素在mid位置的左边还是右边
                if (arr[i] > arr[mid]) {
                    // 查找右半部分
                    low = mid + 1;
                } else {
                    // 查找左半部分
                    high = mid - 1;
                }
            }

            int j = i;
            while (j > low && arr[j] < arr[j-1]) {
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

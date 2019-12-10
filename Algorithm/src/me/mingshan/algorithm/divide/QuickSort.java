package me.mingshan.algorithm.divide;

/**
 * 快速排序
 *
 * https://www.jianshu.com/p/2b2f1f79984e
 * https://www.jianshu.com/p/36ef33ed59ba
 *
 * @author mingshan
 *
 */
public class QuickSort {

    public static void sort(int[] source, int low, int high) {
        if (low < high) {
            int pivot = partition(source, low, high);
            sort(source, low, pivot - 1);
            sort(source, pivot + 1, high);
        }
    }

    static int partition(int[] source, int low , int high) {
        return source[low];
    }

    public static void sort3(int[] source, int low, int high) {
        if (low < high) {

            int pivot = partition(source, low, high);
            sort(source, low, pivot - 1);
            sort(source, pivot + 1, high);
        }
    }

    /**
     * 交换
     *
     * @param a
     * @param b
     */
    public static void swap(int[] source, int a, int b) {
        int temp = source[a];
        source[a] = source[b];
        source[b] = temp;
    }
}

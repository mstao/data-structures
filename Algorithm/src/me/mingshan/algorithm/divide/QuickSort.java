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

    public static void sort(int[] source, int low, int height) {
        if (low < height) {
            int pivot = partition(source, low, height);
            sort(source, low, pivot - 1);
            sort(source, pivot + 1, height);
        }
    }

    static int partition(int[] source, int low , int height) {
        return source[low];
    }

    public static void sort(int[] source) {
        if (source.length < 2) {
            return;
        }
        int pivot = source[0];

        int[] less = new int[source.length];
        int[] greater = new int[source.length];
        for (int item : source) {
            if (item > pivot) {
                //less.
            }
        }

        sort(less);
        sort(greater);
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

package me.mingshan.algorithm.divide;

import java.util.Arrays;

/**
 * 减而治之
 *
 * 任意给定数组a，将其数组内容前后颠倒
 *
 * @author mingshan
 */
public class Reverse {

    public static void main(String[] args) {
        int[] source = {1, 4, 3, 5};
        reverse(source, 0, source.length -1);
        System.out.println(Arrays.toString(source));
    }

    private static void reverse(int[] a, int low, int hi) {
        if (low < hi) {
           swap(a, low, hi);
           reverse(a, low + 1, hi - 1);
        }
    }

    private static void swap(int[] source, int a, int b) {
        int temp = source[a];
        source[a] = source[b];
        source[b] = temp;
    }
}

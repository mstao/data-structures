package me.mingshan.algorithm.divide;

/**
 * 利用分治进行数组求和
 *
 * @author mingshan
 */
public class Sum {

    public static void main(String[] args) {
        int[] source = {1, 4, 3, 5};

        System.out.println(sum(source, 0, source.length - 1));
    }


    public static int sum(int[] source, int left, int right) {
        if (left == right) {
            return source[left];
        }

        if (left == right - 1) {
            return source[left] + source[right];
        }
        int mid = (left + right) / 2;
        return sum(source, left, mid) + sum(source, mid + 1, right);
    }
}

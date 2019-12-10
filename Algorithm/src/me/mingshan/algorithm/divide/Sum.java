package me.mingshan.algorithm.divide;

/**
 * 减而治之 和 分而治之
 *
 * 利用分治进行数组求和
 *
 * @author mingshan
 */
public class Sum {

    public static void main(String[] args) {
        int[] source = {1, 4, 3, 5};

        System.out.println(sum(source, 0, source.length - 1));
        System.out.println(sum(source, source.length));
    }

    /**
     * 分而治之, 时间复杂度为 O(n)
     *
     * @param source
     * @param left
     * @param right
     * @return
     */
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

    /**
     * 减而治之
     *
     * @param source
     * @param n
     * @return
     */
    public static int sum(int[] source, int n) {
        return n < 1 ? 0 : sum(source, n -1) + source[n -1];
    }
}

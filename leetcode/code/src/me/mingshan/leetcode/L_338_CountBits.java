package me.mingshan.leetcode;

import java.util.Arrays;

/**
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 *
 * https://leetcode-cn.com/problems/counting-bits/
 *
 * @author hanjuntao
 * @date 2022/2/19
 */
public class L_338_CountBits {

    public static void main(String[] args) {
        int[] ints = countBits(5);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] countBits(int n) {
        if (n < 0) {
            return null;
        }

        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            result[i] = solution(i);
        }
        return result;
    }

    public static int solution(int source) {
        int count = 32;
        int result = 0;

        while (count > 0) {
            // 与1进行与运算，以为1的二进制最后一位是1，其余全是0，可以判断source 的最后一位是什么
            if ((source & 1) == 1) {
                result++;
            }

            // 右移
            source = source >> 1;
            count--;
        }

        return result;
    }
}

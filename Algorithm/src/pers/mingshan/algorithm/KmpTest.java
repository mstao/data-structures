package pers.mingshan.algorithm;

/**
 * KMP 实现
 * 
 * @author mingshan
 *
 */
public class KmpTest {

    public static void main(String[] args) {
        String source = "123456abc789abc";
        String search = "abc";
        System.out.println(kmp(source, search));
        int[] next = kmpNext(search);
        for (int a : next) {
            System.out.println(a);
        }
    }

    public static int kmp(String sourceStr, String searchStr) {
        int[] next = kmpNext(searchStr);
        char[] str = sourceStr.toCharArray();
        char[] ptr = searchStr.toCharArray();
        int slen = str.length;
        int plen = ptr.length;
        int k = 0;
        int i = 0;
        for (i = 0, k = 0; i < slen; i++) {
            // ptr和str不匹配，且k>0（表示ptr和str有部分匹配）
            while (k > 0 && ptr[k] != str[i])
                k = next[k - 1];// 往前回溯
            if (ptr[k] == str[i])
                k = k + 1;
            // 说明k移动到ptr的最末端
            if (k == plen) {
                return i - plen + 1;// 返回相应的位置
            }
        }
        return -1;
    }

    private static int[] kmpNext(String str) {
        char[] s = str.toCharArray();
        int len = s.length;
        int[] next = new int[len];
        int k = 0; // 最大前后缀长度
        int q = 0;
        next[0] = 0;

        // 从第二个字符开始，依次计算每一个字符对应的next值
        for (q = 1, k = 0; q < len - 1; q++) {
            // 递归的求出s[0]···s[q]的最大的相同的前后缀长度k
            while (k > 0 && s[k] != s[q]) {
                k = next[k - 1];
            }
            // 如果相等，那么最大相同前后缀长度加1
            if (s[k] == s[q]) {
                k++;
            }
            next[q] = k;
        }
        return next;
    }
}

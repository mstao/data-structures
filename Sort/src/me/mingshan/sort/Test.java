package me.mingshan.sort;

public class Test {

    public static void main(String[] args) {
        int N = 3;
        int[][] a = new int[N][N];
        int i, j;
        int z = 1;
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                a[i][j] = z;
                z++;
            }
        }

        int[] b = clockwisePrint(a, N, N);

        for (i = 0; i < 9; i++) {
            System.out.println(b[i]);
        }
    }

    public static int[] clockwisePrint(int[][] mat, int n, int m) {
        // 计算数组长度，初始化一维数组
        int[] result = new int[m*n];
        int count = 0, hang = 0, lie = 0;
        int rows = n, cols = m;
        int index = 0;
        if (mat.length < 0) { 
            return null;
        }
        while (rows >= 1 && cols >= 1) {
            if (rows >=1 && cols >= 1) {
                for (; lie < m - index; lie++) {
                    result[count++] = mat[hang][lie];
                }
                hang++;
                lie--;
                rows--;
            }

            if (rows >= 2 && cols >= 1) {
                for(; hang<n-index;hang++) {
                    result[count++]=mat[hang][lie];
                }
                lie--;
                hang--;
                cols--;
            }

            if (rows >= 1 && cols >= 1) {
                for (; lie > index - 1; lie--) {
                    result[count++] = mat[hang][lie];
                }
                hang--;
                lie++;
                rows--;
            }

            if (cols >=1 && rows >= 2) {
                for (; hang > index; hang--) {
                    result[count++] = mat[hang][lie];
                }
                lie++;
                hang++;
                cols--;
            }
            index++;
        }

        return result;
    }
}

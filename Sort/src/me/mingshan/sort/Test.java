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
                System.out.print(a[i][j]);
                z++;
            }
            System.out.print("\n");
        }
    }
}

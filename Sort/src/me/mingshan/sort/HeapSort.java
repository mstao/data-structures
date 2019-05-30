package me.mingshan.sort;

/**
 * 堆排序
 * 
 * @author mingshan
 *
 */
public class HeapSort {

    /**
     * 根据给定节点的下标，找到其左儿子的下标
     * 
     * @param i
     *            给定节点的下标
     * @return 其左儿子的下标
     */
    public static int left(int i) {
        return 2 * i;
    }

    /**
     * 根据给定节点的下标，找到其右儿子的下标
     * 
     * @param i
     *            给定节点的下标
     * @return 其右儿子的下标
     */
    public static int right(int i) {
        return 2 * i + 1;
    }

    /**
     * 根据给定节点的下标，找到其父节点的下标
     * 
     * @param i
     *            给定节点的下标
     * @return 其父节点的下标
     */
    public static int parent(int i) {
        // 为根节点
        if (i == 0)
            return -1;
        return i / 2;
    }

    public static void buildMaxHeap(int[] data, int n) {
        // 从第一个非叶子结点开始调整
        for (int i = n / 2; i >= 1; --i) {
            heapify(data, n, i);
        }
    }

    /**
     * 堆化
     * 
     * @param data
     *            数组
     * @param n
     *            总数量
     * @param i
     *            当前位置
     */
    private static void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            int left = left(i);
            int right = right(i);
            if (left <= n && a[i] < a[left])
                maxPos = left;
            if (right <= n && a[maxPos] < a[right])
                maxPos = right;
            if (maxPos == i)
                break;
            swap(a, i, maxPos);
            i = maxPos;
        }
    }

    /**
     * 交换两个位置的数据
     * 
     * @param data
     *            数组
     * @param index1
     *            位置1
     * @param index2
     *            位置2
     */
    private static void swap(int[] data, int index1, int index2) {
        int tempValue = data[index1];
        data[index1] = data[index2];
        data[index2] = tempValue;
    }

    public static void sort(int[] data, int n) {
        buildMaxHeap(data, n);
        int k = n;
        while (k > 1) {
            swap(data, 1, k);
            --k;
            heapify(data, k, 1);
        }
    }

    public static void main(String[] args) {
        int[] data = { 0, 12, 6, 4, 9, 1, 5, 14, 3 };
        sort(data, 8);
        print(data);
    }

    private static void print(int[] data) {
        for (int i = 1; i < data.length; i++) {
            System.out.print(data[i] + ",");
        }
        System.out.println();
    }
}

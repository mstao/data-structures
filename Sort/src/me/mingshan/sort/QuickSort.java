package me.mingshan.sort;

import java.util.Arrays;

/**
 * 快速排序
 * <p>
 * 利用分治思想
 * <p>
 *
 *   关于快速排序法为什么一定要哨兵j 先出动的原因？
 *
 *   如果选取最左边的数arr[left]作为基准数，那么先从右边开始可保证i,j在相遇时，相遇数是小于基准数的，
 *   交换之后temp所在位置的左边都小于temp。但先从左边开始,相遇数是大于基准数的，无法满足temp左边的数都小于它。
 *   所以进行扫描，要从基准数的对面开始。（注：左右相对来说，也可前后）
 *
 * https://www.jianshu.com/p/2b2f1f79984e
 * https://www.jianshu.com/p/36ef33ed59ba
 *
 * @author mingshan
 */
public class QuickSort {

  public static void main(String[] args) {
    int[] source = {1, 4, 3, 5, 9, 7};
    quickSort(source, 0, source.length - 1);

    System.out.println(Arrays.toString(source));
  }

  public static void quickSort(int[] source, int low, int high) {
    if (low < high) {
      int i = low;
      int j = high;
      // 找到基准值
      int base = source[low];

      // 两者不想遇
      while (i != j) {
        // 哨兵j从右往左走，找到比基准值第一个小的元素
        while (i < j && source[j] >= base) {
          j--;
        }

        // 哨兵i从右往左走，找到比基准值第一个大的元素
        while (i < j && source[i] <= base) {
          i++;
        }

        // 交换哨兵i，j的数据
        if (i < j) {
          swap(source, i, j);
        }
      }

      // 两个哨兵相遇后，交换基准与当前位置的数据
      swap(source, low, i);

      // 当前位置左边的数都比基准小，递归
      quickSort(source, low, i - 1);
      // 当前位置右边的数都比基准大，递归
      quickSort(source, i + 1, high);
    }
  }

  /**
   * 交换a,b两个位置的元素
   *
   * @param a 位置a
   * @param b 位置b
   */
  public static void swap(int[] source, int a, int b) {
    int temp = source[a];
    source[a] = source[b];
    source[b] = temp;
  }
}

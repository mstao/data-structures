package me.mingshan.leetcode;

/**
 * 剑指 Offer 11. 旋转数组的最小数字
 *
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Walker Han
 * @date 2021/6/7 10:42
 */
public class L_剑指Offer11_MinArray {

  public static void main(String[] args) {
    int[] num1 = {4,5,1,2,3};
    System.out.println(minArray(num1));

    int[] num3 = {1,2,3,4,5};
    System.out.println(minArray(num3));

    int[] num4 = {3,4,5,1,2};
    System.out.println(minArray(num4));

    int[] num5 = {2,2,2,0,1};
    System.out.println(minArray(num5));

    int[] num6 = {3,3,3,1};
    System.out.println(minArray(num6));

    int[] num7 = {2,2,0,0,1,1};
    System.out.println(minArray(num7));

    int[] num8 = {1,1,2,2,0,0};
    System.out.println(minArray(num8));

    int[] num9 = {2,2,0,0,0,1,1,1};
    System.out.println(minArray(num9));

    int[] num10 = {10,10,10,10,1};
    System.out.println(minArray(num10));


    int[] num11 = {0,1,2,2};
    System.out.println(minArray(num11));

    int[] num12 = { 2,2,2,0,1};
    System.out.println(minArray(num12));
  }

  /**
   * 直接for循环
   *
   * @param numbers
   * @return
   */
  public static int minArray2(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
      throw new IllegalArgumentException("numbers is empty");
    }

    if (numbers.length == 1) {
      return numbers[0];
    }

    int lastVal = numbers[0];

    for (int i = 1; i < numbers.length; i++) {
      if (numbers[i] < lastVal) {
        return numbers[i];
      }

      lastVal = numbers[i];
    }

    return numbers[0];
  }

  /**
   * 二分
   *
   * @param numbers
   * @return
   */
  public static int minArray(int[] numbers) {
    if (numbers == null || numbers.length == 0) {
      throw new IllegalArgumentException("numbers is empty");
    }

    if (numbers.length == 1) {
      return numbers[0];
    }

    Integer min = findMin(numbers, 0, numbers.length - 1);
    if (min != null) {
      return min;
    }

    return numbers[0];
  }

  private static Integer findMin(int[] numbers, int low, int high) {
    int mid = (low + high) / 2;

    if (low > high) {
      return null;
    }

    if (low == mid || high == mid) {
      if (numbers[low] > numbers[high]) {
        return numbers[high];
      }
    }

    if ((mid - 1) >= 0 && (mid + 1) <= high) {
      // 左小（等）右小
      if (numbers[mid-1] <= numbers[mid] && numbers[mid+1] < numbers[mid]) {
        return numbers[mid+1];
      }

      // 左大右大（等）
      if (numbers[mid-1] > numbers[mid] && numbers[mid+1] >= numbers[mid]) {
        return numbers[mid];
      }

      Integer min1 = findMin(numbers, mid + 1, high);
      if (min1 != null) {
        return min1;
      }

      return findMin(numbers, low, mid - 1);
    }

    return null;
  }
}

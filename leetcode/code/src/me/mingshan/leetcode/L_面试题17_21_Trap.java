package me.mingshan.leetcode;

public class L_面试题17_21_Trap {

  public static void main(String[] args) {
    int[] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
    System.out.println(trap(arr));

    int[] arr2 = {2, 0, 2};
    System.out.println(trap(arr2));
  }

  public static int trap(int[] height) {
    if (height == null || height.length == 0) {
      return 0;
    }

    int len = height.length;
    // 最高的值
    int high = 0;
    // 黑色块的数目
    int blockNum = 0;

    // 总体积
    int totalVolume = 0;

    for (int value : height) {
      if (value > high) {
        high = value;
      }

      blockNum = blockNum + value;
    }

    for (int j = 1; j <= high; j++) {
      int firstIndex = -1;
      int lastIndex = -1;

      // 高度为j，第一次和最后一次出现的位置，计算面积
      for (int i = 0; i < len; i++) {
        if (height[i] >= j) {

          if (firstIndex == -1) {
            firstIndex = i;
          }

          lastIndex = i;
        }
      }

      if (firstIndex != -1) {
        totalVolume = totalVolume + (lastIndex - firstIndex + 1);
      }
    }

    return totalVolume - blockNum;
  }
}

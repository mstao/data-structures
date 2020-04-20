package me.mingshan.leetcode;

/**
 * https://leetcode-cn.com/problems/container-with-most-water/
 *
 * @author mingshan
 */
public class L_11_MaxArea {
  public static void main(String[] args) {
    int[] arr = {1,8,6,2,5,4,8,3,7};
    System.out.println(maxArea(arr));
  }

  public static int maxArea(int[] height) {
    if (height == null || height.length == 0) {
      return 0;
    }

    int result = 0;

    for (int i = 0; i < height.length; i++) {
      for (int j = i + 1; j < height.length; j++) {
        int area = Math.min(height[i], height[j]) * Math.abs(i - j);
        if (area > result) {
          result = area;
        }
      }
    }

    return result;
  }
}

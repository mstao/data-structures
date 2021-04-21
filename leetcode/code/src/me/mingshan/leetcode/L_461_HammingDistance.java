package me.mingshan.leetcode;

/**
 * 461. 汉明距离
 *
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 * 注意：
 * 0 ≤ x, y < 231.
 *
 * 示例:
 *
 * 输入: x = 1, y = 4
 *
 * 输出: 2
 *
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hamming-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_461_HammingDistance {

  public static void main(String[] args) {
    //  0200
    // >>  1
    //  0020
    int x = 4;

    System.out.println(hammingDistance(1, 4));
  }

  public static int hammingDistance(int x, int y) {
    int count = 32;
    int result = 0;

    while (count > 0) {
      //
      boolean xIsOne = (x & 1) == 1;

      boolean yIsOne = (y & 1) == 1;

      if ((xIsOne && !yIsOne) || (!xIsOne && yIsOne)) {
        result++;
      }

      x = x >> 1;
      y = y >> 1;
      count--;
    }

    return result;
  }
}

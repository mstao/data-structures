package me.mingshan.algorithm.dynamic;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxProfit2 {

  public static void main(String[] args) {
//    int[] a1 = {7,1,5,3,6,4};
//    System.out.println(maxProfit(a1));


    int[] a2 = {1,2,3,4,5,6};
    System.out.println(maxProfit(a2));
  }

  public static int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }

    int result = 0;

    int i = 0;

    while (i < (prices.length - 1)) {
      int currentPrice = prices[i];

      for (int j = i + 1; j < prices.length; j++) {
        int nextValue = prices[j];
        int beforeValue = prices[j-1];

        // 找到上升的，继续向前探测
        if (nextValue > beforeValue) {
          // 直接探测到最后一个，直接计算即可

          if (j == prices.length - 1) {
            result += nextValue - currentPrice;

            i = j;
            break;
          }
        } else {
          result += beforeValue - currentPrice;

          i = j;
          break;
        }
      }
    }

    return result;
  }
}

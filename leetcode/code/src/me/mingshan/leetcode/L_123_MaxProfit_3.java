package me.mingshan.leetcode;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * 
 * 未实现
 */
public class L_123_MaxProfit_3 {

  public static void main(String[] args) {
    int[] prices = {3,3,5,0,0,3,1,4};
//    System.out.println(maxProfit(prices)); // 6
//
//    int[] prices2 = {1,2,3,4,5};
//    System.out.println(maxProfit(prices2)); // 4
//
//    int[] prices3 = {1,4,2};
//    System.out.println(maxProfit(prices3)); // 3

    int[] prices4 = {2,1,4};
//    System.out.println(maxProfit(prices4)); // 3

    int[] prices5 = {1,2,4,2,5,7,2,4,9,0};
    System.out.println(maxProfit(prices5)); // 13
  }

  public static int maxProfit(int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }

    // 最大区间的差值
    int maxDifference = 0;
    int maxDifferenceBegin = 0;
    // 第二大区间的差值
    int secondMaxDifference = 0;

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
            int difference = nextValue - currentPrice;

            if (difference > maxDifference) {
              int oldMax = maxDifference;

              maxDifferenceBegin = i;
              maxDifference = difference;
              secondMaxDifference = oldMax;
            }

            if (i != maxDifferenceBegin && difference <= maxDifference && difference > secondMaxDifference) {
              secondMaxDifference = difference;
            }

            i = j;
            break;
          }
        } else {
          // 有下降，需要计算上个上升的差值
          int difference = beforeValue - currentPrice;

          if (difference > maxDifference) {
            int oldMax = maxDifference;

            maxDifferenceBegin = i;
            maxDifference = difference;
            secondMaxDifference = oldMax;
          }

          if (i != maxDifferenceBegin && difference <= maxDifference && difference > secondMaxDifference) {
            secondMaxDifference = difference;
          }

          i = j;
          break;
        }
      }
    }

    return maxDifference + secondMaxDifference;
  }
}

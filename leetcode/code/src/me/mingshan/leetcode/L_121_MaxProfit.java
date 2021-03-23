package me.mingshan.leetcode;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_121_MaxProfit {
  public static void main(String[] args) {

    int[] prices = {2,3,4,1,2};
    System.out.println(maxProfit2(prices));

    int[] prices2 = {7,1,5,3,6,4};
    System.out.println(maxProfit2(prices2));
  }

  public static int maxProfit2(int[] prices) {
    if (prices == null || prices.length == 0) {
      return 0;
    }

    int result = 0;

    int i = 0;
    while (i < (prices.length - 1)) {
      int currentPrice = prices[i];

      int nextP = i + 1;

      int maxValue = prices[nextP];

      int endP = nextP;
      // 向前探测数据，找出一直上升的块
      for (int j = nextP; j < prices.length; j++) {
        int currentPrice2 = prices[j];

        // 只要下一个值比当前值大，那么就一直探测，找到最大值进行计算
        if (currentPrice2 > currentPrice) {
          if (currentPrice2 > maxValue) {
            maxValue = currentPrice2;
          }

          if ((maxValue - currentPrice) > result) {
            result = maxValue - currentPrice;
          }

          endP = j;
        } else {
          // 出现了值比当前值还小，那么就需要计算前面的值了
          if ((maxValue - currentPrice) > result) {
            result = maxValue - currentPrice;
          }
          endP = j;
          break;
        }
      }

      i = endP;
    }

    return result;
  }

  //  public static int maxProfit(int[] prices) {
//    if (prices == null || prices.length == 0) {
//      return 0;
//    }
//
//    int result = 0;
//
//    int i = 0;
//    while (i < (prices.length - 1)) {
//      int currentPrice = prices[i];
//
//      int upP = i;
//      int nextP = i + 1;
//
//      // 向前探测数据，找出一直上升的块
//      for (int j = nextP; j < prices.length; j++) {
//        int currentPrice2 = prices[j-1];
//        int currentPrice3 = prices[j];
//
//        // 遇到有下跌的，直接跳出循环，不要出手
//        if (currentPrice3 < currentPrice2) {
//          i++;
//          break;
//        } else {
//          // 持续上升，指针一直上升移动
//          i++;
//          upP = j;
//        }
//      }
//
//      int upPValue = prices[upP];
//
//      if ((upPValue - currentPrice) > result) {
//        result = upPValue - currentPrice;
//      }
//    }
//
//    return result;
//  }
}

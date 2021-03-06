package me.mingshan.algorithm.dynamic;

/**
 * 现有 2元，5元，七元三种硬币，假设硬币都足够多，先求解：最少用多少枚上述硬币拼出27块钱
 *
 * https://leetcode-cn.com/problems/coin-change/
 */
public class MinCoin {

  public static void main(String[] args) {
    System.out.println(solution1(27));
    int[] coins = {2, 5, 7};
    System.out.println(solution3(coins, 27));
  }

  public static int solution1(int x) {
    if (x == 0) {
      return 0;
    }

    int res = x + 1;

    if (x >= 2) {
      res = Math.min(solution1(x - 2) + 1, res);
    }

    if (x >= 5) {
      res = Math.min(solution1(x - 5) + 1, res);
    }

    if (x >= 7) {
      res = Math.min(solution1(x - 7) + 1, res);
    }

    return res;
  }


  public static int solution3(int[] coins, int amount) {
    // f(x) 代表x面额最少用多少枚硬币拼出
    int[] f = new int[amount + 1];
    // 初始化
    f[0] = 0;

    // 数值从小到大进行计算
    // f[x] = min{ f[x - coin1] + 1 , f[x - coin2] + 1, ....}
    // 1,2..27
    for (int i = 1; i <= amount; i++) {
      // 先设置f(i) 为最大值，好进行比较更新
      f[i] = Integer.MAX_VALUE;

      for (int j = 0; j < coins.length; j++) {
        int coin = coins[j];
        // 当前待拼的面额必须大于等于硬币的面额
        // 并且f[x - coin1]不为Integer.MAX_VALUE，
        // 注意不能直接用 f[i] > (f[i - coin] + 1) 来判断，因为 f[i - coin]为Integer.MAX_VALUE， + 1 就是负数了，
        // 此时的f[i]计算结果为负数
        if (i >= coin && f[i - coin] != Integer.MAX_VALUE) {
          f[i] = Math.min(f[i - coin] + 1, f[i]);
        }
      }
    }

    if (f[amount] == Integer.MAX_VALUE) {
      return -1;
    }

    return f[amount];
  }

  /**
   * 运用动态规划解决
   *
   * @param coins  硬币的种类
   * @param amount 要拼出的钱数
   * @return 最少硬币数
   */
  public static int solution2(int[] coins, int amount) {
    // f[x]  代表多少枚硬币拼出x
    int[] f = new int[amount + 1];
    f[0] = 0;

    // 数值从小到大进行计算
    // f[x] = min{ f[x - coin1] + 1 , f[x - coin2] + 1, ....}
    // 1,2..27
    for (int i = 1; i <= amount; i++) {
      // 先假设f[i] 不能被拼出
      f[i] = Integer.MAX_VALUE;

      // 下面根据上面的计算公式计算出f[i]的最小值
      for (int j = 0; j < coins.length; j++) {
        // 当前的钱数 要大于 硬币的种类对应钱数；并且f[x - coin1]不为Integer.MAX_VALUE
        if (i >= coins[j] && f[i - coins[j]] != Integer.MAX_VALUE) {
          f[i] = Math.min(f[i - coins[j]] + 1, f[i]);
        }
      }
    }

    if (f[amount] == Integer.MAX_VALUE) {
      return -1;
    }

    return f[amount];
  }
}

package me.mingshan.algorithm.dynamic;

/**
 * 现有 2元，5元，七元三种硬币，假设硬币都足够多，先求解：最少用多少枚上述硬币拼出27块钱
 */
public class MinCoin {

  public static void main(String[] args) {
    System.out.println(solution1(27));
    int[] coins = {2, 5, 7};
    System.out.println(solution2(coins, 27));
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

    // 1,2..27
    for (int i = 1; i <= amount; i++) {
      f[i] = Integer.MAX_VALUE;

      for (int j = 0; j < coins.length; j++) {
        // 当前的硬币值小于要拼出的数值
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

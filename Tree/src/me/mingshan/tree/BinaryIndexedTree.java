package me.mingshan.tree;

import java.util.Arrays;

/**
 * 树状数组
 * https://blog.csdn.net/BrilliantEagle/article/details/51868892
 * https://blog.csdn.net/int64ago/article/details/7429868
 * https://www.topcoder.com/community/competitive-programming/tutorials/binary-indexed-trees/
 *
 */
public class BinaryIndexedTree {
  private int length;
  private int[] tree;

  public BinaryIndexedTree(int length) {
    if (length <= 0)
      throw new IllegalArgumentException("The length [" + length + "] must greater 0.");

    this.length = length;
    tree = new int[length + 1];
  }

  /**
   * 更新
   *
   * @param index 指定位置
   * @param value 值
   */
  public void put(int index, int value) {
    checkPosition(index);

    while (index <= length) {
      tree[index] += value;
      index += lowBit(index);
    }
  }

  /**
   * 计算从1到{@code index}之间的和
   *
   * @param index 指定位置
   * @return 和
   */
  public int sum(int index) {
    checkPosition(index);

    int sum = 0;
    while (index > 0) {
      sum += tree[index];
      index -= lowBit(index);
    }

    return sum;
  }

  /**
   * 算从{@code start}到{@code index}之间的和
   *
   * @param start 起始位置
   * @param end 终止位置
   * @return 值
   */
  public int sum(int start, int end) {
    return sum(end) - sum(start - 1);
  }

  /**
   * 获取
   *
   * @param index 指定位置
   * @return 值
   */
  public int get(int index) {
    checkPosition(index);

    int sum = tree[index];
    int z = index - lowBit(index);
    index--;
    while (index != z) {
      sum -= tree[index];
      index -= lowBit(index);
    }
    return sum;
  }

  /**
   * (二进制)保留最低位的1及其后面的0，高位的1全部变为0，
   * 即得到该数的二进制从右往左第一个非0位所表示的10进制数。
   * 例如：
   * <pre>
   *  原值 0000 0110
   *  取反 1111 1001
   *  +1  1111 1010
   *  &   0000 0111
   *  结果 0000 0010
   * </pre>
   *
   * @param k 待处理的十进制数
   * @return 处理后的十进制数
   */
  private static int lowBit(int k) {
    return k & -k;
  }

  /**
   * 检测指定位置是否越界
   *
   * @param index 指定位置
   */
  private void checkPosition(int index) {
    if (index <= 0) {
      throw new IllegalArgumentException("The index [" + index + "] must greater 0.");
    }
    if (index > this.length) {
      throw new IllegalArgumentException("The index [" + index + "] is out of range.");
    }
  }

  @Override
  public String toString() {
    return Arrays.toString(tree);
  }
}

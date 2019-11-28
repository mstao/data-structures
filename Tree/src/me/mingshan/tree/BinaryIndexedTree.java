package me.mingshan.tree;

import java.util.Arrays;

/**
 * 树状数组
 * https://blog.csdn.net/BrilliantEagle/article/details/51868892
 * https://blog.csdn.net/int64ago/article/details/7429868
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
   *
   * @param k
   * @return
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

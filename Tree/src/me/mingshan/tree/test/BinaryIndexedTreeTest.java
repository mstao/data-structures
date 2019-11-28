package me.mingshan.tree.test;

import me.mingshan.tree.BinaryIndexedTree;
import org.junit.Test;

import java.util.Random;

public class BinaryIndexedTreeTest {

  @Test
  public void test1() {
    int length = 15;
    BinaryIndexedTree bTree = new BinaryIndexedTree(length);
    Random random = new Random();
    //随机放满数据
    for (int i = 1; i <= length; i++) {
      bTree.put(i, random.nextInt(10));
    }

    // 打印数组元素
    System.out.println("打印数组元素");
    System.out.println(bTree);

    // 取出每一位
    System.out.println("取出每一位");
    for (int i = 1; i <= length; i++) {
      int value = bTree.get(i);
      System.out.printf("%3d", value);
      System.out.print("  ");
    }
    System.out.println();

    System.out.println("计算0~i的和");
    //计算0~i的和
    for (int i = 1; i <= length; i++) {
      int sum = bTree.sum(i);

      System.out.printf("%3d", sum);
      System.out.print("  ");
    }
    System.out.println();

    //计算start~end的和
    System.out.println("计算start~end的和");
    System.out.printf("%3d", bTree.sum(2, 4));

  }
}

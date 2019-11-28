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

    // language="HTML"
    var a = "<html><header>" +
      "</header></html>";

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

  @Test
  public void test2() {
    int k1 = 6; // 0000 0110
    // 取反 11111001
    // + 1  11111010
    //   &  00000111
    // 结果 00000010

    System.out.println(lowBit(k1)); // 0000 0010

    int k2 = 10; // 0000 1010
    System.out.println(lowBit(k2)); // 0000 0010

    int k3 = 12; // 0000 1100
    System.out.println(lowBit(k3)); // 0000 0100

    int k4 = 14; // 0000 1110;
    System.out.println(lowBit(k4));

    int k5 = 15; // 0000 1111;
    System.out.println(lowBit(k5)); // 0000 0001

  }


  private static int lowBit(int k) {
    return k & -k;
  }
}

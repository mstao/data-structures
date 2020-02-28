package me.mingshan.linkedlist;

import org.junit.Assert;

/**
 * 链表测试
 * @author mingshan
 *
 */
public class Test {


    /**
     * 测试单链表 add，get
     */
    @org.junit.Test
    public void test1() {
        LinkedList<String> list1 = new SingleLinkedList<String>("aa");
        list1.add("bb");
        list1.add("zz");
        list1.add("cc");
        list1.add("zz");

        list1.add(2, "xx");
        System.out.println(list1);
        Assert.assertEquals("zz", list1.get(3));
    }

    /**
     * 测试单链表 remove
     */
    @org.junit.Test
    public void test2() {
        LinkedList<String> list1 = new SingleLinkedList<String>("aa");
        list1.add("bb");
        list1.add("zz");
        list1.add("cc");
        list1.add("zz");

        list1.remove(1);
        Assert.assertEquals("zz", list1.get(3));
        list1.remove(2);
        System.out.println(list1);
    }

    /**
     * 测试单链表 removeAll
     */
    @org.junit.Test
    public void test3() {
        LinkedList<String> list1 = new SingleLinkedList<String>("aa");
        list1.add("bb");
        list1.add("zz");
        list1.add("cc");
        list1.add("zz");

        list1.removeAll("zz");
        System.out.println(list1.toString());
    }

    /**
     * 测试单链表 reverse
     */
    @org.junit.Test
    public void test4() {
        LinkedList<String> list1 = new SingleLinkedList<String>("aa");
        list1.add("bb");
        list1.add("zz");
        list1.add("cc");
        list1.add("zz");

        System.out.println(list1.toString());

        // 反转下链表
        list1.reverse();
        System.out.println(list1.toString());
    }

    @org.junit.Test
    public void test5() {
        LinkedList<String> list3 = new DoubleLinkedList<>();
        list3.add("a1");
        list3.add("a2");
        list3.add("a3");
        list3.add("a4");
        list3.add("a3");
        System.out.println(list3.toString());
    }

    @org.junit.Test
    public void test6() {
        LinkedList<String> list3 = new DoubleLinkedList<>();
        list3.add("a1");
        list3.add("a2");
        list3.add("a3");
        list3.add("a4");
        list3.add("a3");
        list3.remove(2);
        System.out.println(list3.toString());
    }

    @org.junit.Test
    public void test7() {
        LinkedList<String> list3 = new DoubleLinkedList<>();
        list3.add("a1");
        list3.add("a2");
        list3.add("a3");
        list3.add("a4");
        list3.add("a3");
        list3.removeAll("a3");
        System.out.println(list3.toString());
    }

    @org.junit.Test
    public void test8() {
        LinkedList<String> list3 = new DoubleLinkedList<>();
        list3.add("a1");
        list3.add("a2");
        list3.add("a3");
        list3.add("a4");
        list3.add("a3");

        list3.reverse();
        System.out.println(list3.toString());
    }
}

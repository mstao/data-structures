package me.mingshan.linkedlist;
/**
 * 链表测试
 * @author mingshan
 *
 */
public class Test {

    public static void main(String[] args) {

        // --------------single
        LinkedList<String> list1 = new SingleLinkedList<String>("aa");
        list1.add("bb");
        list1.add("zz");
        list1.add(2, "cc");
        //System.out.println(list1.get(3));
        //list1.set(3, "ff");
        //list1.remove(3);
        System.out.println(list1.toString());

        // 反转下链表
        list1.reverse();
        System.out.println(list1.toString());
        

        java.util.LinkedList<String> list2 = new java.util.LinkedList<>(); 
        list2.add("aa");
        list2.add(0, "333");
        System.out.println(list2.toString());
        
        
        // --------------double
        LinkedList<String> list3 = new DoubleLinkedList<>();
        list3.add("a1");
        list3.add("a2");
        list3.add("a3");
        //list3.remove(0);
        list3.reverse();
        System.out.println(list3.toString());
       
    }
}

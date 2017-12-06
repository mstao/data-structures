package pers.mingshan.linkedlist;

/**
 * 链表测试
 * @author mingshan
 *
 */
public class Test {

    public static void main(String[] args) {

       LinkedList<String> list1 = new SingleLinkedList<String>("aa");
       list1.add("bb");
       list1.add(2, "cc");
       //System.out.println(list1.get(3));
       //list1.set(3, "ff");
       //list1.remove(3);
       System.out.println(list1.toString());

    }
}

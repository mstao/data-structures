package me.mingshan.stack;

public class Test {

    public static void main(String[] args) {
        TreiberStack2<Integer> s = new TreiberStack2<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        s.push(6);
        s.push(7);
        s.push(8);
        s.push(9);
        System.out.println(s.size());
        System.out.println(s.pop());
        System.out.println(s.toString());
        System.out.println(s.peek());
        System.out.println(s.toString());
    }
}

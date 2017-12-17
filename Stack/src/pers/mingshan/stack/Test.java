package pers.mingshan.stack;

public class Test {

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(null);
        s.push(5);
        s.push(6);
        s.push(7);
        s.push(8);
        s.push(9);
        System.out.println(s.pop());
        System.out.println(s.peek());
        System.out.println(s.toString());
        System.out.println(s.search(null));
    }
}

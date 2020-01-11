package me.mingshan.stack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        int count = 20;
        ExecutorService executorService = Executors.newFixedThreadPool(count);

        for (int i = 0; i < count; i++) {
            executorService.submit(() -> {
                System.out.println(s.pop());
                System.out.println(s.toString());
                System.out.println(s.peek());
                System.out.println(s.toString());
                System.out.println("--------");
            });
        }

        executorService.shutdown();
    }
}

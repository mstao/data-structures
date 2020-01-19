package me.mingshan.stack;

import org.junit.Assert;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

  @org.junit.Test
  public void arrayStackTest() {
    Stack<Integer> s = new ArrayStack<>();
    s.push(1);
    s.push(2);
    s.push(3);
    s.push(4);
    s.push(5);
    s.push(6);
    s.push(7);
    s.push(8);
    s.push(9);

    Assert.assertEquals(java.util.Optional.of(9).get(), s.peek());
    Assert.assertEquals(java.util.Optional.of(9).get(), s.pop());
    Assert.assertEquals(java.util.Optional.of(8).get(), s.pop());
    Assert.assertEquals(java.util.Optional.of(7).get(), java.util.Optional.of(s.size()).get());
  }

  @org.junit.Test
  public void treiberStackTest() {
    Stack<Integer> s = new TreiberStack2<>();
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
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + " - " + s.pop());
        System.out.println(threadName + " - " + s.toString());
        System.out.println(threadName + " - " + s.peek());
        System.out.println(threadName + " - " + s.toString());
        System.out.println(threadName + " - " + "--------");
      });
    }

    executorService.shutdown();
  }

}

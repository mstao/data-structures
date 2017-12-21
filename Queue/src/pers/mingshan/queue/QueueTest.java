package pers.mingshan.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueTest {

    public static void main(String[] args) {
        Queue<String> queue = new ArrayQueue<String>();

        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("d");
        queue.offer("e");
        System.out.println(queue.peek());// 获取头结点，但并不移除
        System.out.println(queue.poll());// 获取元素并且在队列中移除，如果队列为空返回null
        
        System.out.println(queue.toString());
        
        //---------------------
        
        Queue<String> queue2 = new LinkQueue<String>("aaaa");
        //添加两个元素  
        queue2.offer("bbbb");
        queue2.offer("cccc");
        System.out.println(queue2);
        //删除一个元素后  
        queue2.poll();
        System.out.println("删除一个元素后的队列：" + queue2);
        //再次添加一个元素  
        queue2.offer("dddd");
        System.out.println("再次添加元素后的队列：" + queue2);
        //删除一个元素后，队列可以再多加一个元素  
        queue2.poll();
        //再次加入一个元素  
        queue2.offer("eeee");
        System.out.println(queue2);

        BlockingQueue<String> s = new ArrayBlockingQueue<>(10);
    }
}

package pers.mingshan.queue;

/**
 * 对 {@link Queue1} 的优化，将队列基本结构抽象成静态内部类Queue，
 * 外部直接操作静态内部即可
 * 
 * @author mingshan
 *
 */
public class Queue2 {
    static class Queue {
        private int[] data;
        private int head;
        private int tail;

        //无参数的构造器  
        public Queue() {
        }

        //初始化全部属性的构造器  
        public Queue(int[] data, int head, int tail) {
          this.data = data;
          this.head = head;
          this.tail = tail;
        }
    }

    public static void main(String[] args) {
        // 初始化
        int[] q = new int[102];
        q[0] = 0;
        q[1] = 6;
        q[2] = 3;
        q[3] = 1;
        q[4] = 7;
        q[5] = 5;
        q[6] = 8;
        q[7] = 9;
        q[8] = 2;
        q[9] = 4;
        int head = 1;
        int tail = 10;
        Queue queue = new Queue(q, head, tail);

        while (queue.head < queue.tail) {
            System.out.println("首先移除队首的元素" + queue.data[queue.head] 
                    + " ~ head = " + queue.head + " ~ tail = " + queue.tail);
            // 队首元素出队
            queue.head++;
            // 然后将队列的第二个元素移到队尾
            queue.data[queue.tail] = queue.data[queue.head];
            // 此时再将tail后移一位
            queue.tail++;
            // 再将队首出队
            queue.head++;
        }
    }
}

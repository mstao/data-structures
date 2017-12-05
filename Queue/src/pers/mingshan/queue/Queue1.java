package pers.mingshan.queue;

/**
 * 现在有一列数 ： 6 3 1 7 5 8 9 2 4
 * 现在操作如下：
 * 现将第一个数删除，然后将第二个数放在末尾，然后再将第三个数删除，将第四个数放在末尾，....直到剩下最
 * 后一个数，将最后一个数也删除，按照删除顺序打印删除的数
 * 初始时 head指向 6， tail指向队列末尾的下一位
 *  head              tail
 *   |                 |
 * 0 6 3 1 7 5 8 9 2 4  
 * @author mingshan
 *
 */
public class Queue1 {

    public static void main(String[] args) {
        // 出队入队过程
        // 左边为出队的数，中间为还在队列的数，右边为进行while判断时head 和 tail的值
        /*
         *    6 | 1 7 5 8 9 2 4 3  {head: 1, tail: 10}
         *    1 | 5 8 9 2 4 3 7    {head: 3, tail: 11}
         *    5 | 9 2 4 3 7 8      {head: 5, tail: 12}
         *    9 | 4 3 7 8 2        {head: 7, tail: 13}
         *    4 | 7 8 2 3          {head: 9, tail: 14}
         *    7 | 2 3 8            {head: 11, tail: 15}
         *    2 | 8 3              {head: 13, tail: 16}
         *    8 | 3                {head: 15, tail: 17}
         *    3 |                  {head: 17, tail: 18}
         *                         {head: 19, tail: 19}
         */

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
        while (head < tail) {
            System.out.println("首先移除队首的元素" + q[head]);
            // 队首元素出队
            head++;
            // 然后将队列的第二个元素移到队尾
            q[tail] = q[head];
            // 此时再将tail后移一位
            tail++;
            // 再将队首出队
            head++;
        }
    }
}

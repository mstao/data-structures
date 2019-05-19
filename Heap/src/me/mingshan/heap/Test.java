package me.mingshan.heap;

public class Test {

    public static void main(String[] args) {
        Integer[] data = {12, 6, 4, 9, 1, 5, 14, 3};
        //Heap<Integer> heap = new MaxHeap<>( data,20);
        Heap<Integer> heap = new MaxHeap<>(20);
        //Heap<Integer> heap = new MinHeap<>(20);
        System.out.printf("== 依次添加: ");
        for(int i = 0; i < data.length; i++) {
            System.out.printf("%d ", data[i]);
            heap.add(data[i]);
        }
        System.out.printf("\n== 最 大 堆: %s", heap);

//        heap.add(20);
//        System.out.printf("\n== 添加元素: %d", 20);
//        System.out.printf("\n== 最 大 堆: %s", heap);

        System.out.printf("\n== 删除元素: %d", heap.remove());
        System.out.printf("\n== 最 大 堆: %s", heap);
        System.out.printf("\n");
    }
}

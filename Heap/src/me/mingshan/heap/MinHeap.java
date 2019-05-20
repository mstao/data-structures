package me.mingshan.heap;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * 最小堆
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class MinHeap<T extends Comparable<T>> extends Heap<T> {

    private T[] data; // 数据存储
    private int count; // 堆目前所含数据量大小
    private int capacity; // 堆容量大小

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        this.data = (T[]) new Comparable[capacity + 1];
    }

    public MinHeap(T[] data, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must not be 0");
        }
        if (capacity <= data.length) {
            throw new IllegalArgumentException("capacity must greater than the length of data");
        }
        this.capacity = capacity;
        this.count = data.length;
        buildHeap(data);
    }

    private void buildHeap(T[] array) {
        data = (T[]) new Comparable[capacity];
        this.count =  array.length;
        System.arraycopy(array, 0, data, 1, count);
        for (int k = count; k > 0 ; k--) {
            heapUp(k);
        }
    }

    private void checkPosition(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("Index must be greater than 0");
        }

        if (index >= capacity) {
            throw new IllegalArgumentException("Index is out of range");
        }
    }

    /**
     * 上浮
     */
    private void heapUp(int index) {
        checkPosition(index);
        int nodeIndex = index;
        T value = this.data[nodeIndex];
        if (value == null)
            return;

        while (nodeIndex > 0 && parent(nodeIndex) >0
                && data[nodeIndex].compareTo(data[parent(nodeIndex)]) < 0) {

            swap(nodeIndex, parent(nodeIndex));
            nodeIndex = parent(nodeIndex);
        }
    }

    /**
     * 下沉
     *
     * @param n 总数量
     * @param i 当前节点位置
     */
    private void heapDown(int n, int i) {
        while (true) {
            int maxPos = i;
            int left = left(i);
            int right = right(i);
            if (left <= n && this.data[i].compareTo(this.data[left]) > 0)
                maxPos = left;
            if (right <= n && this.data[maxPos].compareTo(this.data[right]) > 0)
                maxPos = right;
            if (maxPos == i)
                break;
            swap(i, maxPos);
            i = maxPos;
        }
    }

    /**
     * 交换两个位置的数据
     *
     * @param index1
     * @param index2
     */
    private void swap(int index1, int index2) {
        checkPosition(index1);
        checkPosition(index2);

        T tempValue = this.data[index1];
        this.data[index1] = this.data[index2];
        this.data[index2] = tempValue;
    }

    @Override
    public void add(T value) {
        Objects.requireNonNull(value, "value must not be null");
        if (count >= capacity - 1) return;

        ++count;
        this.data[count] = value;
        heapUp(count);
    }

    @Override
    public T get(int index) {
        checkPosition(index);
        return this.data[index];
    }

    @Override
    public T remove() {
        if (count == 0) return null;
        T value = this.data[1];
        this.data[1] = this.data[this.count];
        this.data[this.count] = null;
        --this.count;

        heapDown(count, 1);
        return value;
    }

    @Override
    public void clear() {
        this.data = (T[]) new Comparable[capacity + 1];
        this.count = 0;
    }

    @Override
    public boolean contains(T value) {
        for (int i = 1; i <= this.count; i++) {
            if (this.data[i].compareTo(value) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public Collection<T> toCollection() {
        return Arrays.asList(this.data);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= this.count; i++)
            sb.append(data[i]).append(" ");

        return sb.toString();
    }

}

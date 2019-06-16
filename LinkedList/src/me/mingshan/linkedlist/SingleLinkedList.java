package me.mingshan.linkedlist;

/**
 * 单链表
 * -------------------------------------------------------------------
 *
 *             _______
 *      head  /       ↘
 * ---------------    ---------------
 * | data | next |    | data | next |     >>>
 * ---------------    ---------------
 * -------------------------------------------------------------------
 * @author mingshan
 *
 * @param <E>
 */
public class SingleLinkedList<E> implements LinkedList<E> {
    private Node head;
    private int size;

    private class Node {
        E item;
        Node next;

        public Node(E e) {
            this.item = e;
        }
    }

    public SingleLinkedList(E e) {
        Node newNode = new Node(e);
        head = newNode;
        size++;
    }

    @Override
    public E get(int index) {
        checkPositionIndex(index);
        int count = 1;
        Node temp = head;
        while (temp != null) {
            if (count++ == index) {
                return temp.item;
            }
            temp = temp.next;
        }

        return null;
    }

    @Override
    public boolean add(E data) {
        if (data == null)
            throw new NullPointerException();
        if (head == null) {
            Node newNode = new Node(data);
            head = newNode;
            size++;
            return true;
        }

        Node temp = head;
        // 从头结点向后遍历，获取链表最后一个节点
        while (temp.next != null) {
            // temp 始终指向下一个节点
            temp = temp.next;
        }

        // 根据当前元素构造新节点
        Node newNode = new Node(data);
        // 将最后一节点的next指向新节点
        temp.next = newNode;
        // 计数加一
        size++;
        return true;
    }

    /**
     * 根据索引插入元素
     * @param e 要插入的元素
     * @param index 传入的索引值， 从1开始
     */
    @Override
    public void add(int index, E data) {
        if (data == null)
            throw new NullPointerException();
        checkPositionIndex(index);

        int count = 1;
        Node temp = head;
        // 从头结点向后遍历
        while (temp.next != null) {
            // 1       2 
            // temp  temp.next
            // 假设现在index为2 那么原先在2位置上的节点需要向后移动一个
            // 1           2              3
            // temp    temp.next(e)     temp.next.next
            // 判断是否到了传入的索引

            // 如果索引为1，那么将当前节点置为头结点
            if (index == 1) {
                Node newNode = new Node(data);
                head = newNode;
                head.next = temp;
                size++;
            }
            // 判断是否到了传入的索引
            if (++count == index) {
                // 构造新节点
                Node newNode = new Node(data);
                // 将当前的位置的节点设置为新节点
                newNode.next = temp.next;
                temp.next = newNode;
                size++;
            }
            // temp 始终指向下一个节点
            temp = temp.next;
        }
    }

    /**
     * 根据索引删除元素
     * @param index 传入的索引值， 从1开始
     */
    @Override
    public E remove(int index) {
        checkPositionIndex(index);

        int count = 1;
        Node temp = head;
        // 从头结点向后遍历
        while (temp.next != null) {
            if (index == 1) {
                head = head.next;
                return head.item;
            }

            if (++count == index) {
                E oldValue = temp.next.item;
                temp.next = temp.next.next;
                return oldValue;
            }
            // temp 始终指向下一个节点
            temp = temp.next;
        }

        return null;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E set(int index, E data) {
        if (data == null)
            throw new NullPointerException();
        checkPositionIndex(index);

        int count = 1;
        Node temp = head;
        while (temp != null) {
            if (count++ == index) {
                E oldValue = temp.item;
                temp.item = data;
                return oldValue;
            }
            temp = temp.next;
        }

        return null;
    }

    @Override
    public boolean removeAll(E data) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public boolean contains(E data) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * 检测索引位置是否合法
     * @param index
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IllegalArgumentException("参数不合法");
    }

    private boolean isPositionIndex(int index) {
        return index >= 1 && index <= size;
    }

    @Override
    public String toString() {
        //链队列为空链队列时  
        if (size == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = head; current != null; current = current.next) {
                sb.append(current.item.toString() + ", ");
            }
            int len = sb.length();  
            return sb.delete(len - 2, len).append("]").toString();
        }
    }

    /**
     * 链表反转
     * 遍历单链表，逐个链接点进行反转。
     * 原理：
     * 使用p和q两个指针配合工作，使得两个节点间的指向反向，同时用r记录剩下的链表。
     * 
     * <a href="http://blog.csdn.net/feliciafay/article/details/6841115"></a>
     */
    @Override
    public void reverse() {
        if (head != null) {
            // 代表指向当前进行反转的下一个节点
            Node r;
            // p 代表进行节点指向反转的节点前一个节点
            Node p = head;
            // q 代表进行节点指向反转的当前节点
            Node q = head.next;

            // 首先将head指向的下一个节点置为null
            // 因为进行链表反转时头结点变成了尾节点，指向的下一个节点必然是null
            head.next = null;
            // 进行循环操作，p, q指向向前移动
            while (q != null) {
                // 将当前正在反转的节点的下一个节点指向r
                r = q.next;
                // 将当前节点的下一个节点指向其前一个节点(由指向后一个节点改为指向前一个节点)
                q.next = p;
                // p和q都向链表后面移一位
                // 原来的q变成了p
                p = q;
                // 原来的r变成了q
                q = r;
            }

            head = p;
        }
    }
}
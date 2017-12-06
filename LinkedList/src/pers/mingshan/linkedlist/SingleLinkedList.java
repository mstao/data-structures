package pers.mingshan.linkedlist;

/**
 * 单链表
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
    public boolean add(int index, E data) {
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
                return true;
            }
            // 判断是否到了传入的索引
            if (++count == index) {
                // 构造新节点
                Node newNode = new Node(data);
                // 将当前的位置的节点设置为新节点
                newNode.next = temp.next;
                temp.next = newNode;
                size++;
                return true;
            }
            // temp 始终指向下一个节点
            temp = temp.next;
        }

        return false;
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
                temp.next = temp.next.next;
                return temp.item;
            }
            // temp 始终指向下一个节点
            temp = temp.next;
        }

        return null;
    }

    @Override
    public void selectSortNode() {
        
    }

    @Override
    public void insertSortNode() {
        // TODO Auto-generated method stub
        
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
                temp.item = data;
                return data;
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
}

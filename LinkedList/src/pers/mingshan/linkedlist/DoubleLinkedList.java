package pers.mingshan.linkedlist;

import java.util.NoSuchElementException;

/**
 * 双向链表
 * 当链表只有一个结点时，first和last均指向该结点
 * 
 * @author mingshan
 *
 * @param <E>
 */
public class DoubleLinkedList<E> implements LinkedList<E> {
    // 链表结点数量
    private int size = 0;

    // 指向头结点
    private Node first;

    // 指向尾结点
    private Node last;

    /**
     * 内部Node，用于存储链表的结点
     * @author mingshan
     *
     */
    private class Node {
        // 存储结点的值
        E item;
        // 指向结点的前驱结点
        Node next;
        // 指向结点的后继结点
        Node prev;

        Node(Node prev, E element, Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public DoubleLinkedList() { }

    @Override
    public E get(int index) {
        checkElementIndex(index);
        // 获取其索引的结点
        Node node = node(index);
        return node.item;
    }

    @Override
    public E set(int index, E data) {
        if (data == null)
            throw new NullPointerException();
        checkPositionIndex(index);

        // 获取原来在该索引位置上的结点
        Node oldNode = node(index);
        // 获取原来结点的值
        E oldValue = oldNode.item;
        // 更新值
        oldNode.item = data;
        return oldValue;
    }

    @Override
    public void add(int index, E data) {
        if (data == null)
            throw new NullPointerException();
        checkPositionIndex(index);

        // 判断在该索引的结点是不是尾结点
        if (size == index) {
            // 将当前结点作为尾结点
            linkLast(data);
        } else {
            // 将结点插入到指定位置index(原来的结点之前)
            linkBefore(index, data);
        }
    }

    @Override
    public boolean add(E data) {
        if (data == null)
            throw new NullPointerException();
        // 将当前结点作为尾结点
        linkLast(data);
        return true;
    }

    /**
     * 返回头结点的值
     * @return
     */
    public E getFirst() {
        Node f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    /**
     * 返回尾结点的值
     * @return
     */
    public E getLast() {
        Node l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);

        // 获取在该索引位置上的结点
        Node c = node(index);
        E element = c.item;
        Node prev = c.prev;
        Node next = c.next;

        // 代表头结点
        if (prev == null) {
            // 将下一个结点置为头结点
            first = next;
            // 将下一个结点的前驱结点置为null
            next.prev = null;
            // 将原来头结点的后继结点置为null
            c.next = null;
        } else if (next == null) {
            // 移除尾结点
            last = prev;
            // 前一个结点的后继结点置为null
            prev.next = null;
            // 将原来尾结点的前驱结点置为null
            c.prev = null;
        } else {
            // 属于一般情况
            // 将前一个结点的后继结点置为原结点的后继结点
            prev.next = next;
            // 将后一个结点的前驱结点置为原结点的前驱结点
            next.prev = prev;
            // 切断当前删除的结点的前驱和后继结点
            c.prev = null;
            c.next = null;
        }
        // 代表头结点
/*        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            c.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            c.next = null;
        }*/

        c.item = null;
        size--;
        return element;
    }

    @Override
    public boolean removeAll(E data) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public boolean contains(E data) {
        // TODO Auto-generated method stub
        return false;
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
    public void reverse() {
        if (first != null) {
            // 代表指向当前进行反转的下一个结点
            Node r;
            // p 代表进行结点指向反转的结点前一个结点
            Node p = first;
            // q 代表进行结点指向反转的当前结点
            Node q = first.next;

            // 首先将head指向的下一个结点置为null
            // 因为进行链表反转时头结点变成了尾结点，指向的下一个结点必然是null
            first.next = null;
            // 进行循环操作，p, q指向向前移动
            while (q != null) {
                // 将当前正在反转的结点的下一个结点指向r
                r = q.next;
                // 将当前结点的下一个结点指向其前一个结点(由指向后一个结点改为指向前一个结点)
                q.next = p;
                // 将当前结点的prev改为指向下一个结点
                p.prev = q;
                // p和q都向链表后面移一位
                // 原来的q变成了p
                p = q;
                // 原来的r变成了q
                q = r;
            }
            // 将最后一个结点的prev指向为null
            p.prev = null;
            // 将原来的头结点置为尾结点
            last = first;
            // 将最后一个结点置为头结点
            first = p;
        }
    }

    public void addFirst(E data) {
        linkFirst(data);
    }

    /**
     * 将当前结点作为头结点
     */
    private void linkFirst(E data) {
        final Node f = first;
        final Node newNode = new Node(null, data, f);
        first = newNode;
        if(f == null) {
            last = newNode;
        } else {
            first = newNode; 
            f.prev = newNode;
        }
        size++;
    }

    /**
     * 将当前结点作为尾结点
     * @param e
     */
    private void linkLast(E data) {
        final Node l = last;
        final Node newNode = new Node(l, data, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            // 原来的尾结点指向新结点
            l.next = newNode;
        }
        size++;
    }

    /**
     * 将结点插入到指定位置index(原来的结点之前)
     * @param index
     * @param data
     */
    private void linkBefore(int index, E data) {
        Node curr = node(index);
        Node pred = curr.prev;
        Node newNode = new Node(pred, data, curr);
        curr.prev = newNode;

        if (pred == null) {
            first = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
    }

    /**
     * 检测元素位置是否合法
     * @param index
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("查找元素位置不合法");
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
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
        return index >= 0 && index <= size;
    }


    /**
     * 根据索引获取结点
     * @param index
     * @return
     */
    private Node node(int index) {
        // 如果当前索引值小于当前链表长度的一半，那么从头结点开始遍历
        if (index < size / 2) {
            Node temp = first;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }

            return temp;
        } else {
            // 如果当前索引值大于当前链表长度的一半，那么从尾结点反向遍历
            Node temp = last;
            for (int i = size - 1; i > index; i--) {
                temp = temp.prev;
            }

            return temp;
        }
    }

    @Override
    public String toString() {
        //链队列为空链队列时  
        if (size == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = first; current != null; current = current.next) {
                sb.append(current.item.toString() + ", ");
            }
            int len = sb.length();  
            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}

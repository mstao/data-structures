package me.mingshan.linkedlist;

import org.junit.Assert;

import java.util.NoSuchElementException;
import java.util.Objects;

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
    private Node<E> first;

    // 指向尾结点
    private Node<E> last;

    /**
     * 内部Node，用于存储链表的结点
     * @author mingshan
     *
     */
    protected static class Node<E> implements Cloneable {
        // 存储结点的值
        E item;
        // 指向结点的前驱结点
        Node<E> next;
        // 指向结点的后继结点
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
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
        Node<E> node = node(index);
        return node.item;
    }

    @Override
    public E set(int index, E data) {
        if (data == null)
            throw new NullPointerException();
        checkElementIndex(index);

        // 获取原来在该索引位置上的结点
        Node<E> oldNode = node(index);
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
        checkElementIndex(index);

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
     * @return 结点的值
     */
    public E getFirst() {
        Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    /**
     * 返回尾结点的值
     * @return 结点的值
     */
    public E getLast() {
        Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);

        // 获取在该索引位置上的结点
        Node<E> c = node(index);
        E element = c.item;
        removeNode(c);

        c.item = null;
        size--;
        return element;
    }

    private Node<E> removeNode(Node<E> node) {
        Objects.requireNonNull(node);

        Node<E> prev = node.prev;
        Node<E> next = node.next;
        Node<E> temp;

        // 代表头结点
        if (prev == null) {
            // 将下一个结点的前驱结点置为null
            next.prev = null;
            // 将下一个结点置为头结点
            first = next;
            // 将原来头结点的后继结点置为null
            node.next = null;
            temp = null;
        } else if (next == null) {
            // 前一个结点的后继结点置为null
            prev.next = null;
            // 移除尾结点
            last = prev;
            // 将原来尾结点的前驱结点置为null
            node.prev = null;
            temp = null;
        } else {
            // 属于一般情况
            // 将前一个结点的后继结点置为原结点的后继结点
            prev.next = next;
            // 将后一个结点的前驱结点置为原结点的前驱结点
            next.prev = prev;
            // 切断当前删除的结点的前驱和后继结点
            node.prev = null;
            node.next = null;
            temp = prev;
        }

        // 代表头结点
/*        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            c.prev = null;
            node.prev = null;
            node.next = null;
        }
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            c.next = null;
        }*/

        return temp;
    }

    @Override
    public boolean removeAll(E data) {
        Node<E> curr = first;
        boolean found = false;

        // 从头结点向后遍历
        while (curr != null) {
            if (curr.item.equals(data)) {

                Node<E> node = removeNode(curr);
                found = true;

                if (node == null) {
                    break;
                } else {
                    curr = node;
                }
            }

            curr = curr.next;
        }

        return found;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public boolean contains(E data) {
        Node<E> temp = first;
        while (temp.next != null) {
            if (temp.item.equals(data)) {
                return true;
            }
            temp = temp.next;
        }

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
            Node<E> r;
            // p 代表进行结点指向反转的结点前一个结点
            Node<E> p = first;
            // q 代表进行结点指向反转的当前结点
            Node<E> q = first.next;

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
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, data, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    /**
     * 将当前结点作为尾结点
     * @param data 结点数据
     */
    private void linkLast(E data) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, data, null);
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
     *
     * @param index 索引
     * @param data 数据
     */
    private void linkBefore(int index, E data) {
        Node<E> curr = node(index);
        Node<E> prev = curr.prev;
        Node<E> newNode = new Node<>(prev, data, curr);
        curr.prev = newNode;

        if (prev == null) {
            first = newNode;
        } else {
            prev.next = newNode;
        }
        size++;
    }

    /**
     * 检测元素位置是否合法
     * @param index 索引
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("查找元素位置不合法");
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * 根据索引获取结点
     * @param index 索引
     * @return 节点
     */
    private Node<E> node(int index) {
        // 如果当前索引值小于当前链表长度的一半，那么从头结点开始遍历
        Node<E> temp;
        if (index < size / 2) {
            temp = first;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }

        } else {
            // 如果当前索引值大于当前链表长度的一半，那么从尾结点反向遍历
            temp = last;
            for (int i = size - 1; i > index; i--) {
                temp = temp.prev;
            }

        }
        return temp;
    }

    @Override
    public String toString() {
        // 链队列为空链队列时
        if (size == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node<E> current = first; current != null; current = current.next) {
                sb.append(current.item.toString()).append(", ");
            }
            int len = sb.length();  
            return sb.delete(len - 2, len).append("]").toString();
        }
    }
}

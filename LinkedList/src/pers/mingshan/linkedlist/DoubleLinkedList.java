package pers.mingshan.linkedlist;

/**
 * 双向链表
 * 当链表只有一个节点时，first和last均指向该节点
 * 
 * @author mingshan
 *
 * @param <E>
 */
public class DoubleLinkedList<E> implements LinkedList<E> {
    // 链表节点数量
    transient int size = 0;

    // 指向头结点
    transient Node first;

    // 指向尾节点
    transient Node last;

    /**
     * 内部Node，用于存储链表的节点
     * @author mingshan
     *
     */
    private class Node {
        // 存储节点的值
        E item;
        // 指向节点的前驱节点
        Node next;
        // 指向节点的后继节点
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
        // 获取其索引的节点
        Node node = node(index);
        return node.item;
    }

    @Override
    public E set(int index, E data) {
        if (data == null)
            throw new NullPointerException();
        checkPositionIndex(index);

        // 获取原来在该索引位置上的节点
        Node oldNode = node(index);
        // 获取原来节点的值
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

        // 判断在该索引的节点是不是尾节点
        if (size == index) {
            // 将当前节点作为尾节点
            linkLast(data);
        } else {
            // 将节点插入到指定位置index(原来的节点之前)
            linkBefore(index, data);
        }
    }

    @Override
    public boolean add(E data) {
        if (data == null)
            throw new NullPointerException();
        // 将当前节点作为尾节点
        linkLast(data);
        return true;
    }

    @Override
    public E remove(int index) {
        checkElementIndex(index);

        // 获取在该索引位置上的节点
        Node c = node(index);
        E element = c.item;
        Node prev = c.prev;
        Node next = c.next;

        // 代表头节点
        if (prev == null) {
            // 将下一个节点置为头节点
            first = next;
            // 将下一个节点的前驱节点置为null
            next.prev = null;
            // 将原来头节点的后继节点置为null
            c.next = null;
        } else if (next == null) {
            // 移除尾节点
            last = prev;
            // 前一个节点的后继节点置为null
            prev.next = null;
            // 将原来尾节点的前驱节点置为null
            c.prev = null;
        } else {
            // 属于一般情况
            // 将前一个节点的后继节点置为原节点的后继节点
            prev.next = next;
            // 将后一个节点的前驱节点置为原节点的前驱节点
            next.prev = prev;
            // 切断当前删除的节点的前驱和后继节点
            c.prev = null;
            c.next = null;
        }
        // 代表头节点
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
        // TODO Auto-generated method stub
        
    }

    public void addFirst(E data) {
        linkFirst(data);
    }

    /**
     * 将当前节点作为头结点
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
     * 将当前节点作为尾节点
     * @param e
     */
    private void linkLast(E data) {
        final Node l = last;
        final Node newNode = new Node(l, data, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            // 原来的尾节点指向新节点
            l.next = newNode;
        }
        size++;
    }

    /**
     * 将节点插入到指定位置index(原来的节点之前)
     * @param index
     * @param data
     */
    private void linkBefore(int index, E data) {
        Node curr = node(index);
        Node pred = curr.prev;
        Node newNode = new Node(pred, data, curr);

        if (pred == null) {
            first = newNode;
        } else {
            pred.next = newNode;
            curr.prev = newNode;
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

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * 根据索引获取节点
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
            // 如果当前索引值大于当前链表长度的一半，那么从尾节点反向遍历
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

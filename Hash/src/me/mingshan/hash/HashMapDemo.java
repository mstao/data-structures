package me.mingshan.hash;

import java.util.ArrayList;
import java.util.List;

public class HashMapDemo<K, V> implements Map<K, V> {
    // 默认大小
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    // 默认负载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    // 定义数组大小
    private int length;
    // 扩容标准 所使用的数组数量/数组长度 > 0.75
    private float loadFactor;
    // 使用数组位置的总量
    private int useSize;
    // 定义Map 骨架 只要数组
    private Entry<K, V>[] table = null;

    public HashMapDemo() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    @SuppressWarnings("unchecked")
    public HashMapDemo(int length, float loadFactor) {
        if (length < 0) {
            throw new IllegalArgumentException("参数不能为负数" + length);
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("扩容标准必须为大于0的数字" + length);
        }

        this.length = length;
        this.loadFactor = loadFactor;
        this.table = (Entry<K, V>[])new Entry[length];
    }

    /**
     * 快存
     */
    @Override
    public V put(K k, V v) {
        if (useSize > this.length * this.loadFactor) {
            // 需要扩容
            up2Size();
        }
        // 通过key来存储位置
        int index = getIndex(k, table.length);
        Entry<K,V> entry = table[index];
        if (entry == null) {
            table[index] = new Entry<K, V>(k, v, null);
        } else if (entry != null) {
            table[index] = new Entry<K, V>(k, v, entry);
        }
        useSize++;

        return table[index].getValue();
    }

    /**
     * 用来通过自身数组的长度和key来确定存储位置
     * @param k
     * @param length
     * @return
     */
    private int getIndex(K k, int length) {
        // hashCode 与运算
        int m = length - 1;
        int index = hash(k.hashCode()) & m;

        // 三元运算符处理
        return index >= 0 ? index : -index;
    }

    /**
     * 创建自己的hash算法
     * @param key
     * @return
     */
    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 增大容量，这里扩容两倍
     */
    @SuppressWarnings("unchecked")
    private void up2Size() {
        Entry<K, V>[] newTable = (Entry<K,V>[])new Entry[2 * this.length];
        // 原来数组有非常多的Entry对象，由于Entry对象散列，需要再次散列
        againHash(newTable);
    }

    /**
     * 存储的对象存储到新数组中（再次散列）
     * @param newTable
     */
    private void againHash(Entry<K, V>[] newTable) {
        // 将数组里面的对象封装到List
        List<Entry<K, V>> entryList = new ArrayList<Entry<K, V>>();

        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }
            foundEntryByNext(table[i], entryList);
        }
        if (entryList.size() > 0) {
            useSize = 0;
            this.length = 2 * this.length;
            table = newTable;
            for (Entry<K, V> entry : entryList) {
                if (entry.next != null) {
                    //形成链表关系取消掉
                    entry.next = null;
                }
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 寻找entry对象
     * @param entry
     * @param entryList
     */
    private void foundEntryByNext(Entry<K, V> entry, List<Entry<K, V>> entryList) {
        if (entry != null && entry.next != null) {
            // 说明entry对象已经形成链表结构
            entryList.add(entry);
            // 需要递归
            foundEntryByNext(entry.next, entryList);
        } else {
            entryList.add(entry);
        }
    }

    /**
     * 快取
     */
    @Override
    public V get(K k) {
        int index = getIndex(k, table.length);
        if (table[index] == null) {
            throw new NullPointerException();
        }
        return findValueByEntryKey(k, table[index]);
    }

    private V findValueByEntryKey(K k, Entry<K, V> entry) {
        Entry<K, V> e = entry;
        while (e != null) {
            if (k == e.getKey() || k.equals(e.getKey()))
                return e.getValue();
            e = e.next;
        }

        return null;
    }

    /**
     * 移除
     * @param k
     */
    @Override
    public V remove(K k) {
        int index = getIndex(k, table.length);
        Entry<K, V> e = table[index];
        Entry<K, V> prev = null;
        
        while (e != null && (!(k == e.getKey() ||
                (k != null && k.equals(e.getKey()))))) {
            prev = e;
            e = e.next;
        }

        if (e == null) {
            return null;
        }

        Entry<K, V> next = e.next;
        if (prev != null && next != null) {
            prev.next = next;
        } else if (prev != null && next == null) {
            prev.next = null;
        } else if (prev == null && next != null) {
            // Node is the head
            table[index] = next;
        } else {
            // prev==null && next==null
            table[index] = null;
        }

        useSize--;

        return e.v;
    }

    static class Entry<K, V> implements Map.Entry<K, V> {
        K k;
        V v;
        Entry<K, V> next;

        public Entry(K k,V v,Entry<K, V> next){
            this.k = k;
            this.v = v;
            this.next = next;
        }

        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }
    }
}

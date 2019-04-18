package pers.mingshan.linkedlist;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author mingshan
 */
public class LruCache<K, V> {

    private static final int DEFAULT_CAPACITY = 16;

    private final Map<K, V> map;

    private final int capacity;
    private int currSize;

    public LruCache() {
        this(DEFAULT_CAPACITY)
    }

    public LruCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity <= 0");
        }
        this.map = new LinkedHashMap<>(capacity);
        this.capacity = capacity;
    }

    
}

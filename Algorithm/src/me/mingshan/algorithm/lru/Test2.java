package me.mingshan.algorithm.lru;

/**
 * @author mingshan
 */
public class Test2 {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(1);

        cache.put(2, 1);
        System.out.println(cache.get(2));
        cache.put(3, 2);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
    }
}

package com.hashmap;

public interface Map<K,V> {
      public V put(K k,V v);
      public V get(K k);
      public V remove(K k);

      interface Entry<K,V>{
          public K getKey();
          public V getValue();
      }
}

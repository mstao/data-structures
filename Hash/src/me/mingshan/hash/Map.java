package me.mingshan.hash;

public interface Map<K,V> {
      V put(K k, V v);
      V get(K k);
      V remove(K k);

      interface Entry<K,V>{
          K getKey();
          V getValue();
      }
}

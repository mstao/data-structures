package com.hashmap;

import java.util.ArrayList;
import java.util.List;

public class HashMapDemo<K, V> implements HashMap<K, V> {
    //定义数组大小
    private  static int defaultLength=11;

    //扩容标准 所使用的数组数量/数组长度  >0.75
    private  static double defaultAddSizeFactor=0.75;

    //使用数组位置的总量
    private int useSize;
    //定义Map 骨架 只要数组
    private  Entry<K,V>[] table=null;

    public HashMapDemo(){
        this(defaultLength,defaultAddSizeFactor);
    }

    public HashMapDemo(int length,double defaultAddSizeFactor){
        if(length<0){
            throw new IllegalArgumentException("参数不能为负数"+length);
        }
        if(defaultAddSizeFactor<=0 || Double.isNaN(defaultAddSizeFactor) ){
            throw new IllegalArgumentException("扩容标准必须为大于0的数字"+length);
        }
        this.defaultLength=length;
        this.defaultAddSizeFactor=defaultAddSizeFactor;
        table=new Entry[defaultLength];
    }

    //快取
    @Override
    public V put(K k, V v) {
        // TODO Auto-generated method stub
        if(useSize>defaultLength*defaultAddSizeFactor){
            //需要扩容
            up2Size();
        }
        //通过key来存储位置
        int index=getIndex(k,table.length);
        Entry<K,V> entry =table[index];
        if(entry==null){
            table[index]=new Entry(k,v,null);
            useSize++;
        }else if(entry!=null){
            //entry!=null
             table[index]=new Entry(k,v,entry);
        }
        return table[index].getValue();
    }

    //用来通过自身数组的长度和key来确定存储位置
    private int getIndex(K k, int length) {
        // TODO Auto-generated method stub
        
        //hashCode 与运算
        int m=length-1;
        int index = hash(k.hashCode()) & m;
        
        //三元运算符处理
        return index>=0 ? index: -index;
    }

    //创建自己的hash算法
    private int hash(int hashCode) {
        // TODO Auto-generated method stub
        hashCode=hashCode^((hashCode>>> 20)^(hashCode>>> 12));
        return hashCode^((hashCode>>> 7)^(hashCode>>> 4));
    }

    //增大容量，这里扩容两倍
    private void up2Size() {
        // TODO Auto-generated method stub
        Entry<K,V>[] newTable =new Entry[2*defaultLength];
        //原来数组有非常多的Entry对象，由于Entry对象散列，需要再次散列
        againHash(newTable);
    }

    //存储的对象存储到新数组中（再次散列）
    private void againHash(HashMapDemo<K, V>.Entry<K, V>[] newTable) {
        // TODO Auto-generated method stub
        //将数组里面的对象封装到List
        List<Entry<K,V>> entryList=new  ArrayList<Entry<K,V>>();

        for(int i=0;i<table.length;i++){
            if(table[i] ==null){
                continue;
            }
            foundEntryByNext(table[i],entryList);
        }
        if(entryList.size()>0){
            useSize=0;
            defaultLength=2*defaultLength;
            table =newTable;
            for(Entry<K,V> entry:entryList){
                if(entry.next!=null){
                    //形成链表关系取消掉
                    entry.next=null;
                }
                put(entry.getKey(),entry.getValue());
            }
        }
    }

    //寻找entry对象
    private void foundEntryByNext(HashMapDemo<K, V>.Entry<K, V> entry, List<HashMapDemo<K, V>.Entry<K, V>> entryList) {
        // TODO Auto-generated method stub
        if(entry !=null  && entry.next !=null){
            //说明entry对象已经形成链表结构
            entryList.add(entry);
            //需要递归
            foundEntryByNext(entry.next,entryList);
        }else{
            entryList.add(entry);
        }
        
        
    }

    //快取
    @Override
    public V get(K k) {
        // TODO Auto-generated method stub
        int index=getIndex(k,table.length);
        if(table[index] ==null){
            return (V) new NullPointerException();
        }
        return findValueByEntryKey(k,table[index]);

    }

    private V findValueByEntryKey(K k, HashMapDemo<K, V>.Entry<K, V> entry) {
        // TODO Auto-generated method stub
        
        if(k ==entry.getKey() || k.equals(entry.getKey())){
            return entry.getValue();
        }else if(entry.next!=null){
            return findValueByEntryKey(k,entry.next);
        }
        return entry.getValue();
        
    }

    class  Entry<K,V> implements HashMap.Entry<K,V> {
        K k;
        V v;
        Entry<K,V> next;
        
        public Entry(K k,V v,Entry<K,V> next){
            
            this.k=k;
            this.v=v;
            this.next=next;
            
        }
        public K getKey(){
            return k;
            
        }
        public V getValue(){
            return v;
            
        }
    }
}

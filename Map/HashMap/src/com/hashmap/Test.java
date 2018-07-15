package com.hashmap;

public class Test {
    public static void main(String[] args){
        Map<String, String> hashmap = new HashMapDemo<String, String>();
        hashmap.put("han", "zzzz");
        hashmap.put("dd", "asadasdsadad");
        hashmap.put("cx", "ccccccc");

        System.out.println(hashmap.get("han"));
        hashmap.remove("dd");
        System.out.println(hashmap.get("dd"));
    }
}

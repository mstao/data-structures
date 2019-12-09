package me.mingshan.hash;

public class Test {
    public static void main(String[] args){
        Map<String, String> hashmap = new HashMap<String, String>();
        hashmap.put("han", "zzzz");
        hashmap.put("dd", "asadasdsadad");
        hashmap.put("cx", "ccccccc");

        System.out.println(hashmap.get("han"));
        System.out.println(hashmap.remove("dd"));
        System.out.println(hashmap.get("dd"));
    }
}

package me.mingshan.algorithm.distinct;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author mingshan
 */
public class Distinct {

    public static void main(String[] args) {
        User user1 = new User();
        user1.setId("1");
        user1.setName("李华");
        user1.setCity("郑州");
        user1.setSchool("郑州大学");

        User user5 = new User();
        user1.setId("1");
        user1.setName("李华");
        user1.setCity("郑州");
        user1.setSchool("郑州大学");

        User user2 = new User();
        user2.setId("2");
        user2.setName("李华");
        user2.setCity("郑州");
        user2.setSchool("郑州轻工业");

        User user3 = new User();
        user3.setId("3");
        user3.setName("小明");
        user3.setCity("郑州");
        user3.setSchool("郑州大学");

        User user4 = new User();
        user4.setId("4");
        user4.setName("李华");
        user4.setCity("广州");
        user4.setSchool("广州大学");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        List<User> users2 = streamDistinct(users, user -> user.getName() + "," + user.getCity());
        //users2.stream().forEach(System.out::println);

        ArrayList<User> tempList = new ArrayList<>();
        tempList.addAll(users);
        tempList.removeAll(users2);
        tempList.stream().forEach(System.out::println);

        System.out.println("---");
        users.stream().forEach(System.out::println);
    }

    public static <T> List<T> streamDistinct(List<T> source, Function<? super T, ?> keyExtractor) {
        Objects.requireNonNull(source);

        return source.parallelStream().filter(distinctByKey(keyExtractor))
                .collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Objects.requireNonNull(keyExtractor);

        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }


}

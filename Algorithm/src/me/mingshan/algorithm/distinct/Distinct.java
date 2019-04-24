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

        List<User> users2 = streamDistinct(users, user -> user.getName() + "," + user.getCity());
        //users2.stream().forEach(System.out::println);

        List<User> repeat = getRepeatData(users, users2);
        repeat.stream().forEach(System.out::println);

        System.out.println("---");
        users.stream().forEach(System.out::println);

        System.out.println("---");

        List<User> repeatData = getRepeatData(users, user -> user.getName() + "," + user.getCity());
        if (repeatData != null) {
            repeatData.stream().forEach(System.out::println);
        }
    }

    /**
     * 计算重复数据
     *
     * @param source 源集合
     * @param keyExtractor key生成Function接口，例如： {@code user -> user.getName() + "," + user.getCity()}
     * @param <T> 泛型参数
     * @return 去重后的集合
     */
    public static <T> List<T> streamDistinct(List<T> source, Function<? super T, ?> keyExtractor) {
        Objects.requireNonNull(source);

        return source.parallelStream().filter(distinctByKey(keyExtractor))
                .collect(Collectors.toList());
    }

    /**
     * 重复key生成方式
     *
     * @param keyExtractor key生成Function接口
     * @param <T> 泛型参数
     * @return {@code Predicate<T>}
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Objects.requireNonNull(keyExtractor);

        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }


    /**
     * 给定两个集合，获取其差集
     *
     * @param source 源集合
     * @param distinctSource 另一个集合
     * @param <T> 泛型参数
     * @return 差集
     */
    public static <T> List<T> getRepeatData(List<T> source, List<T> distinctSource) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(distinctSource);

        ArrayList<T> tempList = new ArrayList<>();
        tempList.addAll(source);
        tempList.removeAll(distinctSource);
        return tempList;
    }

    /**
     * 获取一个集合重复的数据，没有重复返回{@code null}
     *
     * @param source 源集合
     * @param keyExtractor key生成Function接口，例如： {@code user -> user.getName() + "," + user.getCity()}
     * @param <T> 泛型参数
     * @return 重复的数据集合（去重后）
     */
    public static <T> List<T> getRepeatData(List<T> source, Function<? super T, ?> keyExtractor) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(keyExtractor);

        List<T> distinctSource = streamDistinct(source, keyExtractor);
        if (distinctSource.size() == source.size()) {
            return null;
        }

        ArrayList<T> tempList = new ArrayList<>();
        tempList.addAll(source);
        tempList.removeAll(distinctSource);
        return tempList;
    }
}

package me.mingshan.algorithm.distinct;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 获取列表中重复的元素
 *
 * @author hanjuntao
 */
public class FindRepeatItem {

  public static void main(String[] args) {
    List<Integer> source = Arrays.asList(1, 2, 3, 2, 3, 4,5,6);
    List<Integer> integers = find(source);
    integers.forEach(System.out::println);
  }

  public static <T> List<T> find(List<T> source) {
    return source.stream() // list 对应的 Stream
        .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum)) // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
        .entrySet().stream() // 所有 entry 对应的 Stream
        .filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
        .map(Map.Entry::getKey) // 获得 entry 的键（重复元素）对应的 Stream
        .collect(Collectors.toList());  // 转化为 List
  }
}

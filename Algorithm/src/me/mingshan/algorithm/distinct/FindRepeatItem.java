package me.mingshan.algorithm.distinct;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 获取列表中重复的元素
 *
 * @author hanjuntao
 */
public class FindRepeatItem {

  public static void main(String[] args) {
    List<Integer> source = Arrays.asList(1, 2, 3, 2, 3, 4, 5, 5, 6, 6);
    List<Integer> integers = find2(source);
    integers.forEach(System.out::println);

    System.out.println(findFirst(source));
  }

  /**
   * Stream 版本
   *
   * @param source
   * @param <T>
   * @return
   */
  public static <T> List<T> find(List<T> source) {
    if (source == null || source.isEmpty()) {
      return Collections.emptyList();
    }

    return source.stream() // list 对应的 Stream
        .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum)) // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
        .entrySet().stream() // 所有 entry 对应的 Stream
        .filter(entry -> entry.getValue() > 1) // 过滤出元素出现次数大于 1 的 entry
        .map(Map.Entry::getKey) // 获得 entry 的键（重复元素）对应的 Stream
        .collect(Collectors.toList());  // 转化为 List
  }

  public static <T> Set<T> find0(List<T> source) {
    return source.stream().filter(i -> source.stream().filter(i::equals).count() > 1).collect(Collectors.toSet());
  }

  /**
   * Stream 版本
   *
   * @param source
   * @param <T>
   * @return
   */
  public static <T> List<T> find2(List<T> source) {
    if (source == null || source.isEmpty()) {
      return Collections.emptyList();
    }

    Map<T, Integer> countMap = new HashMap<>();
    List<T> result = new ArrayList<>();

    for (T item : source) {
      int count = countMap.getOrDefault(item, 1);
      if (count == -1) {
        continue;
      }

      if (count > 1) {
        result.add(item);
        countMap.put(item, -1);
      } else {
        countMap.put(item, count + 1);
      }
    }

    return result;
  }

  /**
   * 获取第一个出现重复的元素
   *
   * @param source
   * @param <T>
   * @return
   */
  public static <T> T findFirst(List<T> source) {
    if (source == null || source.isEmpty()) {
      return null;
    }

    for (int i = 0; i < source.size(); i++) {
      for (int j = i + 1; j < source.size(); j++) {
        if (source.get(i).equals(source.get(j))) {
          return source.get(i);
        }
      }
    }

    return null;
  }
}

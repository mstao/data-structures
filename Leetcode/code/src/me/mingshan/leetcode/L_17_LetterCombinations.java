package me.mingshan.leetcode;

import java.util.*;

/**
 * 17. 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class L_17_LetterCombinations {

  public static void main(String[] args) {
    System.out.println(letterCombinations("23"));
  }

  public static List<String> letterCombinations(String digits) {
    if (digits == null || digits.length() == 0) {
      return Collections.emptyList();
    }

    Map<Integer, char[]> rules = new HashMap<>();
    rules.put(2, new char[]{'a', 'b', 'c'});
    rules.put(3, new char[]{'d', 'e', 'f'});
    rules.put(4, new char[]{'g', 'h', 'i'});
    rules.put(5, new char[]{'j', 'k', 'l'});
    rules.put(6, new char[]{'m', 'n', 'o'});
    rules.put(7, new char[]{'p', 'q', 'r', 's'});
    rules.put(8, new char[]{'t', 'u', 'v'});
    rules.put(9, new char[]{'w', 'x', 'y', 'z'});

    List<String> result = new ArrayList<>();

    List<char[]> sequence = new ArrayList<>();

    char[] chars = digits.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      Integer value = Integer.valueOf(String.valueOf(chars[i]));
      sequence.add(rules.get(value));
    }

    if (sequence.size() == 1) {
      for (char item : sequence.get(0)) {
        result.add(String.valueOf(item));
      }
    } else if (sequence.size() == 2) {
      for (char item : sequence.get(0)) {
        for (char item2 : sequence.get(1)) {
          result.add(String.valueOf(item) + String.valueOf(item2));
        }
      }
    } else if (sequence.size() == 3) {
      for (char item : sequence.get(0)) {
        for (char item2 : sequence.get(1)) {
          for (char item3 : sequence.get(2)) {
            result.add(String.valueOf(item) + String.valueOf(item2) + String.valueOf(item3));
          }
        }
      }
   } else if (sequence.size() == 4) {
      for (char item : sequence.get(0)) {
        for (char item2 : sequence.get(1)) {
          for (char item3 : sequence.get(2)) {
            for (char item4 : sequence.get(3)) {
              result.add(String.valueOf(item) + String.valueOf(item2) + String.valueOf(item3) + String.valueOf(item4));
            }
          }
        }
      }
    }

    return result;
  }

}


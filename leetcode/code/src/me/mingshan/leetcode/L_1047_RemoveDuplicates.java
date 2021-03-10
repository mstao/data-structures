package me.mingshan.leetcode;

/**
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 *
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 *
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class L_1047_RemoveDuplicates {

  public static void main(String[] args) {
    String s = "abbbcd";
    String s2 = "acd";
    String s3 = "a";
    String s4 = "abbaca";
    String s5 = "aaaaaaaaa";
    String s6 = "aaa";
    String s7 = "aa";
    String s8 = "baa";
    String s9 = "aab";

    System.out.println("结果：" + removeDuplicates(s));
    System.out.println("结果：" + removeDuplicates(s2));
    System.out.println("结果：" + removeDuplicates(s3));
    System.out.println("结果：" + removeDuplicates(s4));
    System.out.println("结果：" + removeDuplicates(s5));
    System.out.println("结果：" + removeDuplicates(s6));
    System.out.println("结果：" + removeDuplicates(s7));
    System.out.println("结果：" + removeDuplicates(s8));
    System.out.println("结果：" + removeDuplicates(s9));
  }

  public static String removeDuplicates(String S) {
    if (S == null || S.length() == 0) {
      return S;
    }

    String result = S;

    while (true) {
      String oldResult = result;
      result = replace(result);

      if (result.equals(oldResult)) {
        break;
      }
    }

    return result;
  }

  public static String replace(String S) {
    if (S == null || S.length() == 0 || S.length() == 1) {
      return S;
    }

    int first = 0;

    char[] chars = S.toCharArray();

    for (int i = 0; i < chars.length; i++) {
      if (first >= 1) {
        char firstValue = chars[first];
        char lastValue = chars[first - 1];

        // 两者不一样，让后面的指针前进一步，跟上first
        if (firstValue == lastValue) {
          // first 第一次探测到与之前的不一致，此时进行截取操作
          String s1 =  "";
          if (first - 1 > 0) {
            s1 = S.substring(0, first - 1);
          }

          s1 += S.substring(first + 1);

          return s1;
        }
      }

      first++;
    }

    return S;
  }

}

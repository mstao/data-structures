package me.mingshan.algorithm.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 四则运算，支持优先级
 * <p>
 * 例如计算：1 + 2 * 3 - ( 1 + 2 / 3 * ( 2 + 3))
 *
 * @author mingshan
 */
public class Calculator {
  /**
   * 支持的运算符
   */
  private static final String[] SUPPORT_OPERATOR = {"+", "-", "*", "/", "(", ")", "\0"};
  /**
   * 操作符索引map
   */
  private static final Map<String, Integer> OPERATOR_INDEX_MAP = new HashMap<>();

  static {
    for (int i = 0; i < SUPPORT_OPERATOR.length; i++) {
      OPERATOR_INDEX_MAP.put(SUPPORT_OPERATOR[i], i);
    }
  }

  /**
   * 运算符优先级表 栈顶运算符索引，当前运算符索引
   */
  private static final String[][] PRIORITY_TABLE = {
// 当前运算符  +     -    *    /    (    )    \0
      // 栈顶的运算符
      {">", ">", "<", "<", "<", ">", ">"},  // +
      {">", ">", "<", "<", "<", ">", ">"},  // -
      {">", ">", ">", ">", "<", ">", ">"},  // *
      {">", ">", ">", ">", "<", ">", ">"},  // /
      {"<", "<", "<", "<", "<", "=", ">"},  // (
      {" ", " ", " ", " ", " ", " ", ">"},  // )
      {"<", "<", "<", "<", "<", "<", "="},  // \0
  };

  public static void main(String[] args) {
    //solution("1 + 2 * 3 - ( 1 + 2 / 3 * ( 2 + 3))");
    //System.out.println(solution("1 + 2"));
    System.out.println(solution("1 + 2 * 3"));
    //System.out.println(solution("1 + 2 * (3 + 1)"));
  }

  public static int solution(String expression) {
    if (expression == null || expression.length() == 0) {
      return 0;
    }

    // 操作栈
    Stack<Character> operatorStack = new Stack<>();
    // 数值栈
    Stack<Integer> numberStack = new Stack<>();
    char[] chars = expression.toCharArray();

    for (char item : chars) {
      System.out.println(item);
      if (item == ' ') {
        continue;
      }

      String itemStr = String.valueOf(item);

      // 字符是数字
      if (Character.isDigit(item)) {
        numberStack.push(Integer.parseInt(itemStr));
      } else if (OPERATOR_INDEX_MAP.containsKey(itemStr)) {
        if (operatorStack.isEmpty()) {
          operatorStack.push(item);
        } else {
          // 字符是操作符
          Character stackChar = operatorStack.pop();
          Integer stackIndex = OPERATOR_INDEX_MAP.get(String.valueOf(stackChar));
          Integer currIndex = OPERATOR_INDEX_MAP.get(itemStr);
          String order = PRIORITY_TABLE[stackIndex][currIndex];

          switch (order) {
            // 当前操作符优先级高于操作栈栈顶操作符
            case "<":
              // 计算推迟，当前操作符入栈
              operatorStack.push(item);
              break;
            case "=":
              // 优先级级相等，说明当前操作符为右括号，此时把括号去掉
              operatorStack.pop();
              break;
            case ">":
              // 当前操作符优先级低于操作栈栈顶操作符，此时实施计算
              int num1 = numberStack.pop();
              int num2 = numberStack.pop();
              numberStack.push(calculate(num1, num2, item));
              break;
            default:
              System.out.println("zz");
          }
        }
      } else {
        throw new IllegalArgumentException("不支持的运算符：" + item);
      }
    }

    return numberStack.pop();
  }

  /**
   * 计算结果
   *
   * @param num1 操作数1
   * @param num2 操作数2
   * @param item 运算符
   * @return 计算结果
   */
  private static int calculate(int num1, int num2, char item) {
    switch (String.valueOf(item)) {
      case "+":
        return num1 + num2;
      case "-":
        return num1 - num2;
      case "*":
        return num1 * num2;
      case "/":
        return num1 / num2;
      default:
        throw new UnsupportedOperationException("位置的运算符：" + item);
    }
  }
}

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
     // 当前运算符  +    -    *    /    (    )    \0
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
    System.out.println(solution("1 + 2"));
    System.out.println(solution("1 * 2 + 3"));
    System.out.println(solution("1 + 2 * 3"));
    System.out.println(solution("1 + 2 * 3 - 4 / 2 + 6 / 2 - 7"));

    System.out.println(solution("1 + 2 * (3 * (1 + 2))"));
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

    for (int i = 0; i < chars.length; i++) {
      calculate(operatorStack, numberStack, String.valueOf(chars[i]), i == chars.length - 1);
    }

    while (!operatorStack.isEmpty()) {
      calculate(operatorStack, numberStack, String.valueOf(numberStack.pop()), true);
    }

    return numberStack.pop();
  }

  /**
   * 根据传入的 {@code item} 元素，进行计算
   *
   * @param operatorStack 操作数栈
   * @param numberStack 数值栈
   * @param item 传入的元素
   * @param isLast 当前元素是否为实际数值栈中栈顶元素
   */
  private static void calculate(Stack<Character> operatorStack, Stack<Integer> numberStack, String item,
                                boolean isLast) {
    if (" ".equals(item)) {
      return;
    }

    char[] items = item.toCharArray();

    // 字符是数字
    if (Character.isDigit(items[0])) {
      int itemInt = Integer.parseInt(item);
      // 判断是不是最后一位
      if (isLast) {
        int result = calculate(itemInt, Integer.parseInt(String.valueOf(numberStack.pop())), operatorStack.pop());
        numberStack.push(result);
      } else {
        numberStack.push(itemInt);
      }
    } else if (OPERATOR_INDEX_MAP.containsKey(item)) {
      handleOperator(operatorStack, numberStack, items[0]);
    } else {
      throw new IllegalArgumentException("不支持的运算符：" + item);
    }
  }

  /**
   * 如果item是数组，并且数值栈连续出现数字，需要将其转为实际数值
   *
   * @param numberStack 操作栈
   * @param item 当前数字
   */
  private static void handleContinuousNumber(Stack<Integer> numberStack, String item) {
    Stack<Integer> helpStack = new Stack<>();
    while (!numberStack.isEmpty()) {

    }
  }

  /**
   * 处理操作符计算
   *
   * @param operatorStack 操作栈
   * @param numberStack   数值栈
   * @param currOperator  当前操作符
   */
  private static void handleOperator(Stack<Character> operatorStack, Stack<Integer> numberStack, char currOperator) {
    if (operatorStack.isEmpty()) {
      operatorStack.push(currOperator);
    } else {
      // 字符是操作符
      Character stackChar = operatorStack.peek();
      Integer stackIndex = OPERATOR_INDEX_MAP.get(String.valueOf(stackChar));
      Integer currIndex = OPERATOR_INDEX_MAP.get(String.valueOf(currOperator));
      String order = PRIORITY_TABLE[stackIndex][currIndex];

      switch (order) {
        // 当前操作符优先级高于操作栈栈顶操作符
        case "<":
          // 计算推迟，当前操作符入栈
          operatorStack.push(currOperator);
          break;
        case "=":
          // 优先级级相等，说明当前操作符为右括号，此时把括号去掉
          operatorStack.pop();
          break;
        case ">":
          // 当前操作符优先级低于操作栈栈顶操作符，此时实施计算
          int num1 = numberStack.pop();
          int num2 = numberStack.pop();
          numberStack.push(calculate(num1, num2, operatorStack.pop()));
          // 继续计算栈内可计算的数据
          handleOperator(operatorStack, numberStack, currOperator);
          break;
        default:
          throw new IllegalArgumentException("无效的比较结果：" + order);
      }
    }
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
        return num2 + num1;
      case "-":
        return num2 - num1;
      case "*":
        return num2 * num1;
      case "/":
        return num2 / num1;
      default:
        throw new UnsupportedOperationException("位置的运算符：" + item);
    }
  }
}

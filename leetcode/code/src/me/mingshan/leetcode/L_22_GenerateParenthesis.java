package me.mingshan.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 22. 括号生成
 *
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * https://leetcode-cn.com/problems/generate-parentheses/
 */
public class L_22_GenerateParenthesis {
  public List<String> generateParenthesis(int n) {
    if (n <= 1) {
      return Collections.emptyList();
    }

    List<String> result = new ArrayList<>();
    Stack<String> stack = new Stack<>();
    stack.push("(");
    for (int i = 1; i < n; i++){

    }

    return null;
  }
}

package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;
import java.util.Stack;

/**
 * ticket: https://www.notion.so/Valid-Parentheses-225365c6c6e24125aeb06aec1568d9c9
 */
public class ValidParenthesis {

  private HashMap<Character, Character> hashMap;

  public ValidParenthesis() {
    hashMap = new HashMap<>();
    this.hashMap.put(')', '(');
    this.hashMap.put('}', '{');
    this.hashMap.put(']', '[');
  }

  /**
   * Big-O: O(n) Justification: Since, for loop is used to traverse and push and pop per character
   * at a time. Assuming that it does start with any open parenthesis.
   */
  public boolean validParenthesis(String str) {
    Stack<Character> stack = new Stack<>();
    for (char c : str.toCharArray()) {
      if (hashMap.containsKey(c)) {
        if (stack.empty()) {
          return false;
        } else if (stack.peek() == hashMap.get(c)) {
          stack.pop();
        } else {
          return false;
        }
      } else {
        stack.push(c);
      }
    }
    return stack.size() == 0;
  }

}

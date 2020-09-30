package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

/**
 * ticket: https://www.notion.so/Implement-Queue-using-Stacks-9e36840d7f944b8c8c4de37d709c5bc9
 */
public class QueueWithStack<E> {

  private Stack<Integer> s1 = new Stack<>();
  private Stack<Integer> s2 = new Stack<>();

  /**
   * Big O: O(1) Justification: Direct insert.
   */
  public void enqueue(int n) {
    s1.push(n);
  }

  /**
   * Big O: O(n) Justification: while loop.
   */
  public int dequeue() {
    Stack<Integer> s2 = new Stack<>();
    int i = 1;
    int len = s1.size();
    while (i < len) {
      s2.push(s1.pop());
      i++;
    }
    int element = s1.pop();
    s1 = s2;
    s2.clear();
    while (!s1.empty()) {
      s2.push(s1.pop());
    }
    s1 = s2;
    return element;
  }

  /**
   * Big O: O(1) Justification: direct insert.
   */
  public void enqueue1(int n) {
    s1.push(n);
  }

  /**
   * Big O: O(1) Justification: direct removal.
   */
  public int dequeue1() {
    if (s2.isEmpty()) {
      while (!s1.empty()) {
        s2.push(s1.pop());
      }
    }
    return s2.pop();
  }
}

package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ticket: https://www.notion.so/Implement-Stack-using-Queue-6734526e4ac04456a6087cbd0d73d7db
 */
public class StackWithQueue<E> {

  Queue<Integer> q1 = new LinkedList<>();

  /**
   * Big O: O(1) Justification: direct insert
   */
  public void push(int n) {
    q1.add(n);
  }

  /**
   * Big O: O(n) Justification: while loop.
   */
  public int pop() {
    Queue<Integer> q2 = new LinkedList<>();
    int i = 0;
    int len = q1.size() - 1;
    while (i < len) {
      q2.add(q1.remove());
      i++;
    }
    int element = q1.remove();
    q1 = q2;
    return element;
  }

  /**
   * Big O: O(n) Justification: while loop.
   */
  public void pushWithOneQueue(int n) {
    if (q1.isEmpty()) {
      q1.add(n);
    } else {
      q1.add(n);
      int i = 1;
      while (i < q1.size()) {
        int element = q1.remove();
        q1.add(element);
        i++;
      }
    }
  }

  /**
   * Big O: O(1) Justification: direct removal
   */
  public int popWithOneQueue() {
    return q1.remove();
  }
}

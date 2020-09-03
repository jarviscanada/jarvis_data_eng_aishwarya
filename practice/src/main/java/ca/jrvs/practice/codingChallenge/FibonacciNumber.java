package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Fibonacci-Number-Climbing-Stairs-5a4f3286a5fc4f3fa6fe4f408cc49f42
 */
public class FibonacciNumber {

  /**
   * Big-O: O(2^n) Justification: N elements in the list.
   */
  public int fibRecursion(int n) {
    if (n == 0) {
      return 0;
    }
    if (n == 1 || n == 2) {
      return 1;
    }
    return fibRecursion(n - 1) + fibRecursion(n - 2);
  }

  /**
   * Big-O: O(n) Justification: Dynamic Programming, storing intermediate values and using them
   * instead of reevaluating it again and again.
   */
  public int fibDP(int n, Integer[] memo) {
    int result;
    if (memo[n] != null) {
      result = memo[n];
    } else if (n == 0) {
      result = 0;
    } else if (n == 1 || n == 2) {
      result = 1;
    } else {
      result = fibDP(n - 1, memo) + fibDP(n - 2, memo);
    }
    memo[n] = result;
    return result;
  }
}
package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class FibonacciNumberTest {

  private FibonacciNumber fib = new FibonacciNumber();

  @Test
  public void fibRecursion() {
    System.out.println(fib.fibRecursion(5));
  }

  @Test
  public void fibDP() {
    int n = 5;
    System.out.println(fib.fibDP(n, new Integer[n + 1]));
  }
}
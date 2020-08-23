package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.codingChallenge.LinkedListCycle.ListNode;
import org.junit.Test;

public class LinkedListCycleTest {

  private LinkedListCycle lc = new LinkedListCycle();

  @Test
  public void hasCycle() {
    ListNode<Integer> d2 = new ListNode<>(2, null);
    ListNode<Integer> d4 = new ListNode<>(4, d2);
    ListNode<Integer> d3 = new ListNode<>(3, d4);
    d2.next = d3;
    ListNode<Integer> d1 = new ListNode<>(1, d2);
    assertTrue(lc.hasCycle(d1));

    d4 = new ListNode<>(4, null);
    d3 = new ListNode<>(3, d4);
    d2 = new ListNode<>(2, d3);
    d1 = new ListNode<>(1, d2);
    assertFalse(lc.hasCycle(d1));

  }
}
package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.codingChallenge.ReverseLinkedList.ListNode;
import org.junit.Before;
import org.junit.Test;

public class ReverseLinkedListTest {

  private ReverseLinkedList linkedList = new ReverseLinkedList();
  private ListNode head = new ListNode(1, null);

  @Before
  public void setup() {
    ListNode current = head;
    for (int i = 2; i < 6; i++) {
      current.next = new ListNode(i, null);
      current = current.next;
    }
    ListNode present = head;
    while (present != null) {
      System.out.println(present.val);
      present = present.next;
    }
  }

  @Test
  public void reverseIteration() {
    System.out.println("\nReverse:");
    ListNode present = linkedList.reverseIteration(head);
    while (present != null) {
      System.out.println(present.val);
      present = present.next;
    }

  }

  @Test
  public void reverseRecursion() {
    System.out.println("\nReverse:");
    ListNode present = linkedList.reverseRecursion(head);
    while (present != null) {
      System.out.println(present.val);
      present = present.next;
    }
  }
}
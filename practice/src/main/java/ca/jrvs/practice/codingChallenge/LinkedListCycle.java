package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/LinkedList-Cycle-a18a676ca1a9434f9e9a20f80fcccc62
 */
public class LinkedListCycle {

  public static class ListNode<E> {

    int val;
    ListNode<E> next;

    public ListNode(int val, ListNode<E> next) {
      this.val = val;
      this.next = next;
    }
  }

  /**
   * Big-O: O(n) Justification: while loop is used to iterate.
   */
  public <E> boolean hasCycle(ListNode<E> head) {
    ListNode<E> slow = head;
    ListNode<E> fast = head.next.next;
    while (slow != null && fast != null && fast.next != null) {
      if (slow == fast) {
        return true;
      }
      slow = slow.next;
      fast = fast.next.next;
    }
    return false;
  }
}

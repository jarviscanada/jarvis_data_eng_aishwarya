package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;

/**
 * ticket: https://www.notion.so/Nth-Node-From-End-of-LinkedList-5cdf333b04d74e6cb7e9e500eb2bec40
 */
public class RemoveNthNode {

  public static class ListNode<E> {

    int val;
    ListNode<E> next;

    public ListNode(int val, ListNode<E> next) {
      this.val = val;
      this.next = next;
    }
  }

  /**
   * Big-O: O(n) Justification: while loop to determine size and iterate to the valid element.
   */
  public <E> ListNode<E> removeNthNode(ListNode<E> head, int n) {
    ListNode<E> start = new ListNode<>(0, head);
    ListNode<E> point1 = start;
    int len = 0;
    while (start.next != null) {
      len++;
      start = start.next;
    }
    len -= n;
    //removing the head
    if (len == 0) {
      return head.next;
    } else {
      while (len > 0) {
        point1 = point1.next;
        len--;
      }
      point1.next = point1.next.next;
      return head;
    }
  }

  /**
   * Big-O: O(n) Justification: while loop to determine size and iterate to the valid element.
   */
  public <E> ListNode<E> removeNthNode2(ListNode<E> head, int n) {
    ListNode<E> start = new ListNode<>(0, head);
    ListNode<E> point1 = start;
    ListNode<E> point2 = head;
    while (n > 0) {
      point2 = point2.next;
      n--;
    }
    while (point2 != null) {
      point1 = point1.next;
      point2 = point2.next;
    }
    if (head == point1.next) {
      return point1.next.next;
    }
    point1.next = point1.next.next;
    return head;
  }

  public void removeNthFromEnd(LinkedList<Integer> list, int n) {
    int point1 = 0;
    int point2 = 0;
    while (point1 < n) {
      point1++;
    }
    while (point1 < list.size()) {
      point1++;
      point2++;
    }
    list.remove(point2);
  }

  public <E> void traverse(ListNode<E> head) {
    ListNode<E> currentNode = head;
    while (currentNode != null) {
      System.out.println(currentNode.val);
      currentNode = currentNode.next;
    }
  }
}

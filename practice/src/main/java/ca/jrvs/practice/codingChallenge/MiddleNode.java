package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * ticket: https://www.notion.so/Middle-of-the-Linked-List-5be480a34018437e95e191873e10a559
 */
public class MiddleNode {

  public static class ListNode<E> {

    int val;
    ListNode<E> next;

    public ListNode(int val, ListNode<E> next) {
      this.val = val;
      this.next = next;
    }
  }

  /**
   * Big-O: O(n) Justification: while loop to iterate to the valid element.
   */
  public <E> ListNode<E> middleNode(ListNode<E> head) {
    ListNode<E> slow = head;
    ListNode<E> fast = head;

    while (slow.next != null && fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  public <E> void traverse(ListNode<E> node) {
    ListNode<E> currentNode = node;
    while (currentNode != null) {
      System.out.println(currentNode.val);
      currentNode = currentNode.next;
    }
  }

  public void removeDuplicate(LinkedList<Integer> list) {
    HashSet<Integer> set = new HashSet<>(list.size());
    int i = 0;
    while (i < list.size()) {
      if (set.contains(list.get(i))) {
        list.remove(i);
      } else {
        set.add(list.get(i));
        i++;
      }
    }
  }
}

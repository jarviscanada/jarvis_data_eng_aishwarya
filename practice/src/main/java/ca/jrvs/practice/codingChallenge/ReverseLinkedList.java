package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/Reverse-Linked-List-c578197c0d554a3e83dca4be042bf686
 */
public class ReverseLinkedList {

  /**
   * Big-O: O(n) Justification: iterating over the linkedList having n elememts.
   */
  public ListNode reverseIteration(ListNode node) {
    ListNode currentNode = node;
    ListNode nextNode;
    ListNode previousNode = null;
    while (currentNode != null) {
      nextNode = currentNode.next;
      currentNode.next = previousNode;
      previousNode = currentNode;
      currentNode = nextNode;
    }
    return previousNode;
  }

  /**
   * Big-O: O(n) Justification: Due to length of the linkedList having n elements.
   */
  public ListNode reverseRecursion(ListNode node) {
    if (node == null || node.next == null) {
      return node;
    }
    ListNode present = reverseRecursion(node.next);
    node.next.next = node;
    node.next = null;
    return present;
  }
//  public ListNode reverseRecursion(ListNode node) {
//    return reverse(node,null);
//  }
//
//  private ListNode reverse(ListNode node, ListNode prev){
//    if(node!=null) {
//      ListNode next = node.next;
//      node.next = prev;
//      return reverse(next, node);
//    }
//    return prev;
//  }

  // Definition for singly-linked list.
  public static class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}
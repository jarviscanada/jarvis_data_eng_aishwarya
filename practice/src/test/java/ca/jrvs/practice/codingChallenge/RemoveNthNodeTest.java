package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import org.junit.Test;

public class RemoveNthNodeTest {

  private RemoveNthNode rn = new RemoveNthNode();

  @Test
  public void removeNthNode() {
    RemoveNthNode.ListNode<Integer> d4 = new RemoveNthNode.ListNode<Integer>(5, null);
    RemoveNthNode.ListNode<Integer> d3 = new RemoveNthNode.ListNode<Integer>(4, d4);
    RemoveNthNode.ListNode<Integer> d2 = new RemoveNthNode.ListNode<Integer>(3, d3);
    RemoveNthNode.ListNode<Integer> d1 = new RemoveNthNode.ListNode<Integer>(2, d2);
    RemoveNthNode.ListNode<Integer> head = new RemoveNthNode.ListNode<Integer>(1, d1);
    rn.traverse(head);
    RemoveNthNode.ListNode<Integer> node = rn.removeNthNode(head, 2);
    rn.traverse(node);
  }

  @Test
  public void removeNthNode2() {
    RemoveNthNode.ListNode<Integer> d4 = new RemoveNthNode.ListNode<Integer>(5, null);
    RemoveNthNode.ListNode<Integer> d3 = new RemoveNthNode.ListNode<Integer>(4, d4);
    RemoveNthNode.ListNode<Integer> d2 = new RemoveNthNode.ListNode<Integer>(3, d3);
    RemoveNthNode.ListNode<Integer> d1 = new RemoveNthNode.ListNode<Integer>(2, d2);
    RemoveNthNode.ListNode<Integer> head = new RemoveNthNode.ListNode<Integer>(1, d1);
    rn.traverse(head);
    RemoveNthNode.ListNode<Integer> node = rn.removeNthNode2(head, 5);
    rn.traverse(node);
  }

  @Test
  public void removeNthFromEnd() {
    LinkedList<Integer> list = new LinkedList<Integer>() {{
      add(1);
      add(2);
      add(3);
      add(4);
      add(5);
    }};
    System.out.println("LinkedList" + list.toString());
    rn.removeNthFromEnd(list, 2);
    System.out.println("Update List:" + list.toString());
  }
}
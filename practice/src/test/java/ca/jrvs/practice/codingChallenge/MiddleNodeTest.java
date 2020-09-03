package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import ca.jrvs.practice.codingChallenge.MiddleNode.ListNode;
import java.util.LinkedList;
import org.junit.Test;

public class MiddleNodeTest {

  private MiddleNode mn = new MiddleNode();

  @Test
  public void middleNode() {
    System.out.println("Odd List");
    ListNode<Integer> d5 = new ListNode<>(5, null);
    ListNode<Integer> d4 = new ListNode<>(4, d5);
    ListNode<Integer> d3 = new ListNode<>(3, d4);
    ListNode<Integer> d2 = new ListNode<>(2, d3);
    ListNode<Integer> d1 = new ListNode<>(1, d2);
    mn.traverse(d1);
    ListNode<Integer> node = mn.middleNode(d1);
    System.out.println("Middle Node: " + node.val);
    mn.traverse(node);

    System.out.println("Even List");
    ListNode<Integer> d6 = new ListNode<>(6, null);
    d5 = new ListNode<>(5, d6);
    d4 = new ListNode<>(4, d5);
    d3 = new ListNode<>(3, d4);
    d2 = new ListNode<>(2, d3);
    d1 = new ListNode<>(1, d2);
    mn.traverse(d1);
    node = mn.middleNode(d1);
    System.out.println("Middle Node: " + node.val);
    mn.traverse(node);
  }

  @Test
  public void removeDuplicate() {
    LinkedList<Integer> list = new LinkedList<Integer>() {{
      add(1);
      add(2);
      add(3);
      add(2);
      add(2);
      add(1);
      add(3);
      add(2);
    }};
    System.out.println("LinkedList: " + list.toString());
    mn.removeDuplicate(list);
    System.out.println("Updated LinkedList " + list.toString());
  }
}
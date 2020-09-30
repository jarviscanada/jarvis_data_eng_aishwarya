package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class QueueWithStackTest {

  private QueueWithStack<Integer> qs = new QueueWithStack<Integer>();

//  @Before
//  public void enqueue() {
//    for(int i =1;i<=5;i++){
//      qs.enqueue(i);
//    }
//    System.out.println("Queue:"+qs.toString());
//  }
//
//  @Test
//  public void dequeue() {
//    System.out.println(qs.dequeue());
//    System.out.println(qs.dequeue());
//  }

  @Before
  public void enqueue1() {
    for (int i = 1; i <= 5; i++) {
      qs.enqueue1(i);
    }
  }

  @Test
  public void dequeue1() {
    System.out.println(qs.dequeue1());
    System.out.println(qs.dequeue1());
  }
}
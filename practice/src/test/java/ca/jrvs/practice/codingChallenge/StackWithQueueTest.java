package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Before;
import org.junit.Test;
import sun.security.util.ArrayUtil;

public class StackWithQueueTest {

  private StackWithQueue sq = new StackWithQueue();

//  @Before
//  public void push() {
//    for (int i = 1; i <= 5; i++) {
//      sq.push(i);
//    }
//    System.out.println("Stack:" + sq.q1.toString());
//  }
//
//    @Test
//    public void pop () {
//      System.out.println(sq.pop());
//      System.out.println(sq.pop());
//    }

  @Before
  public void pushWithOneQueue() {
    for (int i = 1; i <= 5; i++) {
      sq.pushWithOneQueue(i);
    }
    System.out.println("Stack:" + sq.q1.toString());
  }

  @Test
  public void popWithOneQueue() {
    System.out.println(sq.popWithOneQueue());
    System.out.println(sq.popWithOneQueue());
  }
}
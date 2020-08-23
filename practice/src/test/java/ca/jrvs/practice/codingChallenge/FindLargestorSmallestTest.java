package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindLargestorSmallestTest {

  FindLargestorSmallest fls = new FindLargestorSmallest();
  int[] num = {12,45,78,56,100,99,60};
  Integer[] nums = {12,45,78,56,100,99,60};

  @Test
  public void findLargest() {
    assertEquals(100,fls.findLargest(num));
  }

  @Test
  public void findLargestStream() {
    assertEquals(100,fls.findLargestStream(num));
  }

  @Test
  public void findLargestBuiltIn() {
    assertEquals(100,fls.findLargestBuiltIn(nums));
  }
}
package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class RemoveDuplicateTest {

  @Test
  public void removeDuplicates() {
    int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    int len = new RemoveDuplicate().removeDuplicates(nums);
    System.out.println("Length=" + len);
    for (int i = 0; i < len; i++) {
      System.out.println(nums[i]);
    }
  }
}
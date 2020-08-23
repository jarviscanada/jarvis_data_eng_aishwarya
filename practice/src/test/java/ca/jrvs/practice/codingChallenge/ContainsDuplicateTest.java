package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContainsDuplicateTest {

  ContainsDuplicate cd = new ContainsDuplicate();
  int[] nums = {1,1,1,3,3,4,3,2,4,2};
  int[] nums2 = {1,2,3,4};

  @Test
  public void containsDuplicate() {
    System.out.println(cd.containsDuplicate(nums));
    System.out.println(cd.containsDuplicate(nums2));
  }

  @Test
  public void containsDuplicateSet() {
    assertTrue(cd.containsDuplicateSet(nums));
    assertFalse(cd.containsDuplicateSet(nums2));
  }
}
package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindDuplicateTest {

  FindDuplicate fd = new FindDuplicate();
  int[] num = {1,3,4,2,2};
  int[] nums ={3,1,3,4};
  @Test
  public void findDuplicate() {
    assertEquals(2,fd.findDuplicate(num));
    assertEquals(3,fd.findDuplicate(nums));

  }

  @Test
  public void findDuplicateSet() {
    assertEquals(2,fd.findDuplicateSet(num));
    assertEquals(3,fd.findDuplicateSet(nums));
  }
}
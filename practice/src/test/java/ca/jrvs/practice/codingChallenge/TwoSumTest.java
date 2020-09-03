package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;

public class TwoSumTest {

  private TwoSum ts = new TwoSum();

  @Test
  public void twoSumBruteForce() {
    int[] num = {2, 11, 7, 9};
    System.out.println(Arrays.toString(ts.twoSumBruteForce(num, 9)));
  }

  @Test
  public void twoSumSorting() {
    int[] num = {2, 11, 7, 9};
    System.out.println(Arrays.toString(ts.twoSumSorting(num, 18)));
  }

  @Test
  public void twoSum() {
    int[] num = {2, 5, 11, 4, 9};
    System.out.println(Arrays.toString(ts.twoSum(num, 9)));
  }
}
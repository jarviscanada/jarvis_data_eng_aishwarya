package ca.jrvs.practice.codingChallenge;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;

public class MergeSortedArrayTest {

  private MergeSortedArray msa = new MergeSortedArray();

  @Test
  public void merge() {
    int[] numA = {3, 4, 5, 7, 9, 0, 0, 0, 0, 0, 0};
    int[] numB = {1, 2, 3, 6, 8};
    msa.merge(numA, 5, numB, 5);
    System.out.println(Arrays.toString(numA));
  }
}
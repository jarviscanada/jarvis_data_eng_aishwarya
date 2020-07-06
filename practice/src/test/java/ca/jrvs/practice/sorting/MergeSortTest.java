package ca.jrvs.practice.sorting;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;

public class MergeSortTest {

  private MergeSort mergeSort = new MergeSort();
  int[] arr = {4, 1, 3, 6, 5, 8};

  @Test
  public void mergeSort() {
    System.out.println(Arrays.toString(arr));
    mergeSort.mergeSort(arr, 0, arr.length - 1);
    System.out.println(Arrays.toString(arr));
  }
}